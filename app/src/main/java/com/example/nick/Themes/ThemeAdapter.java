package com.example.nick.Themes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nick.R;

import java.util.ArrayList;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String>name;
    private ArrayList<Integer> id;
    private OnItemClicked onClick;

    public ThemeAdapter(Context context, ArrayList<Integer> id, ArrayList<String> name) {
        this.context = context;
        this.id = id;
        this.name = name;


    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.myrow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.OnItemClicked(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        TextView id;
        Button more;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.idTheme);
            name=itemView.findViewById(R.id.name);

            more=itemView.findViewById(R.id.readMore);


        }
    }
    public interface OnItemClicked {

        void OnItemClicked(int position);
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}


