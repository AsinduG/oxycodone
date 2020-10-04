package com.example.shopsinfinifyy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText txtname, txtemail, txtmessage;
    DatabaseReference dbRef;
    Feedback feed;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback");

        txtname = findViewById(R.id.ptxtname);
        txtemail = findViewById(R.id.ptxtemail);
        txtmessage = findViewById(R.id.ptxtMessage);

        button = findViewById(R.id.btnSubmit);


        feed = new Feedback();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");

                try {
                    if (TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtemail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtmessage.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a date", Toast.LENGTH_SHORT).show();


                    else {
                        //Take inputs form the user and assigning them to this instance (pay) of the Payment...
                        feed.setName(txtname.getText().toString().trim());
                        feed.setEmail(txtemail.getText().toString().trim());
                        feed.setMessage(txtmessage.getText().toString().trim());

                        //Insert in to the databse...
                        dbRef.child("feed1").setValue(feed);
                        //Feedback to the user via a Toast...
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }
                } catch (NumberFormatException e) {

                    Toast.makeText(getApplicationContext(), "Invalid Security Code", Toast.LENGTH_SHORT).show();

                }

            }


        });
    }

    private void clearControls() {
        txtname.setText("");
        txtemail.setText("");
        txtmessage.setText("");

    }
}
      //button = (Button) findViewById(R.id.fbtnSubmite);
      /*button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openFirstPay();
          }
      });

    }
    public void openFirstPay(){
        Intent intent = new Intent(this, FirstPay.class);
        startActivity(intent);*/


