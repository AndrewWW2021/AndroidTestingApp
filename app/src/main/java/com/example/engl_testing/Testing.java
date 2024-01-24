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
import java.util.Random;
//
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Testing extends Activity{

    ImageView Zast;
    Drawable drawable;
    private TextView mTimer;
    ImageView b_inbox;
    ImageView b_outbox;
    ImageView b_jump;

    ImageView b_task;
    ImageView b_play;

    ImageView From_to;

    CountDownTimer countDownTimer;

    RecyclerView recyclerView;
    private static RecyclerAdapter recyclerAdapter;
    static ArrayList moveList;
    //
    RecyclerView recyclerViewFrom;
    RecyclerView recyclerViewTo;
    RecyclerAdapter recyclerAdapterElem;
    ArrayList moveListFrom;
    ArrayList moveListTo;

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Data");

    public static void test() {
        moveList.add("mov/Пересылка_данных");
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.testing_l);

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

        this.b_jump = findViewById(R.id.b_jump);
        b_jump.setImageDrawable(getResources().getDrawable(R.drawable.jump));

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



        moveList.add("InBox/ввод");
        moveList.add("OutBox/вывод");

        moveListFrom.add("A/-");
        moveListFrom.add("B/-");
        moveListFrom.add("C/-");
        moveListFrom.add("D/-");
        moveListFrom.add("R_mov/m");
        moveListFrom.add("A/-");
        moveListFrom.add("B/-");
        moveListFrom.add("C/-");
        moveListFrom.add("D/-");


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference("p");
        //final ref = FirebaseDatabase.instance.ref();
        //dbd = FirebaseDatabase.getInstance().getReference().child("cook");
        //DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Data").child("cook");

        //this.test();

        //кнопки-команды
        b_inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveList.add("InBox/ввод");
                recyclerAdapter.notifyDataSetChanged();

                Random r = new Random();
                int i1 = r.nextInt(100 - 1) + 1;
                DatabaseReference ref = db.getReference("points" + i1); // Key
                ref.setValue(1); // Value

                DatabaseReference ref1 = db.getReference("time" + i1); // Key
                ref1.setValue(5); // Value

            }
        });
        b_outbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveList.add("OutBox/вывод");
                recyclerAdapter.notifyDataSetChanged();

                //DatabaseReference ref_p = db.getReference();

                //Datab
                //ref_p.getRoot();
                //ref_p.get();
                getdata();

                //Toast.makeText(getBaseContext(), "1",Toast.LENGTH_LONG).show();
            }
        });
        b_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveList.add("Jump from/переход c");
                moveList.add("Jump to/переход на");
                recyclerAdapter.notifyDataSetChanged();
            }
        });
        // задание
        b_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countDownTimer.cancel();

                AlertDialog.Builder builder = new AlertDialog.Builder(Testing.this);
                builder.setTitle("Уровень: Занятая почта")
                        .setMessage("Задача: возьмите каждую вещь из ВВОДА и отправьте ее на ВЫВОД.")

                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();
                //countDownTimer.start();
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



        // проверка уровня

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerAdapter.notifyDataSetChanged();
                String[] moveListArray = (String[]) moveList.toArray(new String[moveList.size()]);
                if(moveList.size() > 3){
                    if(

                            (moveListArray[0].contains("Jump to") && // 1 возможное решение
                                    moveListArray[1].contains("InBox") &&
                                    moveListArray[2].contains("OutBox") &&
                                    moveListArray[3].contains("Jump from") &&
                                    moveList.size() == 4) ||
                                    (moveListArray[0].contains("InBox") && // 2 возможное решение
                                            moveListArray[1].contains("OutBox") &&
                                            moveListArray[2].contains("InBox") &&
                                            moveListArray[3].contains("OutBox") &&
                                            moveListArray[4].contains("InBox") &&
                                            moveListArray[5].contains("OutBox") &&
                                            moveListArray[6].contains("InBox") &&
                                            moveListArray[7].contains("OutBox") &&
                                            moveList.size() == 8)

                    ){

                        Toast.makeText(getBaseContext(),"Complite!",Toast.LENGTH_LONG).show();
                        countDownTimer.cancel();
                        mTimer.setText("-");

                        //
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // код через 4 секунды

                                AlertDialog.Builder builder = new AlertDialog.Builder(Testing.this);
                                builder.setTitle("Уровень пройден")
                                        .setMessage("Следущий уровень: Копир.")
                                        //.setMessage("Неправильно: " + Integer.toString(mistake))
                                        .setCancelable(false)
                                        .setNegativeButton("Next",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                        Intent intent = new Intent(Testing.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        }, 4000);

                    }
                    else {
                        Toast.makeText(getBaseContext(),"Попробуйте еще ...",Toast.LENGTH_LONG).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Testing.this);
                builder.setTitle("Время на исходе!")
                        .setMessage("Попробуйте пройти уровень заново или выберете другой ")
                        //.setMessage("Неправильно: " + Integer.toString(mistake))
                        .setCancelable(false)
                        .setNegativeButton("В главное меню",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        Intent intent = new Intent(Testing.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        }

                .start();

    } // end on Create



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


                Toast.makeText(Testing.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(Testing.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //

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
                case ItemTouchHelper.LEFT:
                    moveList.remove(pos);
                    recyclerAdapter.notifyItemRemoved(pos);

                    recyclerAdapter.notifyDataSetChanged(); // слишком резко - мб убрть
                    break;
            }
        }
    }; // d&d end



}

