package com.yucatancorp.examenandroid2020good.Activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yucatancorp.examenandroid2020good.R;

import java.util.ArrayList;
import java.util.Random;

public class InstruccionUno extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText cajaTextoIU;
    private Button buttonIU;
    private double longitud, latitud;
    private ArrayList<LatLng> ubicaciones;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruccion_uno);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ubicaciones = (ArrayList<LatLng>) args.getSerializable("UBICACIONES");


        cajaTextoIU = findViewById(R.id.cajaTextoIU);
        buttonIU = findViewById(R.id.buttonIU);
        final MarkerOptions markerOptions = new MarkerOptions();

        buttonIU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LatLngCreada(String.valueOf(cajaTextoIU.getText())) != null) {
                    mMap.addMarker(markerOptions
                            .position(LatLngCreada(String.valueOf(cajaTextoIU.getText())))
                            .icon(BitmapDescriptorFactory.defaultMarker(new Random().nextFloat() * (300 - 0) + 0 )));

                } else {
                    cajaTextoIU.setError("Ingrese, por favor, datos válidos");
                    Toast.makeText(InstruccionUno.this, "ingrese Latitud y longitud válidas separelas por una coma", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private LatLng LatLngCreada(String entrada){

        String[] datos = entrada.trim().split(",");
        double longitud = Double.valueOf(datos[0]);
        double latitud = Double.valueOf(datos[1]);

        Toast.makeText(InstruccionUno.this, " " + longitud + " lat " + latitud, Toast.LENGTH_LONG).show();

        if(LatitudEsValida(latitud) && LongitudEsValida(longitud))
            return new LatLng(latitud, longitud);

        return null;
    }

    private boolean LongitudEsValida(double coordenada){

        if (180 >= coordenada && coordenada >= -180) {
            return true;
        }

        return false;
    }

    private boolean LatitudEsValida(double coordenada){

        if (85 >= coordenada && coordenada >= -85) {
            return true;
        }

        return false;
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
        mMap = googleMap;

        for(LatLng latLng : ubicaciones){

            MarkerOptions markerOptions = new MarkerOptions();
            mMap.addMarker(markerOptions.position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(new Random().nextFloat() * (300 - 0) + 0 )));

        }
        // Add a marker in Sydney and move the camera
    }
}
