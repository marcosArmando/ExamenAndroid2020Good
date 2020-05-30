package com.yucatancorp.examenandroid2020good.BaseDeDatos;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Empleado.class, version = 1)
public abstract class BaseDeDatos extends RoomDatabase {

    private static BaseDeDatos baseDeDatosInstancia;

    public abstract EmpleadoDao empleadoDao();

    public static synchronized BaseDeDatos getInstance(Context context){
        if(baseDeDatosInstancia == null){
            baseDeDatosInstancia = Room.databaseBuilder(context.getApplicationContext(),
                    BaseDeDatos.class, "empleados_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomCallBack)
                    .build();
        }

        return baseDeDatosInstancia;
    }

    private static RoomDatabase.Callback RoomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PersistirBaseDeDatos(baseDeDatosInstancia).execute();
            super.onCreate(db);
        }
    };

    public static class PersistirBaseDeDatos extends AsyncTask<Void, Void, Void>{

        private EmpleadoDao empleadoDao;

        public PersistirBaseDeDatos(BaseDeDatos baseDeDatos){
            empleadoDao = baseDeDatos.empleadoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            empleadoDao.insert(new Empleado("Miguel Cervantes", "08-Dic-1990", "Desarrollador"));
            empleadoDao.insert(new Empleado("Juan Morales", "03-Jul-1990", "Desarrollador"));
            empleadoDao.insert(new Empleado("Roberto Méndez", "14-Oct-1990", "Desarrollador"));
            empleadoDao.insert(new Empleado("Miguel Cuevas", "08-Dic-1990", "Desarrollador"));
            return null;
        }
    }

}
