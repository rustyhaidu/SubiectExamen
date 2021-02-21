package com.example.subiectexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.subiectexamen.adaptor.UserAdaptor;
import com.example.subiectexamen.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private UserAdaptor userAdaptor;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        String stringUrl = "https://run.mocky.io/v3/84769bc9-5c57-403f-b897-6fc0e50b7fd8";

        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        GetJsonUser getJsonUser = new GetJsonUser();
        getJsonUser.execute(url);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    public class GetJsonUser extends AsyncTask<URL, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(URL... url) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url[0].openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();

                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);

                String linie;
                StringBuilder continut = new StringBuilder();

                while ((linie = br.readLine()) != null) {
                    continut.append(linie);
                }

                String continutFinal = continut.toString();

                try {
                    JSONArray jsonArray = new JSONArray(continutFinal);

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int time = jsonObject.getInt("time");
                        String password = jsonObject.getString("password");
                        String username = jsonObject.getString("username");
                        String gen = jsonObject.getString("gen");
                        String functie = jsonObject.getString("functie");

                        User user = new User(username, password, time, gen, functie);
                        users.add(user);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("JSONUSER", continutFinal);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            UserAdaptor userAdaptor = new UserAdaptor(getApplicationContext(), R.layout.item_row, users);
            listView.setAdapter(userAdaptor);
        }
    }


}