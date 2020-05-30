package com.yucatancorp.examenandroid2020good.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.yucatancorp.examenandroid2020good.R;
import com.yucatancorp.examenandroid2020good.consumoWebService.DatosDeEntrada;
import com.yucatancorp.examenandroid2020good.consumoWebService.DatosDeSalida;
import com.yucatancorp.examenandroid2020good.consumoWebService.getDataFromWebService;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class descargaarchivos extends AppCompatActivity {

    private String baseUrl, downloadUrl, resultado;
    private ArrayList<LatLng> ubicaciones;
    private Button verEnMapaUbicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargaarchivos);

        verEnMapaUbicaciones = findViewById(R.id.buttonVerEnMapaUbicaciones);

        ubicaciones = new ArrayList<>();
        baseUrl = "https://apisls.upaxdev.com/";
        downloadUrl = "https://upx-sls-cargainicial.s3.amazonaws.com/dev/cargaInicial_flat_20200522181609_89602.zip";

        cargarInformacion(baseUrl);

        DescargarArchivoAsyncTask descargarArchivoAsyncTask = new DescargarArchivoAsyncTask();

        try {
            resultado = descargarArchivoAsyncTask.execute(downloadUrl).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        }

        verEnMapaUbicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(descargaarchivos.this, InstruccionUno.class);
                Bundle args = new Bundle();
                args.putSerializable("UBICACIONES", ubicaciones);
                intent.putExtra("BUNDLE", args);
                startActivity(intent);
            }
        });
    }

    private void cargarInformacion(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        getDataFromWebService datosRecibidos = retrofit.create(getDataFromWebService.class);

        Call<DatosDeSalida> call = datosRecibidos.enviarDatos(89602, "dev", "android");
        call.enqueue(new Callback<DatosDeSalida>() {
            @Override
            public void onResponse(Call<DatosDeSalida> call, Response<DatosDeSalida> response) {

                if(response.isSuccessful()){
                    Log.i("response", "Exitosa");
                } else {
                    Log.i("response", "!Exitosa");
                    Log.i("response", response.toString());
                }
            }

            @Override
            public void onFailure(Call<DatosDeSalida> call, Throwable t) {
                Log.i("response", "noResponse");
            }
        });
    }

    public class DescargarArchivoAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            int count;

            String resultadoObtenido = "";
            URL url;

            try {
                url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();

                InputStream inputStream = new BufferedInputStream(url.openStream());
                OutputStream outputStream = new FileOutputStream("/sdcard/archivoRecibido.zip");

                byte data[] = new byte[1024];

                while ((count = inputStream.read(data)) != -1){

                    outputStream.write(data, 0, count);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            unpackZip("/sdcard/", "archivoRecibido.zip");
            ReadFile();

            return resultadoObtenido;
        }
    }


    private boolean unpackZip(String path, String zipname) {
        InputStream is;
        ZipInputStream zis;
        try
        {
            String filename;
            is = new FileInputStream(path + zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null)
            {
                filename = ze.getName();

                // Need to create directories if not exists, or
                // it will generate an Exception...
                if (ze.isDirectory()) {
                    File fmd = new File(path + filename);
                    fmd.mkdirs();
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(path + filename);

                while ((count = zis.read(buffer)) != -1)
                {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void ReadFile (){

        try {
            File archivo = new File( "/sdcard/cargaInicial_flat_20200522181609_89602.txt");
            FileInputStream stream = new FileInputStream(archivo);
            String jsonString = null;
            try {
                FileChannel fileChannel = stream.getChannel();
                MappedByteBuffer bb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

                jsonString = Charset.defaultCharset().decode(bb).toString();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally {
                stream.close();
            }

            JSONObject jsonObject = new JSONObject(jsonString);

            String datos = jsonObject.getString("CARGA_INICIAL");
            jsonObject = new JSONObject(datos);
            JSONArray jsonArray = jsonObject.getJSONArray("UBICACIONES");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);
                ubicaciones.add(new LatLng(Double.valueOf(c.getString("FNLATITUD")), Double.valueOf(c.getString("FNLONGITUD"))));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
