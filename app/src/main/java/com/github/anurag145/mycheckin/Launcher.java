package com.github.anurag145.mycheckin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Launcher extends AppCompatActivity {
     private EditText editText[] = new EditText[2];
     private Button button;

     private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("Log",0);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        try {
            if (!(sharedPreferences.getString("Date", null).equals(formattedDate))) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Date", formattedDate);
                editor.putString("CheckIn",null);
                editor.putString("CheckOut",null);
                editor.apply();
            }
        }catch (NullPointerException e)
        {
            Log.e("Launcher: catch Block","First Login");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Date", formattedDate);
            editor.apply();
        }
        UserInfo.getSingleton().Date=formattedDate;
        sharedPreferences = getSharedPreferences("Credentials",0);
        UserInfo.getSingleton().Username = sharedPreferences.getString("Username",null);
        if(UserInfo.getSingleton().Username!=null)
        {

            UserInfo.getSingleton().UserID=sharedPreferences.getString("UserID",null);
            Intent intent=  new Intent(Launcher.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            setContentView(R.layout.activity_launcher);
            editText[0] = findViewById(R.id.name_edit);
            editText[1] = findViewById(R.id.empID);
            button = findViewById(R.id.submit);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserInfo.getSingleton().Username=editText[0].getText().toString();
                    UserInfo.getSingleton().UserID=editText[1].getText().toString();
                    if(UserInfo.getSingleton().Username.length()>0&&UserInfo.getSingleton().UserID.length()>0)
                    {

                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("Username",UserInfo.getSingleton().Username);
                        editor.putString("UserID",UserInfo.getSingleton().UserID);
                        editor.apply();
                        Intent intent=  new Intent(Launcher.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Information Incomplete",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
