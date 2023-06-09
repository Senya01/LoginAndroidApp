package com.example.getloginpassapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GetDataAsyncTask extends AsyncTask<String, Void, String> {
    public AsyncResponse delegate;

    public GetDataAsyncTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    public interface AsyncResponse {
        void processFinish(String output);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String login = strings[0];
            String password = strings[1];
            String url_link = strings[2];

            String data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(login, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL(url_link);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

            writer.write(data);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                break;
            }

            return stringBuilder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null && !result.equals("")) {
            delegate.processFinish(result);
        }
    }
}
