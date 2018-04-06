package com.gabrieleuancruz.collegejobapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;
import com.gabrieleuancruz.collegejobapp.Adapters.TareaAdapter;
import com.gabrieleuancruz.collegejobapp.Adapters.VolleyAdapter;
import com.gabrieleuancruz.collegejobapp.Classes.TareaClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class todastareasFragment extends Fragment implements Response.Listener<JSONArray>,Response.ErrorListener {

    RecyclerView rvTodasTareas;
    ArrayList<TareaClass> ListaTareas;
    JsonArrayRequest jsonArrayRequest;
    RequestQueue request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View Vista = inflater.inflate(R.layout.fragment_todastareas, container, false);
        ListaTareas = new ArrayList<>();
        rvTodasTareas = (RecyclerView) Vista.findViewById(R.id.rvTodasTareas);
        rvTodasTareas.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTodasTareas.setHasFixedSize(true);
        CargarWebService();
        return Vista;
    }

    private void CargarWebService() {
        String url = "http://elisandro-001-site1.itempurl.com/WsCollegeJob.asmx/TodasTareas";

        jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url,null,this,this);
        VolleyAdapter.getInstanciaVolley(getContext()).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        AlertDialog.Builder msjDialogo = new AlertDialog.Builder(getActivity());
        msjDialogo.setTitle("ERROR");
        msjDialogo.setMessage(error.toString());
        msjDialogo.show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
    }

    @Override
    public void onResponse(JSONArray response) {
        TareaClass Tareas = null;

        try {
            for (int i = 0; i < response.length(); i++) {
                Tareas = new TareaClass();
                JSONObject jsonObject = null;
                jsonObject = response.getJSONObject(i);

                Tareas.setTitulo(jsonObject.optString("Titulo"));
                Tareas.setDescripcion(jsonObject.optString("Descripcion"));
                ListaTareas.add(Tareas);
            }
            TareaAdapter adapter=new TareaAdapter(ListaTareas);
            rvTodasTareas.setAdapter(adapter);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
    }

}
