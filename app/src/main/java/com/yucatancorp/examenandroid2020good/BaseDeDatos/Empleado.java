package com.yucatancorp.examenandroid2020good.BaseDeDatos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "empleados_table")
public class Empleado {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;

    private String fechaNacimiento;

    private String puesto;

    public Empleado(String nombre, String fechaNacimiento, String puesto) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getPuesto() {
        return puesto;
    }

}
