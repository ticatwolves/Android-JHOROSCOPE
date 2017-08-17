package com.pythonanywhere.ticatwolves.jhoroscope;


import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_APPEND;


/**
 * A simple {@link Fragment} subclass.
 */
public class Monthly extends Fragment {
    static TextView zdetail;
    String name;
    String month = "http://horoscope-api.herokuapp.com/horoscope/month/";
    ConnectivityManager myconnManager;
    NetworkInfo myinfo;
    Fetchdetails c1;

    public Monthly(String name) {
        this.name = name;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        zdetail = (TextView)getActivity().findViewById(R.id.zodiacmdetails);
        zdetail.setText("Month");

        myconnManager = (ConnectivityManager)getActivity().getSystemService(CONNECTIVITY_SERVICE);
        myinfo = myconnManager.getActiveNetworkInfo();

        /*if(myinfo!=null && myinfo.isConnected()){
            //c1 = new Fetchdetails(getContext(),2,1);
            //month = month + name;
            //c1.execute(month);
        }
        else {*/
            SharedPreferences sh1 = getActivity().getSharedPreferences("Zodiacoffline",MODE_APPEND);
            String s = sh1.getString(name+"m","");
            zdetail.setText(s.toString());
        //}
    }
}
