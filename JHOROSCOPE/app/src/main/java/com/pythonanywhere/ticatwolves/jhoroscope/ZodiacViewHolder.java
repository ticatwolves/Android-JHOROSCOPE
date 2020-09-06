package com.pythonanywhere.ticatwolves.jhoroscope;

import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZodiacViewHolder extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    TextView zname;
    TextView zdate;
    static TextView zdetail;
    Fetchdetails c1;
    ImageView zimage;
    String sign;
    int imageid;

    ConnectivityManager myconnManager;
    NetworkInfo myinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiac_view_holder);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.myview);

        viewPager.setAdapter(new Pager(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        //tabLayout.setTabTextColors(R.color.white,R.color.white);
        tabLayout.setTabTextColors(Color.parseColor("#c8cbce"), Color.parseColor("#ffffff"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        Bundle b1 = getIntent().getExtras();
        sign = b1.getString("ZodiacName").toString();
        imageid = b1.getInt("zimage");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        zname = (TextView)findViewById(R.id.zodiacname);
        zname.setText(sign);

        zdate = (TextView)findViewById(R.id.zodiacdate);
        zdate.setText(dateFormat.format(date));

        zimage = (ImageView)findViewById(R.id.zodiacsign);
        zimage.setImageResource(imageid);

    }

    class Pager extends FragmentPagerAdapter {
        String ztime[] = {"Daily","Weekly","Monthly","Yearly"};

        public Pager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle b1 = getIntent().getExtras();
            sign = b1.getString("ZodiacName").toString();
            if(position==0){
                return new Daily(sign);
            }
            if(position==1){
                return new Weekly(sign);
            }
            if (position==2){
                return new Monthly(sign);
            }
            if(position==3){
                return new Yearly(sign);
            }
            return null;
        }

        @Override
        public int getCount() {
            return ztime.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return ztime[position];
        }
    }

}

