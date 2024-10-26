package com.example.engl_testing;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
//
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Testing extends Activity{

    ImageView Zast;
    Drawable drawable;
    private TextView mTimer;
    ImageView b_inbox;
    ImageView b_outbox;
    ImageView b_jump;

    ImageView b_task;
    ImageView b_play;
    ImageView b_step;

    ImageView b_zap;
    ImageView b_var;

    EditText var;

    ImageView From_to;
    static boolean ch = false;
    static int chInt = -5;

    CountDownTimer countDownTimer;

    RecyclerView recyclerView;
    private static RecyclerAdapter recyclerAdapter;
    static ArrayList moveList;
    //
    RecyclerView recyclerViewFrom;
    RecyclerView recyclerViewTo;
    RecyclerAdapterElem recyclerAdapterElem;
    ArrayList moveListFrom;
    ArrayList moveListTo;

    Button T;
    Button T2;

    TextView build_out;
    EditText build_inp;

    static int ii = 0;
    String s = "";
    String sRes = "";
    int iter = 0;
    int metka = 0;
    String stdin = "";
    public static int i_ex = 0; //количество примеров для проверки
    int iVar = 0;
    String sVar = "";
    String[] sepVar;

    long setTime;
    public String setTimeS = "";

    int finalCh;
    int fail = 1;

    boolean whileCh;

    static boolean active = false;

    public static String resForP = "";


    TextView debg;
    // реги
    String EAXh = "00000000";
    String EAXs = "00000000";
    String EBXh = "00000000";
    String EBXs = "00000000";
    String ECXh = "00000000";
    String ECXs = "00000000";
    String EDXh = "00000000";
    String EDXs = "00000000";

    String AXh = "0000";
    String AXs = "0000";
    String BXh = "0000";
    String BXs = "0000";
    String CXh = "0000";
    String CXs = "0000";
    String DXh = "0000";
    String DXs = "0000";

    String AHh = "00";
    String AHs = "00";
    String BHh = "00";
    String BHs = "00";
    String CHh = "00";
    String CHs = "00";
    String DHh = "00";
    String DHs = "00";

    String ALh = "00";
    String ALs = "00";
    String BLh = "00";
    String BLs = "00";
    String CLh = "00";
    String CLs = "00";
    String DLh = "00";
    String DLs = "00";




    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public static void test(String ss) {
        //moveList.add("MOV/Пересылка данных");
        // функция добавления лексем
        if(!ch){
            moveList.add(ss);
            recyclerAdapter.notifyDataSetChanged();
            if(ss.contains("HelloEx")){
                moveList.clear();
                recyclerAdapter.notifyDataSetChanged();

                moveList.add("section .data/s");
                moveList.add("hello:     db 'Hello World',10/m");
                moveList.add("helloLen:  equ $-hello/m");
                moveList.add("section .text/s");
                moveList.add("global _start/s");
                moveList.add("_start:/s");
                moveList.add("mov eax,4/m");
                moveList.add("mov ebx,1/m");
                moveList.add("mov ecx,hello/m");
                moveList.add("mov edx,helloLen/m");
                moveList.add("int 80h/m");
                moveList.add("mov eax,1/m");
                moveList.add("mov ebx,0/m");
                moveList.add("int 80h/m");
            }
        }

        if(ch){
            String[] sep = ss.split("/");
            String sss = moveList.get(chInt).toString();
            //holder.el.setText(separated[0]);
            moveList.remove(chInt);
        //moveList.add(chInt, "New/Пересылка данных");
            String[] sep2 = sss.split("/");
            moveList.add(chInt, sep2[0] + sep[0] + "/" + sep2[1]);
        recyclerAdapter.notifyDataSetChanged();
        }
    }

    public static void test2(int ind) {
//
        if(chInt == ind || chInt==-5){
            ch = !ch;
        }
        chInt = ind;


    }

    public static String decimal2Hex(int d) {
        return String.format("%2s", Integer.toHexString(d)).toUpperCase().replace(' ', '0');
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    String[] spinnerStr  = { "Секции синтаксиса", "Команды", "Инструкции", "Регистры", "Переменные", "Готовые примеры", "Умнож"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.testing_l);

        this.Zast = findViewById(R.id.Zast);
        drawable = getResources().getDrawable(R.drawable.z2);
        Zast.setImageDrawable(drawable);

        //картинки-кнопки

        this.b_task = findViewById(R.id.b_task);
        b_task.setImageDrawable(getResources().getDrawable(R.drawable.task));

        this.b_play = findViewById(R.id.b_play);
        b_play.setImageDrawable(getResources().getDrawable(R.drawable.play));

        this.b_step = findViewById(R.id.b_step);
        b_step.setImageDrawable(getResources().getDrawable(R.drawable.step));

        this.b_zap = findViewById(R.id.b_zap);
        b_zap.setImageDrawable(getResources().getDrawable(R.drawable.zap));

        this.b_var = findViewById(R.id.b_var);
        b_var.setImageDrawable(getResources().getDrawable(R.drawable.var));

        this.var = findViewById(R.id.t_var);

        //new
        this.T = findViewById(R.id.buttonT);
        this.T2 = findViewById(R.id.buttonT2);

        this.build_inp = findViewById(R.id.t_inp);
        this.build_out = findViewById(R.id.t_out);

        this.debg = findViewById(R.id.debug);



        Intent intent = getIntent();

        // все для списка
        moveList =new ArrayList<>();
        recyclerView = findViewById(R.id.rv);
        recyclerAdapter = new RecyclerAdapter(moveList);
        recyclerView.setAdapter(recyclerAdapter);


        moveListFrom =new ArrayList<>();
        recyclerViewFrom = findViewById(R.id.rv_from);
        recyclerAdapterElem = new RecyclerAdapterElem(moveListFrom);
        recyclerViewFrom.setAdapter(recyclerAdapterElem);


        moveList.add("section .data/секция данных");
        moveList.add("section .text/секция кода");
        moveList.add("global _start/директива экспорта в модули");
        moveList.add("_start:/начало программы");




        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // спинер
        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerStr);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
        /////

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                moveListFrom.clear();
                recyclerAdapterElem.notifyDataSetChanged();
                String item = (String)parent.getItemAtPosition(position);
                //Toast.makeText(getBaseContext(),item,Toast.LENGTH_LONG).show();
                if(item.equals("Секции синтаксиса")){
                    moveListFrom.add("section .data/s");
                    moveListFrom.add("section .text/s");
                    moveListFrom.add("global _start/s");
                    moveListFrom.add("_start:/s");
                    moveListFrom.add("section .bss/s");
                }
                if(item.equals("Команды")){
                    moveListFrom.add("mov /пересылка данных");

                }
                if(item.equals("Регистры")){
                    moveListFrom.add("eax/регистр");
                    moveListFrom.add("ebx/регистр");
                    moveListFrom.add("ecx/регистр");
                    moveListFrom.add("edx/регистр");

                }
                if(item.equals("Инструкции")){
                    moveListFrom.add("int 80h/вызов прерывания");

                }

                if(item.equals("Переменные")){
                    for (int i = 0; i < iVar; i++){
                        moveListFrom.add(sepVar[i]);
                    }

                }
                if(item.equals("Готовые примеры")){
                    //moveListFrom.add("HelloEx/ex");
                    moveListFrom.add("hello:     db 'Hello World',10/пример");
                    moveListFrom.add("helloLen:  equ $-hello/пример");
                    moveListFrom.add("mov eax,4/пример");
                    moveListFrom.add("mov ebx,1/пример");
                    moveListFrom.add("mov ecx,hello/пример");
                    moveListFrom.add("mov edx,helloLen/пример");
                    moveListFrom.add("mov eax,1/пример");
                    moveListFrom.add("mov ebx,0/пример");
                    moveListFrom.add("int 80h/пример");
                }
                if(item.equals("Умнож")){
                    moveListFrom.add("section .data/m");
                    moveListFrom.add("section .bss/m");
                    moveListFrom.add("op resb 2/m");
                    moveListFrom.add("num1 resb 2/m");
                    moveListFrom.add("num2 resb 2/m");

                    moveListFrom.add("res resb 2/m");
                    moveListFrom.add("section .text/m");
                    moveListFrom.add("global _start/m");
                    moveListFrom.add("_start :/m");
                    moveListFrom.add("mov edx, 2/m");

                    moveListFrom.add("mov ecx, num1/m");
                    moveListFrom.add("mov ebx, 0/m");
                    moveListFrom.add(" mov eax, 3/m");
                    moveListFrom.add("int 0x80/m");
                    moveListFrom.add("mov edx, 2/m");

                    moveListFrom.add("mov ecx, num2/m");
                    moveListFrom.add("mov ebx, 0/m");
                    moveListFrom.add("mov eax, 3/m");
                    moveListFrom.add("int 0x80/m");
                    moveListFrom.add("multiplication:/m");

                    moveListFrom.add("movzx eax, byte [num1]/m");
                    moveListFrom.add("sub eax, '0'/m");
                    moveListFrom.add("movzx ebx, byte [num2]/m");
                    moveListFrom.add("sub ebx, '0'/m");
                    moveListFrom.add("imul eax, ebx/m");

                    moveListFrom.add("jmp print_result/m");
                    moveListFrom.add("print_result:/m");
                    moveListFrom.add("add eax, '0'/m");
                    moveListFrom.add("mov [res], eax/m");
                    moveListFrom.add("mov edx, 2/m");

                    moveListFrom.add("mov ecx, res/m");
                    moveListFrom.add("mov ebx, 1/m");
                    moveListFrom.add("mov eax, 4/m");
                    moveListFrom.add("int 0x80/m");
                    moveListFrom.add("mov ebx, 0/m");

                    moveListFrom.add("mov eax, 1/m");
                    moveListFrom.add("int 0x80/m");


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        // задание
        b_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countDownTimer.cancel();
                db.child("TestPart").child("Task").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {

                            //Boolean testAcs = (Boolean) task.getResult().getValue();
                            AlertDialog.Builder builder = new AlertDialog.Builder(Testing.this);
                            builder
                                    .setMessage((String) task.getResult().getValue())

                                    .setCancelable(true);

                            AlertDialog alert = builder.create();
                            alert.show();

                        }
                    }
                });

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



        // проверки
        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getBaseContext(),decimal2Hex(10),Toast.LENGTH_LONG).show();
                String finTime = (String) mTimer.getText();
                finTime = finTime.substring(10, finTime.length());
                Toast.makeText(getBaseContext(),finTime,Toast.LENGTH_LONG).show();
            }
        });
        //
        // отладка
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String sign = "> ";
        SpannableString redSpannable= new SpannableString(sign);
        redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, sign.length(), 0);
        builder.append(redSpannable);
        //
        b_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

                //Toast.makeText(getBaseContext(),String.valueOf(ls),Toast.LENGTH_LONG).show();
                // размер листа
                if(iter == 10){
                    moveList.remove(iter-1);
                    moveList.add(iter-1, s);

                    iter = 0;
                }

                int t = 0;
                if(iter > 0){
                    moveList.remove(iter-1);
                    moveList.add(iter-1, s);

                }
                s = moveList.get(iter).toString();
                moveList.remove(iter);
                moveList.add(iter, sign + s);
                if(s.contains("Jmp")){
                    //Toast.makeText(getBaseContext(),"jmp",Toast.LENGTH_LONG).show();

                }

                recyclerAdapter.notifyDataSetChanged();
                iter++;
