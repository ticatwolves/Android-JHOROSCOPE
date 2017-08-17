package com.pythonanywhere.ticatwolves.jhoroscope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    RecyclerView zodiclist;
    String zodiaclist1[],zodiaclist2[],zodiaclist3[];
    int imagelist1[] = {R.drawable.aries,R.drawable.taurus,R.drawable.gemini,R.drawable.cancer};
    int imagelist2[] = {R.drawable.leo,R.drawable.virgo,R.drawable.libra,R.drawable.scorpio};
    int imagelist3[] = {R.drawable.saggitarius,R.drawable.capricon,R.drawable.aquarius,R.drawable.pisces};

    private InterstitialAd mInterstitialAd;

    ZodiacListAdaptor adaptor;

    //App ID for JHororscope  ca-app-pub-5759830031625110~6682047755


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zodiclist = (RecyclerView) findViewById(R.id.zodiaclists);

        zodiaclist1 = getResources().getStringArray(R.array.list_one);
        zodiaclist2 = getResources().getStringArray(R.array.list_two);
        zodiaclist3 = getResources().getStringArray(R.array.list_three);

        adaptor = new ZodiacListAdaptor(this,zodiaclist1,zodiaclist2,zodiaclist3,imagelist1,imagelist2,imagelist3);

        Intent i1 = new Intent(this,Backgroundfetcher.class);
        startService(i1);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5759830031625110/6610907325");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        zodiclist.setAdapter(adaptor);
        zodiclist.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
}
