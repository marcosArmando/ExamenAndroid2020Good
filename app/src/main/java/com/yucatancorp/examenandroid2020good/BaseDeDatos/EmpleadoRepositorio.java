package com.yucatancorp.examenandroid2020good.BaseDeDatos;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.yucatancorp.examenandroid2020good.BaseDeDatos.BaseDeDatos;

import java.util.List;

public class EmpleadoRepositorio {

    private EmpleadoDao empleadoDao;
    private LiveData<List<Empleado>> ListaEmpleados;

    public EmpleadoRepositorio(Application application){
        BaseDeDatos baseDeDatos = BaseDeDatos.getInstance(application);
        empleadoDao = baseDeDatos.empleadoDao();
        ListaEmpleados = empleadoDao.getAllEmpleados();
    }

    public LiveData<List<Empleado>> getAllEmpleados(){
        return ListaEmpleados;
    }

}