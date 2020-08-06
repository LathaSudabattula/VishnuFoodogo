package com.svecw.vishnufoodogo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ItemDisplay extends AppCompatActivity {

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    ImageView itemimage;
    TextView stallname, itemname, cost, quantity, rating, status;
    Switch favoriteswitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_display);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        stallname = findViewById(R.id.itemhomestallname);
        itemname = findViewById(R.id.itemhomeitemname);
        itemimage = findViewById(R.id.itemhomeimage);
        status = findViewById(R.id.itemhomestatus);
        rating = findViewById(R.id.itemhomerating);
        cost = findViewById(R.id.itemhomecost);
        quantity = findViewById(R.id.itemhomequantity);
        favoriteswitch = findViewById(R.id.favoriteSwitch);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String stallid = getIntent().getStringExtra("id");


        reference = FirebaseDatabase.getInstance().getReference("Stalls").child(stallid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sname = dataSnapshot.child("name").getValue().toString();
                Log.v("pname", sname);
                stallname.setText(sname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final String itemnames = getIntent().getStringExtra("itemname");
        reference = FirebaseDatabase.getInstance().getReference("Stalls").child(stallid).child("fitems").child(itemnames);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Glide.with(getApplicationContext()).load(dataSnapshot.child("imageURL").getValue()).into(itemimage);
                itemname.setText(dataSnapshot.child("fname").getValue().toString());
                cost.setText("₹ " + dataSnapshot.child("cost").getValue().toString());
                quantity.setText(dataSnapshot.child("quantity").getValue().toString());
                rating.setText(dataSnapshot.child("frating").getValue().toString() + " people liked..");
                if (dataSnapshot.child("fstatus").getValue().toString().equals("available")) {
                    status.setText("available");
                    status.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    status.setText("not available");
                    status.setTextColor(getResources().getColor(R.color.colorFilterBackground));
                    rating.setLeftTopRightBottom(60, 10, 30, 0);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



    }
}
