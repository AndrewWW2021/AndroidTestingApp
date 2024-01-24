package com.example.engl_testing;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private static final String TAG = "RecyclerAdapter";
    List<String> moveList;

    public RecyclerAdapter(List<String> moveList){
        this.moveList = moveList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    //time - 0.45
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String[] separated = moveList.get(position).split("/");
        holder.com.setText(separated[0]);
        if(!separated[1].equals("-")){
            holder.desc.setText(String.valueOf(position + 1) + ": " + separated[1]);
        }
        else {
            holder.desc.setText(" ");
        }

    }

    @Override
    public int getItemCount() {
        return moveList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView iView;
        TextView com, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //iView = itemView.findViewById(R.id.flag);
            com = itemView.findViewById(R.id.command);
            desc = itemView.findViewById(R.id.description);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
//
        }

        @Override
        public void onClick(View view) {
            String w = moveList.get(getAdapterPosition());
            //Toast.makeText(view.getContext(), w,Toast.LENGTH_LONG).show();
            if(w.contains("InBox")){
                Toast.makeText(view.getContext(), "Команда InBox берет один верхний элемент из ВВОДА",Toast.LENGTH_LONG).show();
            }
            if(w.contains("OutBox")){
                Toast.makeText(view.getContext(), "Команда OutBox отправляет взятый элемент на ВЫВОД",Toast.LENGTH_LONG).show();
            }
            if(w.contains("R_mov")){
                Toast.makeText(view.getContext(), "Команда mov",Toast.LENGTH_LONG).show();
                //moveList.add("mov");
                //test();
                Testing.test();

            }

        }

        @Override
        public boolean onLongClick(View view) {
            //
            String ww = moveList.get(getAdapterPosition());
            int i = moveList.indexOf(getAdapterPosition()); // String.valueOf(i)
            Toast.makeText(view.getContext(), ww,Toast.LENGTH_LONG).show();
            //moveList.set(1,"t");
            //Testing.test2();
            return true;
        }
    }

}