//                if(iter == 4){
//                    moveList.remove(iter);
//                    moveList.add(iter, s);
//                    iter = 0;
//                }

                if(s.contains("mov eax,4")){
                    EAXh = "00000004";
                }

                debg.setText("                EAX           AX          AH   AL\n" +
                             "Hex  |"+ EAXh+"    "+ AXh +"         "+ AHh +"   "+ALh+"\n" +
                             "Sign |"+ EAXs+"    "+ AXs +"         "+ AHs +"   "+ALs+"\n" +

                                "                EBX           BX          BH   BL\n" +
                                "Hex  |00000000    0000         00   00\n" +
                                "Sign |00000000    0000         00   00\n"
                        );


                // процесс хождения по строкам
//                for (int i = 0; i < ls; i++){
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            s = moveList.get(ii).toString();
//                            moveList.remove(ii);
//                            moveList.add(ii, "> " + s);
//                            recyclerAdapter.notifyDataSetChanged();
//                            ii++;
//
//                        }
//                    }, i*1000);
//                    //
//
//                }
            }
        });
        //
        b_zap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if(ch){
                    test(",/-");
                }

            }
        });
        //
        b_var.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                iVar++;
                sVar += var.getText().toString() + "/-,";
                sepVar = sVar.split(",");
                //
