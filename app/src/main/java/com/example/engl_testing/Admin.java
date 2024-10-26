package com.example.engl_testing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Admin extends Activity {

    EditText time;
    EditText task;
    EditText in;
    EditText out;
    ImageView add;
    Button getT;
    Button clearT;
    Drawable drawable;
    TextView Stat;

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    DatabaseReference dr = FirebaseDatabase.getInstance().getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.admin);


        this.time = findViewById(R.id.t_time);
        this.task = findViewById(R.id.t_task);
        this.in = findViewById(R.id.t_in);
        this.out = findViewById(R.id.t_out);

        this.add = findViewById(R.id.b_add);
        drawable = getResources().getDrawable(R.drawable.add);
        add.setImageDrawable(drawable);

        this.getT = findViewById(R.id.getTest);
        this.clearT = findViewById(R.id.clearTest);

        this.Stat = findViewById(R.id.textViewStat);

        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference("p");
        //final ref = FirebaseDatabase.instance.ref();
        //dbd = FirebaseDatabase.getInstance().getReference().child("cook");
        //DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Data").child("cook");

        // String p = editText.getText().toString();

        getT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Random r = new Random();
//                int i1 = r.nextInt(100 - 1) + 1;
//                DatabaseReference ref = db.getReference("points" + i1); // Key
//                ref.setValue(1); // Value
//
//                DatabaseReference ref1 = db.getReference("time" + i1); // Key
//                ref1.setValue(5); // Value
                int p1 = Integer.parseInt(time.getText().toString());
                String p2 = task.getText().toString();



                //dr.child("TestPart").child("Time").child("username").setValue("name");
                dr.child("TestAccess").setValue(true);
                dr.child("TestPart").child("Time").setValue(p1);
                dr.child("TestPart").child("Task").setValue(p2);

//                dr.child("TestPart").child("Check").child("ex1").setValue("прим1");
//                dr.child("TestPart").child("Check").child("ex2").setValue("прим2");
//                dr.child("TestPart").child("Check").child("ex3").setValue("прим3");

//                Map<String, Object> childUpdates = new HashMap<>();
//                childUpdates.put("TestAccess", true);
//                dr.updateChildren(childUpdates);
                //Testing.i_ex++;
                MainActivity.BackCh = true;

            }
        });
        //
        clearT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Testing.i_ex++;
                dr.child("TestPart").setValue(null);
                dr.child("TestAccess").setValue(false);
                Testing.i_ex = 0;

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Testing.i_ex++;
                String p9 = in.getText().toString() + "/" + out.getText().toString();
                dr.child("TestPart").child("Check").child("ex" + String.valueOf(Testing.i_ex)).setValue(p9);


            }
        });

        //Stat.setText(Testing.resForP);

    }
    // new db
    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String value = snapshot.getValue(String.class);


                Toast.makeText(Admin.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(Admin.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //
}
