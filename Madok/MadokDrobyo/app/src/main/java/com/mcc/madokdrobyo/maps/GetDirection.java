package com.mcc.madokdrobyo.maps;

import android.os.AsyncTask;
import android.util.Log;

import com.mcc.madokdrobyo.parser.RoutesJsonParser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class GetDirection {
    Listener listener;

    public GetDirection(Listener listener) {
       this.listener = listener;
    }

    public void startGettingDirections(String downloadUrl) {
        new DirectionsTask().execute(downloadUrl);
    }

    class DirectionsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            String data = null;

            try {
                data = GetDataFromUrl.getDataFromUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);
        }

        private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

            @Override
            protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

                JSONObject jObject;
                List<List<HashMap<String, String>>> routes = null;

                try {
                    jObject = new JSONObject(jsonData[0]);
                    RoutesJsonParser parser = new RoutesJsonParser();
                    routes = parser.parse(jObject);

                } catch (Exception e) {
                    listener.onFail();
                    e.printStackTrace();
                }
                return routes;
            }

            @Override
            protected void onPostExecute(List<List<HashMap<String, String>>> result) {
                super.onPostExecute(result);
                if (result != null && result.size() > 0)
                    listener.onSuccessfullRouteFetch(result);
                else
                    listener.onFail();
            }
        }
    }
}
