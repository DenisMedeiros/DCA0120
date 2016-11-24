package ga.digentre.mobile.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import ga.digentre.mobile.R;

public class RotasActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final double RESTAURANTE_LATITUDE = -5.8428903;
    public static final double RESTAURANTE_LONGITUDE = -35.1974639;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent intent = getIntent();
        mMap = googleMap;

        LatLng pontoRestaurante = new LatLng(RESTAURANTE_LATITUDE, RESTAURANTE_LONGITUDE);
        mMap.addMarker(new MarkerOptions().position(pontoRestaurante).title("Restaurante"));

        ArrayList<String> pedidos = intent.getStringArrayListExtra("pedidos");

        for(String pedido: pedidos) {
            String[] valores = pedido.split(";");

            int pedidoId = Integer.parseInt(valores[0]);
            double latitude = Double.parseDouble(valores[1]);
            double longitude = Double.parseDouble(valores[2]);
            float peso = Float.parseFloat(valores[3]);
            float volume = Float.parseFloat(valores[4]);

            mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Pedido #" + pedidoId));

        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(ponto));

/*        ArrayList<Integer> ids = intent.getIntegerArrayListExtra("ids");
        for(Integer id : ids) {
            double latitude = intent.getDoubleExtra("latitude" + id, 0);
            double longitude = intent.getDoubleExtra("longitude" + id, 0);

            // Add a marker in Sydney and move the camera
            ponto = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(ponto).title("Pedido #" + id));

        }*/

        //mMap.moveCamera(CameraUpdateFactory.newLatLng());

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pontoRestaurante, 12.0f));

    }
}
