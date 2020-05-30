package com.yucatancorp.examenandroid2020good.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yucatancorp.examenandroid2020good.R;

public class InstruccionDos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruccion_dos);

        LinearLayout linearLayout = findViewById(R.id.layoutInstruccionDos);

        Button miBoton = new Button(this);
        miBoton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        miBoton.setText("Crear nuevo hilo");
        miBoton.setGravity(Gravity.CENTER_HORIZONTAL);

        llamarNotificacion();

        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llamarNotificacion();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                llamarNotificacion();
                            }
                        }, 10000);
                    }
                });

                thread.run();

            }
        });

        linearLayout.addView(miBoton);
    }

    public void llamarNotificacion(){

        Intent intent = new Intent(getApplicationContext(), InstruccionDos.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("El tiempo ha llegado")
                .setContentText("Se han cumplido los 10 segundos.")
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat, "Tiempo", pendingIntent)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
