package com.example.omer.formsubmissionthroughvolleytry1.helper;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by OMER on 7/26/2017.
 */

public class ConfigApi {

    private static final String TAG = ConfigApi.class.getSimpleName();

    public static final String  BASE_URL    =   "http://192.168.0.106:3000";

    public static URL BuildURL(List<String> subURLList, Map<String,String> queryParams){
        URL url =   null;
        Uri.Builder base    =   Uri.parse(BASE_URL).buildUpon();
        try {
            if (subURLList != null){
                for(String subUrl:  subURLList){
                    base    =   Uri.parse(base.toString()).buildUpon().appendPath(subUrl);
                }
            }

        }   catch (NullPointerException e){
            e.printStackTrace();
        }
        try {
            if (queryParams !=  null){
                for (Map.Entry<String,String>  entry :   queryParams.entrySet()){
                    base    =   Uri.parse(base.toString()).buildUpon().appendQueryParameter(entry.getKey(),entry.getValue());
                }
            }
        }catch (NullPointerException    e){
            e.printStackTrace();
        }
        try {
            url =   new URL(base.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
