package com.example.resttest;

//import androidx.appcompat.app.AppCompatActivity;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;

//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;

//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HttpContext;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        Button bb = findViewById(R.id.button);
        bb.setEnabled(false);
        new LongRunningGetIO().execute();

    }

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {



        @Override
        protected String doInBackground(Void... params) {

            try {
                //HttpResponse response = httpClient.execute(httpGet, localContext);
                //HttpEntity entity = response.getEntity();
                //text = getASCIIContentFromEntity(entity);

                String myurlstr = "http://example.com/whatever/route/path/put/here";
                URL urlObj = new URL(myurlstr);
                HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
                InputStream in = urlConnection.getInputStream();

                StringBuffer out = new StringBuffer();
                int nn = 0;
                byte[] bb = new byte[4096];
                nn =  in.read(bb);

                if (nn > 0)
                    out.append(new String(bb,0, nn));

                return out.toString();

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
        }


        protected void onPostExecute(String results) {
            if (results!=null) {
                TextView tv = findViewById(R.id.textView);
                tv.setText(results);
            }


            Button b = findViewById(R.id.button);
            b.setEnabled(true);
        }


    }

}//Main
