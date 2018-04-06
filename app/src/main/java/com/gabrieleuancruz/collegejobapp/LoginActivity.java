package com.gabrieleuancruz.collegejobapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegistro;;
    EditText txtEmail, txtPassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        txtEmail = (EditText)findViewById(R.id.txtEmailL);
        txtPassword = (EditText)findViewById(R.id.txtContraseñaL);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread hilo = new Thread(){
                    @Override
                    public void run() {
                        final String resp = enviarpost(txtEmail.getText().toString(), txtPassword.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int con=OBJJson(resp);
                                if(con>0)
                                {
                                    String nombre ="";
                                    String ema ="";
                                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                                    JSONArray jsonArray= null;
                                    try {
                                        jsonArray = new JSONArray(resp);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject =jsonArray.getJSONObject(0);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        nombre = (jsonObject.getString("Nombre")).toString();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        ema= (jsonObject.getString("Email")).toString();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    intent.putExtra("nombreus",nombre);
                                    intent.putExtra("email",ema);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
                hilo.start();
            }
        });
    }

    public  String enviarpost(String Email,String Password) {
        URL url=null;
        String linea="";
        int respuesta=0;
        StringBuilder resul=null;
        try {

            url=new URL("http://elisandro-001-site1.itempurl.com/WsCollegeJob.asmx/Login?Usuario=" + Email + "&Contrasena=" + Password);
            HttpURLConnection conec=(HttpURLConnection)url.openConnection();
            respuesta=conec.getResponseCode();
            resul=new StringBuilder();
            if(respuesta==HttpURLConnection.HTTP_OK)
            {
                InputStream in= new BufferedInputStream(conec.getInputStream());
                BufferedReader reader= new BufferedReader(new InputStreamReader(in));

                while ((linea=reader.readLine()) !=null)
                {
                    resul.append(linea);
                }

            }
        }catch (Exception e)
        {
            Toast.makeText(LoginActivity.this, "No hay conexion a internet", Toast.LENGTH_SHORT ).show();
        }
        return  resul.toString();
    }

    public int OBJJson(String respu) {
        int respuesta=0;
        try{
            JSONArray jsonArray=new JSONArray(respu);
            if (jsonArray.length()>0)
            {
                respuesta=1;
            }

        }catch (Exception e)
        {

        }
        return  respuesta;
    }
}
