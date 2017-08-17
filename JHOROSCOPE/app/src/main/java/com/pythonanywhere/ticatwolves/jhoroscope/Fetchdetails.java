package com.pythonanywhere.ticatwolves.jhoroscope;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ticat on 3/7/17.
 */

public class Fetchdetails extends AsyncTask<String, String,JSONObject> {

    Context ctx;
    int type,show;

    public Fetchdetails(Context ct,int t,int s){
        ctx = ct;
        type = t;
        show = s;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        String str = strings[0];
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return new JSONObject(stringBuffer.toString());
        }
        catch(Exception ex)
        {
            Log.e("App", "yourDataTask", ex);
            return null;
        }
        finally
        {
            if(bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(JSONObject response)
    {
        String name,data,time;
        SharedPreferences sh;
        sh = ctx.getSharedPreferences("Zodiacoffline", MODE_PRIVATE);
        SharedPreferences.Editor myeditor;
        if(response != null)
        {
            try {
                switch (type){
                    case 0: Log.e("App", "Success: " + response.getString("sunsign") );
                        Log.e("App", "Success: " + response.getString("horoscope") );
                        name = response.getString("sunsign");
                        data = response.getString("horoscope");
                        data = data.replace("\\n", "").replace("\\r", "").replace("['", "").replace("']", "");
                        if(show==1){
                            Daily.zdetail.setText(data);
                        }
                        myeditor = sh.edit();
                        myeditor.putString(name+"d",data);
                        myeditor.commit();
                        break;
                    case 1: Log.e("App", "Success: " + response.getString("sunsign") );
                        Log.e("App", "Success: " + response.getString("horoscope") );
                        name = response.getString("sunsign");
                        data = response.getString("horoscope");
                        time = response.getString("week");
                        data = data.replace("\\n", "").replace("\\r", "").replace("['", "").replace("']", "");
                        if(show==1){
                            Weekly.zdetail.setText(data);
                        }
                        myeditor = sh.edit();
                        myeditor.putString(name+"w",data);
                        myeditor.commit();
                        break;
                    case 2: Log.e("App", "Success: " + response.getString("sunsign") );
                        Log.e("App", "Success: " + response.getString("horoscope") );
                        name = response.getString("sunsign");
                        data = response.getString("horoscope");
                        time = response.getString("month");
                        data = data.replace("\\n", "").replace("\\r", "").replace("['", "").replace("']", "");
                        if(show==1){
                            Monthly.zdetail.setText(data);
                        }
                        myeditor = sh.edit();
                        myeditor.putString(name+"m",data);
                        myeditor.commit();
                        break;
                    case 3: Log.e("App", "Success: " + response.getString("sunsign") );
                        Log.e("App", "Success: " + response.getString("horoscope") );
                        name = response.getString("sunsign");
                        data = response.getString("horoscope");
                        time = response.getString("year");
                        data = data.replace("\\n", "").replace("\\r", "").replace("['", "").replace("']", "");
                        if(show==1){
                            Yearly.zdetail.setText(data);
                        }
                        myeditor = sh.edit();
                        myeditor.putString(name+"y",data);
                        myeditor.commit();
                        break;
                }
            } catch (JSONException ex) {
                Log.e("App", "Failure", ex);
            }
        }

    }

}
