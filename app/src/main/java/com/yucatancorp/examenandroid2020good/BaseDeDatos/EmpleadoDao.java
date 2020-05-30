package com.yucatancorp.examenandroid2020good.BaseDeDatos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmpleadoDao {

    @Insert
    void insert(Empleado empleado);

    @Query("SELECT * FROM empleados_table")
    LiveData<List<Empleado>> getAllEmpleados();
}
