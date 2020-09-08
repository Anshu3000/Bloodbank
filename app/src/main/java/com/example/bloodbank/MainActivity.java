package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.net.Mysingleton;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

     private TextInputLayout t1,t2;
        String st,st1;
       AlertDialog.Builder al;
      AlertDialog al1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.username1);
        t2=findViewById(R.id.pass1);
       al=new AlertDialog.Builder(this);
    }

    boolean val()
    {
         st=t1.getEditText().getText().toString().trim();
          st1=t2.getEditText().getText().toString().trim();

         if(st.isEmpty())
         { t1.setError("fill can't be empty ");
            return false;
         }else if(st1.isEmpty()) {

             t2.setError("fill can't be empty ");
             return false ;
         }else {
                 t1.setError(null);
                  t2.setError(null);
                 return true;
             }

    }

    public void con1(View view) {

         if(!val())
         { return ;
         }

        Toast.makeText(this,"done"+t1.getEditText().getText()+" /"+t2.getEditText().getText(),Toast.LENGTH_SHORT).show();


        StringRequest str=new StringRequest(Request.Method.POST, "https://anshu1234.000webhostapp.com/logi.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
//                          al=new AlertDialog.Builder(getApplicationContext());
                        al.setMessage(response.toString()).setTitle("RESULT");
                        al1=al.create();
                        al1.show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast ts1=Toast.makeText(getApplicationContext(),"ERROR ...",Toast.LENGTH_SHORT);
                 ts1.setGravity(Gravity.CENTER,0,0);
                 ts1.show();
                Log.d("key1","errror ");
                 error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> mp=new HashMap<String, String>();
                mp.put("USER1",st);
                mp.put("PASS1",st1);
                return mp;
            }
        };

        Mysingleton.getInsta(this).addRequest(str);

    }


    public void sign1(View view) {
       startActivity(new Intent(MainActivity.this,Signupf.class));
      }
}
