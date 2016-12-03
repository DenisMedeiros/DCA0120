package ga.digentre.mobile.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ga.digentre.mobile.R;

public class PedidosActivity extends AppCompatActivity {

    final Context context = this;
    ListView listaPedidos;
    SimpleAdapter adapter;
    List<Map<String, String>> pedidosItemSubitem = new ArrayList<Map<String, String>>();
    Map<Integer, String> pedidos = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        setTitle("DigEntrega - Pedidos");
        Intent intent = getIntent();
        String cpf = intent.getStringExtra("cpf");

        listaPedidos = (ListView) this.findViewById(R.id.listaPedidos);


        adapter = new SimpleAdapter(this, pedidosItemSubitem,
                android.R.layout.simple_list_item_2,
                new String[] {"id", "detalhes"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});



     /*  // Temporario
        Map<String, String> pedido1 = new HashMap<String, String>(2);
        pedido1.put("id", "Pedido #" + 1);
        pedido1.put("detalhes", "(" + 6000 + " g, " + 15 + " l)");
        pedidosItemSubitem.add(pedido1);
        adapter.notifyDataSetChanged();

        Map<String, String> pedido2 = new HashMap<String, String>(2);
        pedido2.put("id", "Pedido #" + 2);
        pedido2.put("detalhes", "(" + 3000 + " g, " + 2 + " l)");
        pedidosItemSubitem.add(pedido2);
        adapter.notifyDataSetChanged();

        Map<String, String> pedido3 = new HashMap<String, String>(2);
        pedido3.put("id", "Pedido #" + 3);
        pedido3.put("detalhes", "(" + 3 + " g, " + 2 + " l)");
        pedidosItemSubitem.add(pedido3);
        adapter.notifyDataSetChanged();

        Map<String, String> pedido4 = new HashMap<String, String>(2);
        pedido4.put("id", "Pedido #" + 4);
        pedido4.put("detalhes", "(" + 600 + " g, " + 24 + " l)");
        pedidosItemSubitem.add(pedido4);
        adapter.notifyDataSetChanged();

        Map<String, String> pedido5 = new HashMap<String, String>(2);
        pedido5.put("id", "Pedido #" + 5);
        pedido5.put("detalhes", "(" + 100 + " g, " + 2 + " l)");
        pedidosItemSubitem.add(pedido5);
        adapter.notifyDataSetChanged();*/

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


        // Action para calcular rota.
        FloatingActionButton fabCriarRota = (FloatingActionButton) findViewById(R.id.calcularRota);
        fabCriarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PedidosActivity.this, RotasActivity.class);
                ArrayList<String> pedidosStr = new ArrayList<String>(pedidos.values());
                if(pedidosStr.size() < 1) {

                    Toast toast = Toast.makeText(PedidosActivity.this, "Nenhum pedido encontrado. Utilize a câmera para adicionar um novo pedido.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                intent.putStringArrayListExtra("pedidos", pedidosStr);
                PedidosActivity.this.startActivity(intent);

//                Intent intent = new Intent(PedidosActivity.this, MapsActivity.class);
//                intent.setPackage("com.google.android.apps.maps");
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }


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
                                pedidosItemSubitem.remove(position);
                                pedidos.remove(pedidoId);
                                adapter.notifyDataSetChanged();
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
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {

                // Supondo que o QR code do sistema web seja gerado no formato "id;latitude;longitude;peso;volume;".
                String conteudo = result.getContents();
                String[] valores = conteudo.split(";");

                int pedidoId = Integer.parseInt(valores[0]);
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

                Toast toast = Toast.makeText(this, "Pedido #" + pedidoId + " adicionado com sucesso!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}

