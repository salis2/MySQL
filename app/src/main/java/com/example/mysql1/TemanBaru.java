package com.example.mysql1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TemanBaru extends AppCompatActivity {
    private EditText tNama,tTelpon;
    private Button simpanBtn;
    String nm,tlp;
    int success;
    //BDController controller = new BDController(this);

    private static String url_insert = "http://10.0.2.2:8090/pam/tambahtm.php";
    private static final String TAG = TemanBaru.class.getSimpleName();
    private static final String TAG_SUCCES = "success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_baru);

        tNama = findViewById(R.id.ednama);
        tTelpon = findViewById(R.id.edtelp);
        simpanBtn = findViewById(R.id.simpanBtn);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tNama.getText().toString().equals("")||tTelpon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data Belum Lengkap !!",Toast.LENGTH_LONG).show();

                }else {
                    nm = tNama.getText().toString();
                    tlp = tTelpon.getText().toString();

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response : " + response.toString());
                            try {
                                JSONObject jobj = new JSONObject(response);
                                success = jobj.getInt(TAG_SUCCES);
                                if (success == 1) {
                                    Toast.makeText(TemanBaru.this, "Sukses Simpan Data", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(TemanBaru.this, "Gagal", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG,"Error : "+error.getMessage());
                            Toast.makeText(TemanBaru.this, "Gagal Simpan Data", Toast.LENGTH_LONG).show();
                        }
                }){
                        @Override
                        protected Map<String, String > getParams(){
                            Map<String,String> params = new HashMap<>();
                            params.put("nama",nm);
                            params.put("telpon",tlp);

                            return params;
                        }
                    };
                    requestQueue.add(strReq);
                }
            }
        });

    }
//    public void callHome(){
//        Intent intent = new Intent(TemanBaru.this,MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
}