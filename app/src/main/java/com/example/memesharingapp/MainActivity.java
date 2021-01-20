package com.example.memesharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity {
   //TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onLoad();
    }

      public void onLoad(){
          RequestQueue queue = Volley.newRequestQueue(this);
          String url = "https://meme-api.herokuapp.com/gimme";

          JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                  (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                      @Override
                      public void onResponse(JSONObject response) {
                          try {
                              /*ImageView imageView = (ImageView) findViewById(R.id.my_image_view);

                              Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);*/
                              ImageView imageView = (ImageView) findViewById(R.id.laugh);
                              String url2 = (String) response.get("url");
                              Glide.with(getApplicationContext()).load(url2).into(imageView);

                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                         // textView.setText("Response: " + response.toString());
                      }
                  }, new Response.ErrorListener() {

                      @Override
                      public void onErrorResponse(VolleyError error) {
                          // TODO: Handle error

                      }
                  });

// Access the RequestQueue through your singleton class.
          queue.add(jsonObjectRequest);

                }
    public void shareMeme(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is crazy meme to share");
        sendIntent.setType("image/jpeg");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

    }

    public void nextMeme(View view) {
        onLoad();
    }
}