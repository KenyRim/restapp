package com.kenyrim.appdevtest.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kenyrim.appdevtest.model.Model;
import com.kenyrim.appdevtest.listener.MyAsyncListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonAsync extends AsyncTask<Void, Integer, String> {

    private final MyAsyncListener mListener;
    private String jsonData, mApiUrl;


    public JsonAsync(String apiUrl, MyAsyncListener listener) {
        mListener = listener;
        mApiUrl = apiUrl;
    }

    protected void onPreExecute(){
    }

    protected String doInBackground(Void... params) {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(mApiUrl)
                    .build();
            Response responses = null;

            try {
                responses = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert responses != null;
            assert responses.body() != null;
            jsonData = responses.body().string();

        }catch (Exception e){
            Log.e("JSONException",Objects.requireNonNull(e.getMessage()));
        }

        return jsonData;

    }


    protected void onPostExecute(String items){
         if (mListener != null) {

             JsonParser parser = new JsonParser();
             JsonObject root = (JsonObject) parser.parse(items);

             Gson gson = new Gson();
             ArrayList<Model> catDogArr = gson.fromJson(root.get("data").getAsJsonArray(),
                     new TypeToken<ArrayList<Model>>() {
                     }.getType());

              mListener.onCompleted(catDogArr);
         } 
    }


}