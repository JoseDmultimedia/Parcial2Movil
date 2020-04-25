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
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class ingresarUsuario extends AppCompatActivity {

    private TextView eNombre, eCodigo, eFacultad, ePrograma;
    private RadioButton rEstudiante, rProfesor;
    private Button insertar,  mostrarbtn;
    private List listaEstudiante;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_usuario);

        eNombre = (EditText) findViewById(R.id.eName);
        eCodigo = (EditText) findViewById(R.id.eCodigo);
        eFacultad = (EditText) findViewById(R.id.eFacultad);
        ePrograma = (EditText) findViewById(R.id.ePrograma);

        rEstudiante = (RadioButton) findViewById(R.id.rEstudiante);
        rProfesor = (RadioButton) findViewById(R.id.rProfesor);

        insertar = (Button) findViewById(R.id.insertar);
        mostrarbtn = (Button) findViewById(R.id.mostrarbtn);

        ePrograma.setEnabled(false);
        eFacultad.setEnabled(false);

        listaEstudiante = new ArrayList();
        posicion = 0;

        /*
        Metodo onClik del boton insertar, este llama a la clase Insetar y ejecuta lo que esta dentro.
         */

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

        /*
        Metdo onClick del boton mostrar
         */
        mostrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Mostrar().execute();
            }
        });

    }

    /*
    Este es el metodo que controla la selecion de cajas de texto en el view
    con los radio buttons
     */

    public void onRadioBtn1(View view) {
        boolean marcado = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rEstudiante:
                if (marcado) {
                    ePrograma.setEnabled(true);
                    eFacultad.setEnabled(false);
                }
                break;

            case R.id.rProfesor:
                if (marcado) {
                    eFacultad.setEnabled(true);
                    ePrograma.setEnabled(false);
                }
                break;
        }

    }

    /*
    Codigo de insertar, con este se realiza la conexion y se indica que se realizaraa un post a traves del web service de php
    --------------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    private boolean insetar(){
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        HttpPost httppost;
        httpclient = new DefaultHttpClient();
        httppost = new HttpPost("http://192.168.0.4/insertar.php");

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
    /*
    Metodo mostrar
    -------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    public String mostrar(){
        String request = "";
        HttpClient httpclient;
        HttpPost httppost;
        httpclient = new DefaultHttpClient();
        httppost = new HttpPost("http://192.168.0.4/select.php");
        try {
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            request = httpclient.execute(httppost, responseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    /*
    Metodo que filtra los resultados
     */
    private boolean filtradoDatos(){
        listaEstudiante.clear();
        if(!mostrar().equalsIgnoreCase("")){
            String [] cargaDatos=mostrar().split("/");
            for(int i = 0; i < cargaDatos.length; i++){
                String datosEstudiante [] = cargaDatos[i].split("<br>");
                Estudiante estudiante = new Estudiante();
                estudiante.setNombreE(datosEstudiante[0]);
                estudiante.setCodigoE(datosEstudiante[1]);
                estudiante.setProgramaE(datosEstudiante[2]);
                listaEstudiante.add(estudiante);
            }
            return true;
        }
        return false;
    }
    /*
    Muestra la perssona almacenada como objeto de nuestro arraylist
     */
    private void mostrarEstudiante(final int posicion){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Estudiante estudiante = (Estudiante) listaEstudiante.get(posicion);
                eNombre.setText(estudiante.getNombreE());
                eCodigo.setText(estudiante.getCodigoE());
                ePrograma.setText(estudiante.getProgramaE());
            }
        });
    }

    /*
    Clase alojada en la clase de ingresar que basicamente llama al metodo de insertar y genera alertas
    para verificar si se ingreso o no los datos a la bd
     */

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

    class Mostrar extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            if(filtradoDatos()) mostrarEstudiante(posicion);
            return null;
        }
    }
}
