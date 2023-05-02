package com.example.rasppiintercom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void getCarPos(View view){
        EditText editText1 = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editTextFlask);
        EditText editText3 = (EditText) findViewById(R.id.editTextTag);
        String ip = editText1.getText().toString();
        String flask = editText2.getText().toString();
        String tag = editText3.getText().toString();
        boolean ip_ad = Pattern.matches("(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}",ip);
        if(ip_ad==false) {
            Snackbar mySnackbar = Snackbar.make(view, "Not an IP address", BaseTransientBottomBar.LENGTH_LONG);
            mySnackbar.show();
        }
        else {
            sendAndRequestResponse(view,ip,flask,tag);
        }
    }
    private void sendAndRequestResponse(View view, String ip, String flask, String tag) {

        //RequestQueue initialized
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = "http://"+ip+":"+flask+"/get_pos/"+tag;

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.e(" result",(response.toString()));
                            float x = Math.abs(Float.parseFloat(response.getString("x")))*615;
                            float y = Math.abs(Float.parseFloat(response.getString("y")))*1500;
                            ImageView iv = (ImageView) findViewById(R.id.imageView);
                            iv.setTranslationX(x);
                            iv.setTranslationY(y);
                            iv.setVisibility(View.VISIBLE);
                        } catch (JSONException as)
                        {
                            System.out.println("TEST");
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(getRequest);
    }
}