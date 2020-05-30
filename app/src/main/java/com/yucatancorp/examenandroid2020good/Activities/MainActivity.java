package com.yucatancorp.examenandroid2020good.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yucatancorp.examenandroid2020good.R;

public class MainActivity extends AppCompatActivity {

    private Button irAInstruccionUno, irAConsumoWebService, irAInstruccionDos, irAInstruccionTres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        irAInstruccionUno = findViewById(R.id.buttonInstruccionUno);
        irAConsumoWebService = findViewById(R.id.buttonConsumoWebService);
        irAInstruccionDos = findViewById(R.id.buttonIrAInstruccionDos);
        irAInstruccionTres = findViewById(R.id.buttonIrAInstruccionTres);

        irAInstruccionUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InstruccionUno.class));
            }
        });

        irAConsumoWebService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, descargaarchivos.class));
            }
        });

        irAInstruccionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InstruccionDos.class));
            }
        });

        irAInstruccionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InstruccionTres.class));
            }
        });
    }
}
