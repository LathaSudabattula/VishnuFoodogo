package com.svecw.vishnufoodogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.svecw.vishnufoodogo.Modal.FoodItem;
import com.svecw.vishnufoodogo.Modal.Stall;
import com.svecw.vishnufoodogo.Modal.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StallMainHomeActivity extends AppCompatActivity implements ClickListener{
    TextView stallname;
    ImageView stallimage;
    TextView status, timings, rating;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    private RecyclerView recyclerView;
    private UserAdapterStall userAdapter;

    private List<FoodItem> mitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stall_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        stallname = findViewById(R.id.stallhomename);
        stallimage = findViewById(R.id.stallhomeimage);
        status = findViewById(R.id.stallhomestatus);
        timings = findViewById(R.id.stallhometimings);
        rating = findViewById(R.id.stallhomerating);

        recyclerView = findViewById(R.id.recyclerStallMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String stallid = getIntent().getStringExtra("id");

        reference = FirebaseDatabase.getInstance().getReference("Stalls").child(stallid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stallname.setText(dataSnapshot.child("name").getValue().toString());
                Glide.with(getApplicationContext()).load(dataSnapshot.child("imageURL").getValue()).into(stallimage);
                if (dataSnapshot.child("status").getValue().toString().equals("opened")) {
                    status.setText("opened");
                    status.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    status.setText("closed");
                    status.setTextColor(getResources().getColor(R.color.colorFilterBackground));
                }

                timings.setText("Timings: " + dataSnapshot.child("timings").getValue().toString());
                rating.setText(dataSnapshot.child("rating").getValue().toString() + " people liked..");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Stalls").child(stallid).child("fitems");
        mitems = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, FoodItem>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, FoodItem>>() {
                };
                Map<String, FoodItem> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                ArrayList<FoodItem> objectArrayList = new ArrayList<FoodItem>(objectHashMap.values());

                mitems = objectArrayList;

                userAdapter = new UserAdapterStall(getApplicationContext(), mitems);
                recyclerView.setAdapter(userAdapter);
                userAdapter.setClickListener(StallMainHomeActivity.this);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {
        String teamval = mitems.get(position).getFname();
        Intent intent = new Intent(getApplicationContext(), ItemDisplay.class);
        intent.putExtra("itemname", teamval);
        intent.putExtra("id", getIntent().getStringExtra("id"));
        startActivity(intent);

    }
}
