package com.example.shopsinfinifyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      button = (Button) findViewById(R.id.button2);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openFirstPay();
          }
      });

    }
    public void openFirstPay(){
        Intent intent = new Intent(this, FirstPay.class);
        startActivity(intent);
    }

}