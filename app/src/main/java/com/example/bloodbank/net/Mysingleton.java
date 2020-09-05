package com.example.bloodbank.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton {

    public static Mysingleton inst=null;
    public RequestQueue que;
    Context ctx;

     private  Mysingleton(Context ctx){
            this.ctx=ctx;
         que=getRequest1();
     }

    private RequestQueue getRequest1() {
         if(que==null)
         { return Volley.newRequestQueue(ctx.getApplicationContext());   }
         return  que;
    }

    public static  synchronized  Mysingleton getInsta(Context ct1){
         if(inst==null)
         {  inst= new Mysingleton(ct1);
         }
         return  inst;
    }

    public void addRequest(Request req){
         que.add(req);
     }

}
