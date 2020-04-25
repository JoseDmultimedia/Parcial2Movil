package com.example.appadmin;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



public class ingresarUsuario extends AppCompatActivity {

    private TextView eNombre, eCodigo, eFacultad, ePrograma;
    private RadioButton rEsstudiante, rProfesor;
    private Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_usuario);

        eNombre = (EditText) findViewById(R.id.eName);
        eCodigo = (EditText) findViewById(R.id.eCodigo);
        eFacultad = (EditText) findViewById(R.id.eFacultad);
        ePrograma = (EditText) findViewById(R.id.ePrograma);

        rEsstudiante = (RadioButton) findViewById(R.id.rEstudiante);
        rProfesor = (RadioButton) findViewById(R.id.rProfesor);

        insertar = (Button) findViewById(R.id.insertar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eNombre.getText().toString().trim().equalsIgnoreCase("")||
                !eCodigo.getText().toString().trim().equalsIgnoreCase("")||
                !ePrograma.getText().toString().trim().equalsIgnoreCase(""))
                    new Insertar(ingresarUsuario.this).execute();
                else
                    Toast.makeText(ingresarUsuario.this, "Hay informacion por rellenar", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean insetar(){
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        HttpPost httppost;
        httpclient = new DefaultHttpClient();
        httppost = new HttpPost("http://192.168.0.7/insertar.php");

        //Añadir Datos
        nameValuePairs = new ArrayList<NameValuePair>(3);
        nameValuePairs.add(new BasicNameValuePair("nombre",eNombre.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("idestudiante",eCodigo.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("programa",ePrograma.getText().toString().trim()));

        //----------------Prueba de conexion primero con estudiante, depues tocaria crear un ingresar para profesores, asociar con el radio button
        // ---------------que se ingresa
        //nameValuePairs.add(new BasicNameValuePair("facultad",eNombre.getText().toString().trim()));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    class Insertar extends AsyncTask<String, String, String>{

        private Activity context;

        Insertar(Activity context){
            this.context=context;
        }

        @Override
        protected String doInBackground(String... strings) {
        if (insetar())
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,"Estudiante Insertado Con Exito", Toast.LENGTH_LONG).show();
                    eNombre.setText("");
                    eCodigo.setText("");
                    ePrograma.setText("");
                }
            });
        else
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,"Estudiante no insertado", Toast.LENGTH_LONG).show();
                }
            });

            return null;
        }
    }

}
