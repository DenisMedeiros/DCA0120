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
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import ga.digentre.mobile.R;

public class PedidosActivity extends AppCompatActivity {

    final Context context = this;
    ListView listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        setTitle("DigEntrega - Pedidos");
        Intent intent = getIntent();
        String cpf = intent.getStringExtra("cpf");

        listaPedidos = (ListView) this.findViewById(R.id.listaPedidos);
        View pedidosView = findViewById(R.id.includePedidos);
        pedidosView.setVisibility(View.VISIBLE);

        String[] values = new String[] {
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
                "Pedido #1",
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        listaPedidos.setAdapter(adapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
                //IntentIntegrator integrator = new IntentIntegrator(PedidosActivity.this);
                //integrator.initiateScan();

                new IntentIntegrator(PedidosActivity.this).initiateScan(); // `this` is the current Activity

            }
        });
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}

