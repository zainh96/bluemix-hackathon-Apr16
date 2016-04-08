package com.rbc.rbcbudgets;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BluemixDownloader extends AsyncTask<String, Boolean, Boolean>{
    private static String mURL = "TODO";
    private DownloadListener listener;

    public interface DownloadListener{
        void downloadSucceeded(ArrayList<BudgetTarget> list);
        void downloadFailed();
    }

    public void setDownloadListener(DownloadListener listener){
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        JSONArray array = doDownload();
        ArrayList<BudgetTarget> list = new ArrayList<>();

        if(array == null){
            listener.downloadFailed();
            return false;
        }

        // parse data
        try{
            int length = array.length();
            for(int i = 0; i < length; i++){
                BudgetTarget target = new BudgetTarget();
                JSONObject object = array.getJSONObject(i);

                target.setId(object.getInt("id"));
                target.setGoal(object.getString("name"));
                target.setTargetSaveGoal(object.getDouble("value"));
                target.setSaved(object.getDouble("saved"));
                target.setPercentage(object.getDouble("percent"));
                target.setBecause(object.getString("reason"));

                // date
                final long unixTimeStamp = object.getLong("date");
                Calendar date = new GregorianCalendar();
                date.setTimeInMillis(unixTimeStamp);

                target.setTargetYear(date.get(Calendar.YEAR));
                target.setTargetMonth(date.get(Calendar.MONTH) - 1);
                target.setTargetDayOfMonth(date.get(Calendar.DAY_OF_MONTH));

                list.add(target);
            }
            listener.downloadSucceeded(list);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return true;
    }

    private JSONArray doDownload(){
        String json = "{}";
        InputStream is = null;
        try {
            URL requesturl = new URL(mURL);
            URLConnection connection = requesturl.openConnection();
            is = connection.getInputStream();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return null;
    }
}
