package com.example.sugarorm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sugarorm.R;
import com.example.sugarorm.model.Person;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder> {
    private List<Person> personList;
    private Context context;

    public ModelAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.context = context;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_item, parent, false);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.id.setText("ID: " + person.getId());
        holder.name.setText("Info: " + person);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder{
        private TextView id, name;
        public ModelViewHolder(@NonNull View view) {
            super(view);
            id = view.findViewById(R.id.id);
            name = view.findViewById(R.id.name);
        }
    }
}
