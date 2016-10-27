package com.nanodegree.alse.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.nanodegree.gradle.backend.myApi.MyApi;

import java.io.IOException;


public class MyBackendAsyncTask extends AsyncTask<Context,Void,String> {
    private MyApi myAPIService = null;
    private Context context;
    TaskCompleteListener listener;

    public MyBackendAsyncTask setListener(TaskCompleteListener l){
        listener = l;
        return this;
    }


    //Backend task to fetch joke from GCE server
    @Override
    protected String doInBackground(Context... params) {
        if(myAPIService == null){
            //Connect to backend
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(),null)
                    .setRootUrl("https://builditbigger-6b5dd.appspot.com/_ah/api/");
            myAPIService = builder.build();
        }
        context = params[0];
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
        listener.onComplete(s);
       /* Intent intent = new Intent(context,DisplayActivity.class);
        intent.putExtra(DisplayActivity.JOKE_EXTRA,s);
        context.startActivity(intent);*/
    }

    public interface TaskCompleteListener{
        public void onComplete(String s);
    }

}

