package com.gabrieleuancruz.collegejobapp.Adapters;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Gabriel Euan Cruz on 06/04/2018.
 */

public class VolleyAdapter {

    private static VolleyAdapter instanciaVolley;
    private RequestQueue request;
    private static Context contexto;

    private VolleyAdapter(Context context) {
        contexto = context;
        request = getRequestQueue();
    }


    public static synchronized VolleyAdapter getInstanciaVolley(Context context) {
        if (instanciaVolley == null) {
            instanciaVolley = new VolleyAdapter(context);
        }

        return instanciaVolley;
    }

    public RequestQueue getRequestQueue() {
        if (request == null) {
            request = Volley.newRequestQueue(contexto.getApplicationContext());
        }

        return request;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
