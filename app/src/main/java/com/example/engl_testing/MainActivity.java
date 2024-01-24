package com.example.engl_testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

    ImageView Zast;
    Drawable drawable;
    Button Group;
    Button Level1;
    Button Level2;
    Button Testing;
    //Button Time;

    //new
    //public static int level = 0;

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
                SetTimingActivity.level = 2;
                Intent intent = new Intent(MainActivity.this, SetTimingActivity.class);

                startActivity(intent);
            }
        });

        Testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTimingActivity.level = 9999;
                Intent intent = new Intent(MainActivity.this, SetTimingActivity.class);

                startActivity(intent);
            }
        });


    }
}