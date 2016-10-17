package com.nanodegree.alse.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.nanodegree.alse.displayjoke.DisplayActivity;
import com.nanodegree.gradle.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by aharyadi on 10/14/16.
 */

public class MyBackendAsyncTask extends AsyncTask<Pair<Context,String>,Void,String> {
    private MyApi myAPIService = null;
    private Context context;
    TaskCompleteListener listener;

    public MyBackendAsyncTask setListener(TaskCompleteListener l){
        listener = l;
        return this;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myAPIService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(),null)
                    .setRootUrl("https://builditbigger-6b5dd.appspot.com/_ah/api/");
            myAPIService = builder.build();
        }
        context = params[0].first;
        String name = params[0].second;
        try{
            return myAPIService.getJoke().execute().getData();
        }
        catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        //Toast toast = Toast.makeText(context,s,Toast.LENGTH_LONG);
        //toast.show();
        Log.v("InsidePost",s);
       // listener.onComplete(s);
        Intent intent = new Intent(context,DisplayActivity.class);
        intent.putExtra(DisplayActivity.JOKE_EXTRA,s);
        context.startActivity(intent);
    }

    public interface TaskCompleteListener{
        public void onComplete(String s);
    }

}

