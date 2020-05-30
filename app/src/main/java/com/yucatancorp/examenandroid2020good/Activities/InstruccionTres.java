package com.yucatancorp.examenandroid2020good.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yucatancorp.examenandroid2020good.BaseDeDatos.Empleado;
import com.yucatancorp.examenandroid2020good.BaseDeDatos.EmpleadoViewModel;
import com.yucatancorp.examenandroid2020good.R;

import java.util.ArrayList;
import java.util.List;

public class InstruccionTres extends AppCompatActivity {

    private EmpleadoViewModel empleadoViewModel;
    private ListView listViewEmpleados;
    private List<String> empleadosLista;
    private Observer observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruccion_tres);

        empleadosLista = new ArrayList<>();
        listViewEmpleados = findViewById(R.id.listViewEmpleados);

        observer = new Observer<List<Empleado>>() {
            @Override
            public void onChanged(List<Empleado> listData) {

                for (Empleado empleado : listData) {
                    empleadosLista.add("Nombre: " + empleado.getNombre() + "\n" +
                            "Puesto: " + empleado.getPuesto() + "\n" +
                            "Fecha de nacimiento" + empleado.getFechaNacimiento() + "\n");
                }
            }
        };

        Log.i("mensaje", "prueba1");
        empleadoViewModel = new ViewModelProvider(this).get(EmpleadoViewModel.class);
        empleadoViewModel.getAllEmpleados().observe(this, observer);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(InstruccionTres.this,
                android.R.layout.simple_list_item_1, empleadosLista);
        listViewEmpleados.setAdapter(arrayAdapter);
    }


}
