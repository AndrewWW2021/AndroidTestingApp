package com.example.engl_testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SetTimingActivity extends Activity {

    ImageView Zast;
    Drawable drawable;
    Button GoTask;
    EditText Time;
    public static int level = 0;
    public static int level_next = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set_timing);
        this.GoTask = findViewById(R.id.buttonStart);
        this.Zast = findViewById(R.id.Zast);
        this.Time = findViewById(R.id.timing);
        drawable = getResources().getDrawable(R.drawable.z2);
        Zast.setImageDrawable(drawable);


        GoTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(level == 0){
                    Intent intent = new Intent(SetTimingActivity.this, TimeActivity.class);
                    intent.putExtra("t", Time.getText().toString());
                    startActivity(intent);
                }
                if(level == 1){
                    Intent intent = new Intent(SetTimingActivity.this, Level_1.class);
                    intent.putExtra("t", Time.getText().toString());
                    startActivity(intent);
                }
                if(level == 2){
                    Intent intent = new Intent(SetTimingActivity.this, Level_2.class);
                    intent.putExtra("t", Time.getText().toString());
                    startActivity(intent);
                }
                if(level == 9999){
                    Intent intent = new Intent(SetTimingActivity.this, Testing.class);
                    intent.putExtra("t", Time.getText().toString());
                    startActivity(intent);
                }

            }
        });

    }
}
