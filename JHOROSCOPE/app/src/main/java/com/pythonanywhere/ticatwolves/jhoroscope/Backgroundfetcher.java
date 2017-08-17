package com.pythonanywhere.ticatwolves.jhoroscope;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Backgroundfetcher extends Service {

    Fetchdetails c1;
    String url[] = {"http://horoscope-api.herokuapp.com/horoscope/today/",
            "http://horoscope-api.herokuapp.com/horoscope/week/",
            "http://horoscope-api.herokuapp.com/horoscope/month/",
            "http://horoscope-api.herokuapp.com/horoscope/year/"
    };
    String name[];
    int i=0;
    public Backgroundfetcher() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        name = getResources().getStringArray(R.array.list);

        for(String n: name){
            i=0;
            for (String u: url){
                c1 = new Fetchdetails(this,i,0);
                u = u + n;
                c1.execute(u);
                i=i+1;
            }
        }
    }
}

