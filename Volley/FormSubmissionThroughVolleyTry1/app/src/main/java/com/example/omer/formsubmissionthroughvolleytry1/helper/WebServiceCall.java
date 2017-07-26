package com.example.omer.formsubmissionthroughvolleytry1.helper;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.omer.formsubmissionthroughvolleytry1.Models.Doctor;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by OMER on 7/26/2017.
 */

public class WebServiceCall {
    private static final String TAG = WebServiceCall.class.getSimpleName();

    private static final String POST    =   "post";

    public static void AddDoctor(final Doctor   doctor, RequestQueue    requestQueue){
        List<String>    subURLs = new ArrayList<>();
        subURLs.add(POST);
        subURLs.add("doctor");
        URL url =   ConfigApi.BuildURL(subURLs,null);
        Log.d(TAG, "AddDoctor: URL :["+url+"]");

        StringRequest   postRequest =   new StringRequest(Request.Method.POST, ConfigApi.BuildURL(subURLs, null).toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: response : [" + response + "]");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: error  :   ["+error+"]");
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > requestParamsPair   =   new HashMap<>();
                requestParamsPair.put("code",doctor.getCode());
                requestParamsPair.put("name",doctor.getName());
                return requestParamsPair;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        postRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(postRequest);
    }

}
