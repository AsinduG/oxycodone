package com.example.firebasetest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BillingDetails extends AppCompatActivity {

    EditText name, address, postalcode, phone, email;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Billing bil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_details);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");


        name = findViewById(R.id.namef);
        address = findViewById(R.id.addressf);
        postalcode = findViewById(R.id.postalcodef);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.emailf);

        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnCancel);

        bil = new Billing();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Billing/");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("bil1")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Billing/bil1");
                            dbRef.removeValue();

                            Toast.makeText(getApplicationContext(), "Delete Data Successfully", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "Not data Delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Billing");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("bil1")) {
                            try {
                                bil.setName(name.getText().toString().trim());
                                bil.setPhone(phone.getText().toString().trim());
                                bil.setAddress(address.getText().toString().trim());
                                bil.setPostalcode(postalcode.getText().toString().trim());
                                bil.setEmail(email.getText().toString().trim());
                                dbRef = FirebaseDatabase.getInstance().getReference().child("Billing/bil1");
                                dbRef.setValue(bil);
                                clearControls();

                                //Feedback to the user vi a Toast...
                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "Not Update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Billing/bil1");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            name.setText(dataSnapshot.child("name").getValue().toString());
                            phone.setText(dataSnapshot.child("phone").getValue().toString());
                            address.setText(dataSnapshot.child("address").getValue().toString());
                            postalcode.setText(dataSnapshot.child("postalcode").getValue().toString());
                            email.setText(dataSnapshot.child("email").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        //btnsave.setOnClickListener(new View.OnClickListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Billing");

                try {
                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(phone.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a phone", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(address.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a address", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(postalcode.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Postal Code", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Email", Toast.LENGTH_SHORT).show();

                    else {
                        //Take inputs form the user and assigning them to this instance (pay) of the Payment...
                        bil.setName(name.getText().toString().trim());
                        bil.setPhone(phone.getText().toString().trim());
                        bil.setAddress(address.getText().toString().trim());
                        bil.setPostalcode(postalcode.getText().toString().trim());
                        bil.setEmail(email.getText().toString().trim());
                        //Insert in to the databse...
                        dbRef.child("bil1").setValue(bil);
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
        name.setText("");
        phone.setText("");
        address.setText("");
        postalcode.setText("");
        email.setText("");
    }
}



