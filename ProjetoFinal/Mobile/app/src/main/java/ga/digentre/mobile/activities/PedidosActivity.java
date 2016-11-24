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
import java.util.List;
import java.util.Map;

import ga.digentre.mobile.R;

public class PedidosActivity extends AppCompatActivity {

    final Context context = this;
    ListView listaPedidos;
    SimpleAdapter adapter;
    List<Map<String, String>> pedidos = new ArrayList<Map<String, String>>();
    public static Map<Integer, double[]> coordenadas = new HashMap<Integer, double[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        setTitle("DigEntrega - Pedidos");
        Intent intent = getIntent();
        String cpf = intent.getStringExtra("cpf");

        listaPedidos = (ListView) this.findViewById(R.id.listaPedidos);
        View pedidosView = findViewById(R.id.includePedidos);


        adapter = new SimpleAdapter(this, pedidos,
                android.R.layout.simple_list_item_2,
                new String[] {"id", "detalhes"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});

        // Temporario


        Map<String, String> pedido1 = new HashMap<String, String>(2);
        pedido1.put("id", "Pedido #" + 1);
        pedido1.put("detalhes", "(" + 6000 + " g, " + 15 + " l)");
        pedidos.add(pedido1);
        adapter.notifyDataSetChanged();
        coordenadas.put(1, new double[]{-5.8459685,-35.2036081});

        Map<String, String> pedido2 = new HashMap<String, String>(2);
        pedido2.put("id", "Pedido #" + 2);
        pedido2.put("detalhes", "(" + 3000 + " g, " + 2 + " l)");
        pedidos.add(pedido2);
        adapter.notifyDataSetChanged();
        coordenadas.put(2, new double[]{-5.8325928,-35.2090004});

        Map<String, String> pedido3 = new HashMap<String, String>(2);
        pedido3.put("id", "Pedido #" + 3);
        pedido3.put("detalhes", "(" + 3 + " g, " + 2 + " l)");
        pedidos.add(pedido3);
        adapter.notifyDataSetChanged();
        coordenadas.put(3, new double[]{-5.8375942,-35.2233547});

        Map<String, String> pedido4 = new HashMap<String, String>(2);
        pedido4.put("id", "Pedido #" + 4);
        pedido4.put("detalhes", "(" + 600 + " g, " + 24 + " l)");
        pedidos.add(pedido4);
        adapter.notifyDataSetChanged();
        coordenadas.put(4, new double[]{-5.8418456,-35.2169791});

        Map<String, String> pedido5 = new HashMap<String, String>(2);
        pedido5.put("id", "Pedido #" + 5);
        pedido5.put("detalhes", "(" + 100 + " g, " + 2 + " l)");
        pedidos.add(pedido5);
        adapter.notifyDataSetChanged();
        coordenadas.put(5, new double[]{-5.8163808,-35.2200636});


        // Assign adapter to ListView
        listaPedidos.setAdapter(adapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabAdicionarPedido = (FloatingActionButton) findViewById(R.id.adicionarPedido);
        fabAdicionarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
                IntentIntegrator integrator = new IntentIntegrator(PedidosActivity.this);
                integrator.initiateScan();
            }
        });

        FloatingActionButton fabCriarRota = (FloatingActionButton) findViewById(R.id.calcularRota);
        fabCriarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PedidosActivity.this, MapsActivity.class);
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    // Get the results:
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
                float latitude = Float.parseFloat(valores[1]);
                float longitude = Float.parseFloat(valores[2]);
                float peso = Float.parseFloat(valores[3]);
                float volume = Float.parseFloat(valores[4]);

                Map<String, String> pedido = new HashMap<String, String>(2);
                pedido.put("id", "Pedido #" + pedidoId);
                pedido.put("detalhes", "(" + peso + " kg, " + volume + " l)");
                pedidos.add(pedido);
                adapter.notifyDataSetChanged();

                Toast.makeText(this, "Pedido adicionado com sucesso!" + conteudo, Toast.LENGTH_LONG).show();


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}

