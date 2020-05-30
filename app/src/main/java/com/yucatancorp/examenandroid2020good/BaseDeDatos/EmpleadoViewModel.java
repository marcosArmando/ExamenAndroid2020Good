package com.yucatancorp.examenandroid2020good.BaseDeDatos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yucatancorp.examenandroid2020good.BaseDeDatos.EmpleadoRepositorio;

import java.util.List;

public class EmpleadoViewModel extends AndroidViewModel {

    private EmpleadoRepositorio empleadoRepositorio;
    private LiveData<List<Empleado>> listaDeEmpleados;

    public EmpleadoViewModel(@NonNull Application application) {
        super(application);
        empleadoRepositorio = new EmpleadoRepositorio(application);
        listaDeEmpleados = empleadoRepositorio.getAllEmpleados();
    }

    public LiveData<List<Empleado>> getAllEmpleados(){
        return listaDeEmpleados;
    }
}
