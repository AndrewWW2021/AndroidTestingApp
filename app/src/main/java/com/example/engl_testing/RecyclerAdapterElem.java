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

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.el.setText(moveListElem.get(position));
    }

    @Override
    public int getItemCount() {
        return moveListElem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView el;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            el = itemView.findViewById(R.id.elem);
        }

        @Override
        public void onClick(View view) {
            String w = moveListElem.get(getAdapterPosition());
            //Toast.makeText(view.getContext(), w,Toast.LENGTH_LONG).show();
            if(w.contains("mov")){
                Toast.makeText(view.getContext(), "Команда mov",Toast.LENGTH_LONG).show();
            }
        }
    }
    // new dip


}
