package com.nanodegree.alse.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    InterstitialAd mInterstitialAd;
    ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //MobileAds.initialize(getContext(), "ca-app-pub-3940256099942544~3347511713");
       AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        requestNewAd();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewAd();
                tellJoke(getView());
            }
        });

        Button tellJoke = (Button)root.findViewById(R.id.tellJoke_button);
        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();

                }
                else {
                    tellJoke(v);
                }
            }
        });

        progressBar = (ProgressBar)root.findViewById(R.id.progress_bar);
        progressBar.setVisibility(ProgressBar.GONE);
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        progressBar.setVisibility(ProgressBar.GONE);
    }

    public void tellJoke(View view){

        //Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        Log.v("Inside tellJoke","ada");
        progressBar.setVisibility(ProgressBar.VISIBLE);
        MyBackendAsyncTask task = new MyBackendAsyncTask();
        task.setListener((MainActivity)getActivity());
        task.execute(getActivity());
      //  new MyBackendAsyncTask().execute(new Pair<Context,String>(getActivity(),"Test123"));
      //  JokeWizard jokeWizard = new JokeWizard();
        //String joke = jokeWizard.getJokes();
        // Toast.makeText(this, jokeWizard.getJokes(), Toast.LENGTH_SHORT).show();

    }
    public void requestNewAd(){
        AdRequest adRequest = new AdRequest.Builder().
                addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mInterstitialAd.loadAd(adRequest);
    }
}
