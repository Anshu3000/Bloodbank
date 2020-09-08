package com.example.bloodbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.net.Mysingleton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class Signupf extends AppCompatActivity {

    private TextInputLayout use1,pass12,pho1,address;
     private Spinner sp;
    private String s1,us,pas,adr,str1;
    private AlertDialog sign1;
    private AlertDialog.Builder signbud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupf);

        use1=findViewById(R.id.usernamesign);
        pass12=findViewById(R.id.userpasswo);
        address=findViewById(R.id.useraddress);
        pho1=findViewById(R.id.userphone);
        sp=findViewById(R.id.bloodgroup1);
         signbud=new AlertDialog.Builder(this);

        ArrayAdapter<CharSequence> ar= ArrayAdapter.createFromResource(this,R.array.bloodgroup,android.R.layout.simple_spinner_item);
         ar.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
         sp.setAdapter(ar);
         sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 s1=adapterView.getItemAtPosition(i).toString();
                 Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {
                 s1=adapterView.getItemAtPosition(0).toString();
             }
         });
    }



    public void sign123(View view) {

             us=use1.getEditText().getText().toString().trim();
             pas=pass12.getEditText().getText().toString().trim();
             adr=address.getEditText().getText().toString().trim();
              str1=pho1.getEditText().getText().toString().trim();

        if(chck(us,pas,adr,str1)){
            use1.setError(null);
            pass12.setError(null);
            address.setError(null);
            pho1.setError(null);

            StringRequest str4=new StringRequest(Request.Method.POST, "https://anshu1234.000webhostapp.com/regis.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    signbud.setTitle("Result");
                     signbud.setMessage("THANKYOU FOR  SIGNIN ");
                     signbud.setCancelable(false);
                     signbud.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             finish();
                         }
                     });
                     sign1=signbud.create();
                    sign1.show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                     Map<String,String> mp2=new HashMap<>();
                       mp2.put("username",us);
                       mp2.put("phone1",str1);
                       mp2.put("addr",adr);
                       mp2.put("Password",pas);
                        mp2.put("blood1",s1);
                    return mp2;
                }
            };

            Mysingleton.getInsta(this).addRequest(str4);

        }else{

            Toast.makeText(getApplicationContext(),us+"/"+pas+"/"+adr+"/"+str1,Toast.LENGTH_SHORT).show();

        }

    }

    boolean chck(String us1,String pas1,String adr1, String str12){

        if(us1.isEmpty()){
            use1.setError(" Fill the username..."+us1);
            return false;
        }

        if(pas1.isEmpty()){
            pass12.setError(" Fill  the password...");
            return false;
        }

        if(adr1.isEmpty()){
            address.setError("Fill the address...");
            return false;
        }

        if(str12.isEmpty()){
            pho1.setError("Fill the Phone number...");
            return false;
        }

      return true;
    }

}
