package ga.digentre.mobile.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ga.digentre.mobile.R;

public class PedidosActivity extends AppCompatActivity {

    final Context context = this;
    ListView listaPedidos;
    SimpleAdapter adapter;
    List<Map<String, String>> pedidosItemSubitem = new ArrayList<Map<String, String>>();
    Map<Integer, String> pedidos = new HashMap<Integer, String>();
    String entregadorId;

    public static final double RESTAURANTE_LATITUDE = -5.8428903;
    public static final double RESTAURANTE_LONGITUDE = -35.1974639;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        setTitle("DigEntrega - Pedidos");

        final RequestQueue queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        final String cpf = intent.getStringExtra("cpf");
        entregadorId = intent.getStringExtra("entregadorId");

        listaPedidos = (ListView) this.findViewById(R.id.listaPedidos);

        adapter = new SimpleAdapter(this, pedidosItemSubitem,
                android.R.layout.simple_list_item_2,
                new String[] {"id", "detalhes"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});


        // Tenta autenticar o usuário.
        final String url = "http://digentre.ga/webservice/listar/pedidos/entregador/";

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.get("pedidos") != null) {
                                JSONArray pedidosJSON = (JSONArray) json.get("pedidos");
                                for(int i = 0; i < pedidosJSON.length(); i++) {
                                    JSONObject pedidoJSON = pedidosJSON.getJSONObject(i);
                                    final int pedidoId = pedidoJSON.getInt("id");
                                    double latitude = pedidoJSON.getDouble("latitude");
                                    double longitude = pedidoJSON.getDouble("longitude");
                                    double peso = pedidoJSON.getDouble("peso");
                                    double volume = pedidoJSON.getDouble("volume");

                                    String conteudo = pedidoId + ";" + latitude + ";" + longitude + ";" + peso + ";" + volume + ";";
                                    Map<String, String> pedido = new HashMap<String, String>(2);
                                    pedido.put("id", "Pedido #" + pedidoId);
                                    pedido.put("detalhes", "(" + peso + " g, " + volume + " ml)");
                                    pedidosItemSubitem.add(pedido);
                                    pedidos.put(pedidoId, conteudo);
                                    adapter.notifyDataSetChanged();



                                }

                                Log.d("ELEMENTO", pedidos.toString());
                            }
                        } catch (JSONException e) {
                            Log.d("ERRO", e.toString());
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("entregadorId", entregadorId);
                return params;
            }

        };

        queue.add(jsonObjRequest);


     /*  // Temporario
        Map<String, String> pedido1 = new HashMap<String, String>(2);
        pedido1.put("id", "Pedido #" + 1);
        pedido1.put("detalhes", "(" + 6000 + " g, " + 15 + " l)");
        pedidosItemSubitem.add(pedido1);
        adapter.notifyDataSetChanged();
    */

        // Assign adapter to ListView
        listaPedidos.setAdapter(adapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Action para abrir a câmera e ler o QR Code.
        FloatingActionButton fabAdicionarPedido = (FloatingActionButton) findViewById(R.id.adicionarPedido);
        fabAdicionarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(PedidosActivity.this);
                integrator.initiateScan();
            }
        });
		
		// Action voltar para restaurante
        FloatingActionButton fabRotaRestaurante = (FloatingActionButton) findViewById(R.id.restaurante);
        fabRotaRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUri = "google.navigation:q=" + RESTAURANTE_LATITUDE + "," + RESTAURANTE_LONGITUDE;

                Uri gmmIntentUri = Uri.parse(strUri);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                //mapIntent.setPackage("com.google.android.apps.maps");
                PedidosActivity.this.startActivity(mapIntent);            }
        });

        // Action para calcular rota.
        FloatingActionButton fabCriarRota = (FloatingActionButton) findViewById(R.id.calcularRota);
        fabCriarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(PedidosActivity.this, RotasActivity.class);
                ArrayList<String> pedidosStr = new ArrayList<String>(pedidos.values());
                if(pedidosStr.size() < 1) {

                    Toast toast = Toast.makeText(PedidosActivity.this, "Nenhum pedido encontrado. Utilize a câmera para adicionar um novo pedido.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                /*intent.putStringArrayListExtra("pedidos", pedidosStr);
                PedidosActivity.this.startActivity(intent);*/

				String pedido = pedidosStr.get(0);
                String[] valores = pedido.split(";");

                //int pedidoId = Integer.parseInt(valores[0]);
                double latitude = Double.parseDouble(valores[1]);
                double longitude = Double.parseDouble(valores[2]);
                //float peso = Float.parseFloat(valores[3]);
                //float volume = Float.parseFloat(valores[4]);

                String strUri = "google.navigation:q=" + latitude + "," + longitude;

                Uri gmmIntentUri = Uri.parse(strUri);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                //mapIntent.setPackage("com.google.android.apps.maps");
                PedidosActivity.this.startActivity(mapIntent);


            }
        });


        Button btnSair = (Button) findViewById(R.id.sair);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Action responsável por fechar um pedido.
        listaPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Map<String, String> pedido = (Map<String, String>) parent.getAdapter().getItem(position);
                String pedidoIdStr  = pedido.get("id").replace("Pedido #", "");
                final int pedidoId = Integer.parseInt(pedidoIdStr);

                android.app.AlertDialog.Builder ad  = new android.app.AlertDialog.Builder(context);
                ad.setTitle("Confirmar entrega");
                ad.setNegativeButton("Cancelar", null);
                ad.setCancelable(true);
                ad.setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                final String url = "http://digentre.ga/webservice/finalizar/pedido/";
                                StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                                        url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject json = new JSONObject(response);

                                                    if (json.get("sucesso") != null && json.get("status") != null) {

                                                        pedidosItemSubitem.remove(position);
                                                        pedidos.remove(pedidoId);
                                                        adapter.notifyDataSetChanged();


                                                        return;
                                                    }
                                                } catch (JSONException e) {
                                                    Log.d("ERRO", e.toString());
                                                    e.printStackTrace();
                                                }

                                            }
                                        }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        VolleyLog.d("volley", "Error: " + error.getMessage());
                                        error.printStackTrace();
                                    }
                                }) {

                                    @Override
                                    public String getBodyContentType() {
                                        return "application/x-www-form-urlencoded; charset=UTF-8";
                                    }

                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("entregadorId", entregadorId);
                                        params.put("pedidoId", String.valueOf(pedidoId));
                                        return params;
                                    }

                                };

                                queue.add(jsonObjRequest);



                                Toast toast = Toast.makeText(PedidosActivity.this, "Pedido " + pedidoId + " removido com sucesso", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                            }
                        });
                ad.setMessage("Confirmar entrega do " + pedidoId + "?");
                ad.create().show();

            }
        });

    }

    // Obtém os resultados do QR-code.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        final RequestQueue queue = Volley.newRequestQueue(this);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {

                // Supondo que o QR code do sistema web seja gerado no formato "id;latitude;longitude;peso;volume;".
                String conteudo = result.getContents();
                String[] valores = conteudo.split(";");

                final int pedidoId = Integer.parseInt(valores[0]);
                double latitude = Double.parseDouble(valores[1]);
                double longitude = Double.parseDouble(valores[2]);
                double peso = Double.parseDouble(valores[3]);
                double volume = Double.parseDouble(valores[4]);

                if(pedidos.get(pedidoId) != null) {
                    Toast toast = Toast.makeText(this, "O pedido #" + pedidoId + " já está na lista!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                Map<String, String> pedido = new HashMap<String, String>(2);
                pedido.put("id", "Pedido #" + pedidoId);
                pedido.put("detalhes", "(" + peso + " g, " + volume + " ml)");
                pedidosItemSubitem.add(pedido);
                pedidos.put(pedidoId, conteudo);
                adapter.notifyDataSetChanged();

                final String url = "http://digentre.ga/webservice/entregar/pedido/";

                StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject json = new JSONObject(response);

                                    if (json.get("sucesso") != null && json.get("status") != null) {
                                        Log.d("ASSOCIADO", json.get("status").toString());
                                        return;
                                    }
                                } catch (JSONException e) {
                                    Log.d("ERRO", e.toString());
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("volley", "Error: " + error.getMessage());
                        error.printStackTrace();
                    }
                }) {

                    @Override
                    public String getBodyContentType() {
                        return "application/x-www-form-urlencoded; charset=UTF-8";
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("entregadorId", entregadorId);
                        params.put("pedidoId", String.valueOf(pedidoId));
                        return params;
                    }

                };

                queue.add(jsonObjRequest);

                Toast toast = Toast.makeText(this, "Pedido #" + pedidoId + " adicionado com sucesso!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}

