package com.example.engl_testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;


public class MainActivity extends Activity {

    ImageView Zast;
    Drawable drawable;
    Button Group;
    Button Level1;
    Button Level2;
    Button Testing;
    Button Admin;
    //Button Time;
    public static boolean BackCh = true;

    public static String passStr = "";
    public static String nameStr = "";

    // Идентификатор уведомления .
    private static final int NOTIFY_ID = 101;

    // Идентификатор канала
    private static String CHANNEL_ID = "way";
    static boolean activeM = false;

    //new
    //public static int level = 0;
    DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onStart() {
        super.onStart();
        activeM = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.Group = findViewById(R.id.menu1);

        //new
        this.Level1 = findViewById(R.id.menu2);
        this.Level2 = findViewById(R.id.menu3);
        this.Testing = findViewById(R.id.menu4);
        this.Admin = findViewById(R.id.menu5);

        this.Zast = findViewById(R.id.Zast);
        drawable = getResources().getDrawable(R.drawable.z2);
        Zast.setImageDrawable(drawable);
        // первая кнопка
        Group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTimingActivity.level = 0;
                Intent intent = new Intent(MainActivity.this, ExoActivity.class);

                startActivity(intent);
            }
        });
        // new вторая кнопка
        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTimingActivity.level = 1;
                Intent intent = new Intent(MainActivity.this, SetTimingActivity.class);

                startActivity(intent);
            }
        });
        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SetTimingActivity.level = 2;
//                Intent intent = new Intent(MainActivity.this, SetTimingActivity.class);
//
//                startActivity(intent);
                dbr.child("TestAccess").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            //Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            Toast.makeText(getBaseContext(), String.valueOf(task.getResult().getValue()),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        Testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SetTimingActivity.level = 9999;
                //Intent intent = new Intent(MainActivity.this, SetTimingActivity.class);

                //startActivity(intent);
//                dbr.child("TestAccess").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        Boolean testAcs = (Boolean) dataSnapshot.getValue();
//                        if(testAcs){
//                            Intent intent = new Intent(MainActivity.this, Testing.class);
//                            startActivity(intent);
//                        } else {
//                            Toast.makeText(getBaseContext(), "Тест не доступен",Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        System.out.println("The read failed: " + databaseError.getCode());
//                    }
//                });

                //
                dbr.child("TestAccess").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            //Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            //Toast.makeText(getBaseContext(), String.valueOf(task.getResult().getValue()),Toast.LENGTH_LONG).show();
                            Boolean testAcs = (Boolean) task.getResult().getValue();
                        if(testAcs && BackCh){
                            //Intent intent = new Intent(MainActivity.this, Testing.class);
                            //startActivity(intent);

                            AlertDialog.Builder name = new AlertDialog.Builder(MainActivity.this);
                            name.setTitle("Введите Фамилию и имя для авторизации");
                            final EditText nameEd = new EditText(MainActivity.this);
                            nameEd.setInputType(InputType.TYPE_CLASS_TEXT);
                            name.setView(nameEd);

                            name.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    nameStr = nameEd.getText().toString();
                                        Intent intent = new Intent(MainActivity.this, Testing.class);

                                        startActivity(intent);

                                }
                            });

                            name.show();

                        } else {
                            Toast.makeText(getBaseContext(), "Тест не доступен",Toast.LENGTH_LONG).show();
                        }
                        }
                    }
                });
            }
        });
        Admin.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

//                NotificationChannel channel=new NotificationChannel("channel1",
//                        "hello",
//                        NotificationManager.IMPORTANCE_HIGH);
//                NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                manager.createNotificationChannel(channel);
//
//                //Creating the notification object
//                NotificationCompat.Builder notification=new NotificationCompat.Builder(getBaseContext());
//                //notification.setAutoCancel(true);
//                notification.setContentTitle("Hi this is a notification");
//                notification.setContentText("Hello you");
//                notification.setSmallIcon(R.drawable.ic_launcher_foreground);
//
//                //make the notification manager to issue a notification on the notification's channel
//                manager.notify(12,notification.build());

                if (passStr.equals("Admin")){
                    Intent intentt = new Intent(MainActivity.this, Admin.class);
                    startActivity(intentt);
                } else {
                    //
                    AlertDialog.Builder pass = new AlertDialog.Builder(MainActivity.this);
                    pass.setTitle("Введите пароль");
                    final EditText passEd = new EditText(MainActivity.this);
                    passEd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pass.setView(passEd);

                    pass.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            passStr = passEd.getText().toString();
                            if(passStr.equals("Admin")){
                                Intent intent = new Intent(MainActivity.this, Admin.class);

                                startActivity(intent);
                            } else {
                                Toast.makeText(getBaseContext(), "Не правильный пароль",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    pass.show();
                }

            }
        });

        dbr.child("TestStatistic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String U = String.valueOf(dataSnapshot.getValue());
                if(activeM && dataSnapshot.getValue() != null){
                    Toast.makeText(getBaseContext(), U,Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
    // new db
    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String value = snapshot.getValue(String.class);


                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}