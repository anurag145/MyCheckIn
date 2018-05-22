package com.github.anurag145.mycheckin;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.estimote.sdk.SystemRequirementsChecker;
import com.github.anurag145.mycheckin.estimote.BeaconID;
import com.github.anurag145.mycheckin.estimote.EstimoteCloudBeaconDetailsFactory;
import com.github.anurag145.mycheckin.estimote.ProximityContentManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
  private ProximityContentManager proximityContentManager;
  private DatabaseReference databaseReference;
  private WebView webView;
  private TextView textView;
  private ValueEventListener val;
  private Boolean bool=false;
  private RecyclerView mRecyclerView;
  private RecyclerView.LayoutManager  layoutManager;
  private CustomAdapter  customAdapter;
  private int l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);
        webView= findViewById(R.id.webview);
        webView.setVerticalScrollBarEnabled(false);
        webView.loadData(UserInfo.getSingleton().s,"text/html",null);
        databaseReference= FirebaseDatabase
                .getInstance()
                .getReference(UserInfo.getSingleton().Username+"-"+UserInfo.getSingleton().UserID);
              setValueEventListener();
        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 45290, 64760),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 50484, 16475),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1068, 47383)),
                new EstimoteCloudBeaconDetailsFactory());
        proximityContentManager.setListener(new ProximityContentManager.Listener() {
            @Override
            public void onContentChanged(Object content) {
                if(content!=null)
                {

                    Long ts= System.currentTimeMillis()/1000;
                    SharedPreferences sharedPreferences=getSharedPreferences("Log",0);
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    if(sharedPreferences.getString("CheckIn",null)==null)
                    {   databaseReference.child(UserInfo.getSingleton().Date).child("CheckIn").setValue(ts);
                        databaseReference.child(UserInfo.getSingleton().Date).child("CheckOut").setValue("NA");
                        editor.putString("CheckIn", ts.toString());
                        editor.apply();

                    }
                    else {
                        databaseReference.child(UserInfo.getSingleton().Date).child("CheckOut").setValue(ts);
                        editor.putString("CheckOut", ts.toString());
                        editor.apply();
                    }
                    textView.setText("If this takes longer than 5 seconds close the app and try again");
                    proximityContentManager.stopContentUpdates();
                }

            }
        });
    }
   public void setValueEventListener()
   {    try {
       val = new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.getValue() != null) {
                   l = 0;
                   ArrayList<HashMap<String, String>> arr = new ArrayList<>();
                   Log.e("OnChange", dataSnapshot.getValue().toString());

                   if (bool) {
                       textView.setText("Successful...!!");
                       webView.setVisibility(View.GONE);
                       for (DataSnapshot child : dataSnapshot.getChildren()) {
                           HashMap<String, String> hm = new HashMap<>();
                           hm.put("Date", child.getKey());

                          for(DataSnapshot child1: child.getChildren())
                           {
                                if(child1.getKey().equals("CheckOut"))
                                hm.put("CheckOut", child1.getValue().toString());
                                else
                               hm.put("CheckIn", child1.getValue().toString());



                           }
                           l++;
                           arr.add(hm);
                       }
                       setContentView(R.layout.success_view);
                       mRecyclerView = findViewById(R.id.recyclerView);
                       customAdapter = new CustomAdapter(arr, l);
                       layoutManager = new LinearLayoutManager(MainActivity.this);
                       mRecyclerView.setLayoutManager(layoutManager);
                       mRecyclerView.setAdapter(customAdapter);

                   } else
                       bool = true;
               } else
                   bool = true;
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
               textView.setText("Something isn't right try again...close the app");
           }
       };
       databaseReference.addValueEventListener(val);

   }catch (Exception e)
   {
       Log.e("set",e.getMessage());
   }
   }

    @Override
    protected void onResume() {
        super.onResume();



        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e("Permission","Denied");
        } else {


            proximityContentManager.startContentUpdates();


        }

    }



    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onPause() {
        super.onPause();
        proximityContentManager.stopContentUpdates();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        proximityContentManager.destroy();

    }

}
