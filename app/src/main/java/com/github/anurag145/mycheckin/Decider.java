package com.github.anurag145.mycheckin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Decider extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider);
        Button log =findViewById(R.id.logs);
        Button check=findViewById(R.id.check);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(Decider.this,MainActivity.class);
                intent.putExtra("Type",0);
                startActivity(intent);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(Decider.this,MainActivity.class);
                intent.putExtra("Type",1);
                startActivity(intent);
            }
        });
    }




    }

