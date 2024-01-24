package com.example.engl_testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level_1 extends Activity{

    ImageView Zast;
    Drawable drawable;
    private TextView mTimer;
    ImageView b_inbox;
    ImageView b_outbox;
    ImageView b_task;
    ImageView b_play;

    ImageView From_to;

    CountDownTimer countDownTimer;

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList moveList;
    //
    RecyclerView recyclerViewFrom;
    RecyclerView recyclerViewTo;
    RecyclerAdapter recyclerAdapterElem;
    ArrayList moveListFrom;
    ArrayList moveListTo;
    int ii = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lev_1);

        this.Zast = findViewById(R.id.Zast);
        drawable = getResources().getDrawable(R.drawable.z2);
        Zast.setImageDrawable(drawable);

        this.From_to = findViewById(R.id.from_to);
        drawable = getResources().getDrawable(R.drawable.fromto);
        From_to.setImageDrawable(drawable);
        //картинки-кнопки
        this.b_inbox = findViewById(R.id.b_inbox);
        b_inbox.setImageDrawable(getResources().getDrawable(R.drawable.inbox));

        this.b_outbox = findViewById(R.id.b_outbox);
        b_outbox.setImageDrawable(getResources().getDrawable(R.drawable.outbox));

        this.b_task = findViewById(R.id.b_task);
        b_task.setImageDrawable(getResources().getDrawable(R.drawable.task));

        this.b_play = findViewById(R.id.b_play);
        b_play.setImageDrawable(getResources().getDrawable(R.drawable.play));

        //new
        Intent intent = getIntent();

        // все для списка
        moveList =new ArrayList<>();
        recyclerView = findViewById(R.id.rv);
        recyclerAdapter = new RecyclerAdapter(moveList);
        recyclerView.setAdapter(recyclerAdapter);
        // from/to list's
        moveListTo =new ArrayList<>();
        recyclerViewTo = findViewById(R.id.rv_to);
        recyclerAdapterElem = new RecyclerAdapter(moveListTo);
        recyclerViewTo.setAdapter(recyclerAdapterElem);

        moveListFrom =new ArrayList<>();
        recyclerViewFrom = findViewById(R.id.rv_from);
        recyclerAdapterElem = new RecyclerAdapter(moveListFrom);
        recyclerViewFrom.setAdapter(recyclerAdapterElem);

//        moveList.add("InBox/ввод");
//        moveList.add("OutBox/вывод");

        moveListFrom.add("A/-");
        moveListFrom.add("B/-");
        //moveListFrom.add("C/-");
        //moveListFrom.add("D/-");

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        //кнопки-команды
        b_inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveList.add("InBox/ввод");
                recyclerAdapter.notifyDataSetChanged();
            }
        });
        b_outbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveList.add("OutBox/вывод");
                recyclerAdapter.notifyDataSetChanged();
            }
        });
        // начальное сообщение
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // код через 1 секунду
                Toast.makeText(getBaseContext(),"Нажмите иконку с файлом чтобы просмотреть задание",Toast.LENGTH_LONG).show();
            }
        }, 1000);
        // задание
        b_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countDownTimer.cancel();

                AlertDialog.Builder builder = new AlertDialog.Builder(Level_1.this);
                builder.setTitle("Уровень: Почта")
                        .setMessage("Задача: перетащите команды в эту область, чтобы создать программу.")

                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();
                //countDownTimer.start();
            }
        });

        // проверка уровня
        int t_next = Integer.parseInt(intent.getStringExtra("t"));
        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerAdapter.notifyDataSetChanged();
                String[] moveListArray = (String[]) moveList.toArray(new String[moveList.size()]);
                if(moveList.size() == 4){
                    if(

                            moveListArray[0].contains("InBox") &&
                            moveListArray[1].contains("OutBox") &&
                            moveListArray[2].contains("InBox") &&
                            moveListArray[3].contains("OutBox")


                    ){

                        Toast.makeText(getBaseContext(),"Complite!",Toast.LENGTH_LONG).show();
                        countDownTimer.cancel();
                        mTimer.setText("-");
                        //
                        for(int i =0; i < 2; i ++){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    moveListTo.add(moveListFrom.get(0));
                                    moveListFrom.remove(0);
                                    recyclerAdapterElem.notifyItemRemoved(0);
                                    //for(int i =0; i < 4; i ++)

                                }
                            }, 4000 + i*1000);
                        }
                        //
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // код через 4 секунды

                                AlertDialog.Builder builder = new AlertDialog.Builder(Level_1.this);
                                builder.setTitle("Уровень пройден")
                                        .setMessage("Следущий уровень: Занятая почта.")
                                        .setCancelable(false)
                                        .setNegativeButton("Next",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                        SetTimingActivity.level = 2;
                                                        Intent intent = new Intent(Level_1.this, SetTimingActivity.class);
                                                        //intent.putExtra("t", t_next);
                                                        startActivity(intent);
                                                    }
                                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        }, 6000);

                    }
                }

                else {
                    Toast.makeText(getBaseContext(),"Попробуйте еще ...",Toast.LENGTH_LONG).show();
                }
            }
        });

        //все по таймеру
        int t = Integer.parseInt(intent.getStringExtra("t"));

        mTimer = (TextView) findViewById(R.id.tv);

        countDownTimer = new CountDownTimer(t*1000 + 1000, 1000) {

            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            public void onTick(long millisUntilFinished) {
                mTimer.setText("Осталось: "
                        + millisUntilFinished / 1000);
            }

            //Задаем действия после завершения отсчета
            public void onFinish() {
                mTimer.setText("Время на исходе!");
                AlertDialog.Builder builder = new AlertDialog.Builder(Level_1.this);
                builder.setTitle("Время на исходе!")
                        .setMessage("Попробуйте пройти уровень заново или выберете другой ")
                        //.setMessage("Неправильно: " + Integer.toString(mistake))
                        .setCancelable(false)
                        .setNegativeButton("В главное меню",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        Intent intent = new Intent(Level_1.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        }

                .start();

    } // end on Create
    // D&d
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
            ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPos = viewHolder.getAdapterPosition();
            int toPos = target.getAdapterPosition();

            Collections.swap(moveList, fromPos, toPos);

            recyclerView.getAdapter().notifyItemMoved(fromPos, toPos);
            recyclerAdapter.notifyDataSetChanged(); // только по одному элементу - мб убрть
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int pos = viewHolder.getAdapterPosition();

            switch (direction){
                case ItemTouchHelper.RIGHT:
                    moveList.remove(pos);
                    recyclerAdapter.notifyItemRemoved(pos);

                    recyclerAdapter.notifyDataSetChanged(); // слишком резко - мб убрть
                    break;
            }
        }
    }; // d&d end


}
