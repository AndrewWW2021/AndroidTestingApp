package com.example.engl_testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TimeActivity extends Activity {

    ImageView Zast;
    Drawable drawable;
    private TextView mTimer;
    EditText Txt;
    TextView wrd;
    Button b1;
    String tr;
    int res;
    int mistake = 0;
    String mistakeStr = "";
    int i = 1;
    String p;
    public static int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_time);

        this.Zast = findViewById(R.id.Zast);
        drawable = getResources().getDrawable(R.drawable.z2);
        Zast.setImageDrawable(drawable);
        this.Txt = findViewById(R.id.transl);
        this.b1 = findViewById(R.id.buttonNext);
        this.wrd = findViewById(R.id.words);

        Intent intent = getIntent();

        int t = Integer.parseInt(intent.getStringExtra("t"));

        String[] words = {"задача/решение", "задача/решение", "задача/решение", "задача/решение", "задача/решение"
        , "задача/решение", "задача/решение"};
        //String[] separated = words[0].split("/");
        //Toast.makeText(getBaseContext(),separated[1],Toast.LENGTH_LONG).show();

        //начальное слово
        wrd.setText("Чему будет равно n после завершения цикла? \n n = 0; \n while n < 3: \n n = n + 1" );
        p = "он \n он";
        res = 0;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    String[] separated = words[i].split("/");
                    tr = Txt.getText().toString();
                    if (tr.equals(p)){
                        res++;
                        //Toast.makeText(getBaseContext(),"+",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getBaseContext(),"перевод: " + p,Toast.LENGTH_LONG).show();
                        if(!separated[0].equals("конец1")){
                            mistake++;
                            mistakeStr +=separated[0]+ "-" + separated[1] + "\n";
                        }

                    }
                    //String[] separated = words[i].split("/");
                    p = separated[1];
                    wrd.setText(separated[0]);
                    i++;
                    //
                    if(separated[0].equals("конец1")){
                        mTimer.setText("Слова на исходе!");
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimeActivity.this);
                        builder.setTitle("Слова на исходе!")
                                .setMessage("Ваш результат: " + Integer.toString(res) + " правильно" + "\n" +
                                        "Неправильно: " + Integer.toString(mistake) + "\n" + mistakeStr)
                                //.setMessage("Неправильно: " + Integer.toString(mistake))
                                .setCancelable(false)
                                .setNegativeButton("Вернуться в меню",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                Intent intent = new Intent(TimeActivity.this, MainActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    //

                    Txt.getText().clear();

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        });

        mTimer = (TextView) findViewById(R.id.tv);

        new CountDownTimer(t*1000 + 1000, 1000) {

            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            public void onTick(long millisUntilFinished) {
                mTimer.setText("Осталось: "
                        + millisUntilFinished / 1000);
            }
            //Задаем действия после завершения отсчета
            public void onFinish() {
                mTimer.setText("Время на исходе!");
                AlertDialog.Builder builder = new AlertDialog.Builder(TimeActivity.this);
                builder.setTitle("Время на исходе!")
                        .setMessage("Ваш результат: " + Integer.toString(res) + " правильно" + "\n" +
                                "Неправильно: " + Integer.toString(mistake) + "\n" + mistakeStr)
                        //.setMessage("Неправильно: " + Integer.toString(mistake))
                        .setCancelable(false)
                        .setNegativeButton("Вернуться в меню",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        Intent intent = new Intent(TimeActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
        .start();

    }
}
