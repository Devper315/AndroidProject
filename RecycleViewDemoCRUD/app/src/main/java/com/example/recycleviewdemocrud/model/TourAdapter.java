package com.example.recycleviewdemocrud.model;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recycleviewdemocrud.R;
import com.example.recycleviewdemocrud.listener.CatItemListener;
import com.example.recycleviewdemocrud.utils.Utils;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private Context context;
    private CatItemListener catItemListener;

    public TourAdapter(Context context) {
        this.context = context;
    }

    public void setClickListener(CatItemListener mCatItem) {
        this.catItemListener = mCatItem;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TourViewHolder(view);
    }

    public Tour getItem(int position) {
        return Utils.tourListShow.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, @SuppressLint("RecyclerView") int positon) {
        Tour tour = Utils.tourListShow.get(positon);
        if (tour == null)
            return;
        holder.img.setImageResource(tour.getImg());
        holder.tvName.setText(tour.getPath());
        holder.tvDescribe.setText(tour.getTime());
        holder.btRemove.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Ban có chắc chắn muốn xóa tour " + tour.getPath() + " không?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Utils.removeTour(positon, TourAdapter.this);
                }
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return Utils.tourListShow.size();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tvName, tvDescribe;
        private Button btRemove;

        public TourViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.txtName);
            tvDescribe = view.findViewById(R.id.txtDescribe);
            btRemove = view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (catItemListener != null) {
                catItemListener.onItemClick(view, this.getAdapterPosition());
            }
        }
    }


}
