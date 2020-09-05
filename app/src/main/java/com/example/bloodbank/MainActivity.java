package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

     private TextInputLayout t1,t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.username1);
        t2=findViewById(R.id.pass1);

    }

    boolean val()
    {
        String st=t1.getEditText().getText().toString().trim();

         if(st.isEmpty())
         { t1.setError("fill can't be empty ");
            return false;
         }else {
              t1.setError(null);
              return true;
         }

    }

    public void con1(View view) {

         if(!val())
         { return ;
         }

        Toast.makeText(this,"done"+t1.getEditText().getText()+" /"+t2.getEditText().getText(),Toast.LENGTH_SHORT).show();

    }


}
