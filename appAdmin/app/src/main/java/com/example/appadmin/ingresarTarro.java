package com.example.appadmin;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ingresarTarro extends AppCompatActivity {

    private EditText eIdentificacion, eNombreT, eContenido, eImagen, eUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_tarro);

        eIdentificacion = (EditText) findViewById(R.id.eIndetificacion);
        eNombreT = (EditText) findViewById(R.id.eNombreT);
        eContenido = (EditText) findViewById(R.id.eContenido);
        eImagen = (EditText) findViewById(R.id.eImagen); //Por ahora solo lo manejamos como un txt(reducir complexity) despues almacenaremos una img
        eUbicacion = (EditText) findViewById(R.id.eUbicacion); //Despues haremos la conexion con mapBox

    }


}