//
                //Toast.makeText(getBaseContext(),sepVar[0],Toast.LENGTH_LONG).show();

            }
        });

        // проверка сборки

        b_play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stdin = "";
                    // взять инпут
                stdin = build_inp.getText().toString();
                //...
                sRes = "";
                int ls = moveList.size();
                for (int i = 0; i < ls; i++){
                    //sRes = moveList.get(iter).toString();
                    String[] sepRes = moveList.get(i).toString().split("/");
                    sRes += sepRes[0] + "\\n ";
                }
                    // api
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            OkHttpClient client = new OkHttpClient();

                            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                            Charset charset = Charset.forName(StandardCharsets.UTF_8.name());

                            //new asm
                            RequestBody body = RequestBody.create(mediaType, "{\"language\":\"assembly\"," +
                                    "\"stdin\":\"" + stdin + "\"," +
                                    "\"files\":[" +
                                    "{" +
                                    "\"name\":\"index.asm\"," +
                                    "\"content\":\""+ sRes + "\"" +
                                    "}" +
                                    "]}");

                            Request request = new Request.Builder()
                                    .url("https://onecompiler-apis.p.rapidapi.com/api/v1/run")
                                    .addHeader("content-type", "application/json; charset=utf-8")
                                    .addHeader("X-RapidAPI-Key", "2fe09e3650msh4c36672b599eda2p1c7be1jsn9ee0df80dcbd")
                                    .addHeader("X-RapidAPI-Host", "onecompiler-apis.p.rapidapi.com")

                                    .post(body)
                                    .build();

                            client.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    if(response.isSuccessful()){
                                        final String resp = response.body().string();
                                        String[] sepResp = resp.split(",");

                                        String resp1 = sepResp[2].substring(10, sepResp[2].length()-3); // вывод
                                        if(resp1.contains("u00")){
                                            resp1 = resp1.substring(0, resp1.length()-4);
                                        }
                                        build_out.setText(resp1);
                                        String finalResp = resp1;
                                        Testing.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                //Toast.makeText(getBaseContext(), finalResp,Toast.LENGTH_LONG).show();
                                                // засунуть респ в поле
                                            }
                                        });
                                    }
                                }
                            });

                        }
                    }).start(); // конец api


            }

        });
        // проверки оконч.
        b_play.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(getBaseContext(),"ku",Toast.LENGTH_LONG).show();
                whileCh = true;
                finalCh = 0;

                String finTime = (String) mTimer.getText();
                finTime = finTime.substring(10, finTime.length());
                String finalFinTime = finTime;
                for (int q = 0; q < i_ex; q++){
                    int finalQ = q;

                    db.child("TestPart").child("Check").child("ex" + String.valueOf(q+1)).addValueEventListener(new ValueEventListener() {
                        // получаем проверку
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //setTime = (long) dataSnapshot.getValue();
                            //String checkEx = (String) dataSnapshot.getValue();
                                // new check
                            String checkEx;
                            if(dataSnapshot.getValue() != null){
                                 checkEx = (String) dataSnapshot.getValue();
                            } else {
                                 checkEx = "-/-";
                            }
                            //Toast.makeText(getBaseContext(),checkEx,Toast.LENGTH_LONG).show();
                            String[] checkExSep = checkEx.split("/");
                            //...
                            sRes = "";
                            int ls = moveList.size();
                            for (int i = 0; i < ls; i++){
                                //sRes = moveList.get(iter).toString();
                                String[] sepRes = moveList.get(i).toString().split("/");
                                sRes += sepRes[0] + "\\n ";
                            }
                            // апи часть
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    OkHttpClient client = new OkHttpClient();

                                    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                                    Charset charset = Charset.forName(StandardCharsets.UTF_8.name());

                                    //new asm
                                    RequestBody body = RequestBody.create(mediaType, "{\"language\":\"assembly\"," +
                                            "\"stdin\":\"" + checkExSep[0] + "\"," +
                                            "\"files\":[" +
                                            "{" +
                                            "\"name\":\"index.asm\"," +

                                            "\"content\":\""+ sRes + "\"" +
                                            "}" +
                                            "]}");

                                    Request request = new Request.Builder()
                                            .url("https://onecompiler-apis.p.rapidapi.com/api/v1/run")
                                            .addHeader("content-type", "application/json; charset=utf-8")
                                            .addHeader("X-RapidAPI-Key", "2fe09e3650msh4c36672b599eda2p1c7be1jsn9ee0df80dcbd")
                                            .addHeader("X-RapidAPI-Host", "onecompiler-apis.p.rapidapi.com")

                                            .post(body)
                                            .build();

                                    client.newCall(request).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                            if(response.isSuccessful()){
                                                final String resp = response.body().string();
                                                String[] sepResp = resp.split(",");

                                                String resp1 = sepResp[2].substring(10, sepResp[2].length()-3); // вывод
                                                if(resp1.contains("u00")){
                                                    resp1 = resp1.substring(0, resp1.length()-4);
                                                }

                                                String finalResp = resp1;
                                                Testing.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        //Toast.makeText(getBaseContext(),resp1,Toast.LENGTH_LONG).show();
                                                        //если resp1 == checkExSep[0]
                                                        if(finalResp.equals(checkExSep[1])){
                                                            Toast.makeText(getBaseContext(), "Тест №" + String.valueOf(finalQ+1) + " +",Toast.LENGTH_LONG).show();
                                                            //Toast.makeText(getBaseContext(), "ch" + String.valueOf(finalCh),Toast.LENGTH_LONG).show();
                                                            finalCh++;
                                                        }
//                                                        else {
//                                                            if(whileCh){
//                                                                Toast.makeText(getBaseContext(), "Не прошел на тест №" + String.valueOf(finalQ+1),Toast.LENGTH_LONG).show();
//                                                                whileCh = false;
//                                                                fail++;
//                                                            }
//
//                                                        }
                                                        if(!finalResp.equals(checkExSep[1]) && whileCh){

                                                            new Handler().postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {

                                                                    Toast.makeText(getBaseContext(), "Не прошел на тест №" + String.valueOf(finalQ+1),Toast.LENGTH_LONG).show();
                                                                    whileCh = false;
                                                                    fail++;
                                                                }
                                                            }, 1000);

                                                        }
                                                        //
                                                        if(finalCh == i_ex && whileCh){
                                                            Toast.makeText(getBaseContext(), "Задание выполнено",Toast.LENGTH_LONG).show();

                                                            countDownTimer.cancel();
                                                            mTimer.setText("-");
                                                            //
                                                            Date currentTime = Calendar.getInstance().getTime();
                                                            resForP = MainActivity.nameStr + "   Time: " + "Attempt: " + String.valueOf(fail) + " | " + currentTime + "\n";
                                                            db.child("TestStatistic").setValue(resForP);
                                                            //db.child("TestStatistic").child(MainActivity.nameStr + " - Date").setValue(currentTime);
                                                            //
                                                            db.child("TestPart").child("Time").addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {

                                                                    // alert
                                                                    if(dataSnapshot.getValue() != null){
                                                                        AlertDialog.Builder builder = new AlertDialog.Builder(Testing.this);
                                                                        builder.setTitle("Задание выполнено")

                                                                                .setMessage("Затрачено времени: " + String.valueOf((int)((long) dataSnapshot.getValue()) - Integer.parseInt(finalFinTime) ) +"\n"
                                                                                + "Затрачено попыток: " + fail)



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

                                                                    //

                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {
                                                                    System.out.println("The read failed: " + databaseError.getCode());
                                                                }
                                                            });
                                                            //
                                                            whileCh = false;
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });

                                }
                            }).start(); // конец api
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("The read failed: " + databaseError.getCode());
                        }


                    });
                } // конец всего проверочного цикла

                return true;
            }
        });


        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        // конец кнопки

        //все по таймеру
        //


        db.child("TestPart").child("Time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String setTimeS = String.valueOf(dataSnapshot.getValue());

                if(dataSnapshot.getValue() != null){
                    setTime = (long) dataSnapshot.getValue();
                } else {
                    setTime = 5;
                }

                // new
                int t = (int)(setTime);

                mTimer = (TextView) findViewById(R.id.tv);

                countDownTimer = new CountDownTimer(t*1000 + 1000, 1000) {

                    //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
                    public void onTick(long millisUntilFinished) {
                        mTimer.setText("Осталось: "
                                + millisUntilFinished / 1000);
                    }

                    //Задаем действия после завершения отсчета
                    public void onFinish() {

                        if(active){
                            AlertDialog.Builder builder = new AlertDialog.Builder(Testing.this);
                            builder.setTitle("Время на исходе!")
                                    .setMessage("Попробуйте пройти тест заново, если он доступен или дождтесь начала новой сессии ")
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

                        mTimer.setText("Время на исходе!");

                        MainActivity.BackCh = false;
                    }

                }.start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        //


    } // end on Create
    @Override
    public void onBackPressed() {
        Toast.makeText(getBaseContext(), "Доступ к тесту заблокирован",Toast.LENGTH_LONG).show();
        MainActivity.BackCh = false;
         super.onBackPressed();

    }


    // new db
    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        db.addValueEventListener(new ValueEventListener() {
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
        public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.@NonNull ViewHolder viewHolder, RecyclerView.@NonNull ViewHolder target) {
            int fromPos = viewHolder.getAdapterPosition();
            int toPos = target.getAdapterPosition();

            Collections.swap(moveList, fromPos, toPos);

            recyclerView.getAdapter().notifyItemMoved(fromPos, toPos);
            recyclerAdapter.notifyDataSetChanged(); // только по одному элементу - мб убрть
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.@NonNull ViewHolder viewHolder, int direction) {
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
            if(chInt == pos){
                Toast.makeText(Testing.this, "Выбор строки снят", Toast.LENGTH_SHORT).show();
                ch = false;
            }
        }
    }; // d&d end

}

