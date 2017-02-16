package com.mcc.madokdrobyo.maps;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.mcc.madokdrobyo.constants.MapConsts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetDataFromUrl {

    public static String getDirectionsUrl(LatLng origin,LatLng dest){

        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        String url = MapConsts.BASE_URL_MAPS_DIRECTION + MapConsts.MAPS_DATA_TYPE + "?" + parameters;

        return url;
    }

    public static String getDataFromUrl(String strUrl) throws IOException {

        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;

        try{
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();
            br.close();

        }catch(Exception e){
            Log.d("Maps", "Exception while downloading url" + e.toString());
        }
        finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}

