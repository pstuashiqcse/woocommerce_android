package com.mcc.madokdrobyo.netWork;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public abstract class BaseNetWork extends AsyncTask< Void, Void, String > {

    private Context mContext;
    private String requestedUrl;
    private String requestMethod;

    private HashMap<String, String> requestParams;

    public BaseNetWork(Context mContext){
        this.mContext = mContext;
    }

    protected void setRequestParams(HashMap<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    protected void setRequestedUrl(String requestedUrl, String requestMethod) {
        this.requestedUrl = requestedUrl;
        this.requestMethod = requestMethod;
    }



    @Override
    protected String doInBackground(Void... params) {
        if (new SystemInformation(mContext).isConnected()){
            return sendRequest(requestParams);
        }
        else return null;
    }

    private String sendRequest(HashMap<String, String> params) {

        try {
            URL url= new URL(requestedUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod(requestMethod);
            urlConnection.setDoOutput(true);

            if (params != null){

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                writer.write(getPostDataString(params));

                writer.flush();
                writer.close();
                outputStream.close();
            }
            return getResponseInString(urlConnection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private String getResponseInString(HttpURLConnection urlConnection) {
        StringBuilder response = new StringBuilder();

        try {
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }


    private String getPostDataString(HashMap<String, String> requestParams) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();
        boolean isFirst = true;

        for (Map.Entry<String, String>entry:requestParams.entrySet()) {

            if (isFirst)
                isFirst = false;
            else result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
