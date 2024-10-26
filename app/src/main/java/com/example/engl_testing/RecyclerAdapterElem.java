package com.example.engl_testing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

public class RecyclerAdapterElem extends RecyclerView.Adapter<RecyclerAdapterElem.ViewHolder>{

    private static final String TAG = "RecyclerAdapterElem";
    List<String> moveListElem;

    public RecyclerAdapterElem(List<String> moveListElem){
        this.moveListElem = moveListElem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item_e, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.el.setText(moveListElem.get(position));
        String[] separated = moveListElem.get(position).split("/");
        holder.el.setText(separated[0]);

    }

    @Override
    public int getItemCount() {
        return moveListElem.size();
    }

    public Activity getActivity(Context context)
    {
        if (context == null)
        {
            return null;
        }
        else if (context instanceof ContextWrapper)
        {
            if (context instanceof Activity)
            {
                return (Activity) context;
            }
            else
            {
                return getActivity(((ContextWrapper) context).getBaseContext());
            }
        }

        return null;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView el;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            el = itemView.findViewById(R.id.elem);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String w = moveListElem.get(getAdapterPosition());
            //Toast.makeText(view.getContext(), w,Toast.LENGTH_LONG).show();

            Testing.test(w);

        }
//        private String convertStreamToString(InputStream is) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            StringBuilder sb = new StringBuilder();
//
//            String line = null;
//            try {
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line).append('\n');
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return sb.toString();
//        }
//
//        String getStringFromAssetFile(Activity activity)
//        {
//            AssetManager am = activity.getAssets();
//            InputStream is = null;
//            try {
//                is = am.open("movT.txt");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String s = convertStreamToString(is);
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return s;
//        }

        @Override
        public boolean onLongClick(View view) {
            String ww = moveListElem.get(getAdapterPosition());

            if(ww.contains("mov")){
                //Toast.makeText(view.getContext(), "Команда mov",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Инструкция MOV")
                        .setMessage("Инструкция MOV в ассемблере самая простая. Синтаксис этой команды такой: \n\n " +
                                "MOV ПРИЁМНИК, ИСТОЧНИК \n\n"
                                + "С помощью этой команды можно переместить значение из ИСТОЧНИКА в ПРИЁМНИК. То есть по сути команда MOV копирует содержимое ИСТОЧНИКА и помещает это содержимое в ПРИЁМНИК. \n\n"
                                + "Никакие флаги при этом НЕ изменяются. \n\n"
                                + "При использовании этой команды следует учитывать, что имеются некоторые ограничения. А именно, инструкция MOV не может: \n\n"
                                + "> Записывать данные в регистры CS и IP. \n\n"
                                + "> Копировать данные из одного сегментного регистра в другой сегментный регистр (сначала нужно скопировать данные в регистр общего назначения). \n\n"
                                + "> Копировать непосредственное значение в сегментный регистр (сначала нужно скопировать данные в регистр общего назначения). \n\n"
                                + "ИСТОЧНИКОМ может быть один из следующих: \n\n"
                                + "Область памяти (MEM) \n\n"
                                + "Регистр общего назначения (REG) \n\n"
                                + "MOV ПРИЁМНИК, ИСТОЧНИК \n\n"
                                + "Непосредственное значение (например, число) (IMM) \n\n"
                                + "Сегментный регистр (SREG) \n\n"
                                + "ПРИЁМНИКОМ может быть один из следующих: \n\n"
                                + "> Область памяти (MEM) \n\n"
                                + "> Регистр общего назначения (REG) \n\n"
                                + "> Сегментный регистр (SREG) \n\n"

                        )

                        .setCancelable(true);
//                        .setNegativeButton("В главное меню",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                        Intent intent = new Intent(Testing.this, MainActivity.class);
//                                        startActivity(intent);
//                                    }
//                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            return true;
        }
    }
    // new dip


}
