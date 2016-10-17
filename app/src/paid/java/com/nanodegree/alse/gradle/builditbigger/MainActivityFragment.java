package com.nanodegree.alse.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellJoke = (Button)root.findViewById(R.id.tellJoke_button);
        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke(v);
            }
        });


        return root;
    }

    public void tellJoke(View view){

        //Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        Log.v("Inside tellJoke","ada");
        new MyBackendAsyncTask().execute(new Pair<Context,String>(getActivity(),"Test123"));
      //  JokeWizard jokeWizard = new JokeWizard();
        //String joke = jokeWizard.getJokes();
        // Toast.makeText(this, jokeWizard.getJokes(), Toast.LENGTH_SHORT).show();

    }
}
