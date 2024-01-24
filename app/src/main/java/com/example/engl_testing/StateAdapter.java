package com.example.engl_testing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder> implements ItemTouchHelperAdapter{



    private final LayoutInflater inflater;
    private final List<State> states;

    StateAdapter(Context context, List<State> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        State state = states.get(position);
        holder.flagView.setImageResource(state.getFlagResource());
        //holder.nameView.setText(state.getName());
        //holder.capitalView.setText(state.getCapital());
        holder.flagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //states.remove(position);
                //notifyItemInserted(position);
                //b_inbox = true;
                //Level_1.click_inbox = true;

            }
        });
    }
    public void add(View view){
        states.add(new State (R.drawable.inbox));

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    @Override
    public void onItemDismiss(int position) {
        states.remove(position);
        notifyItemInserted(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        //final TextView nameView, capitalView;
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.flag);
            //nameView = view.findViewById(R.id.name);
            //capitalView = view.findViewById(R.id.capital);
        }
    }
//    public void restoreItem(int position) {
//        //data.add(position, item);
//        states.remove(position);
//        notifyItemInserted(position);
//    }


}
