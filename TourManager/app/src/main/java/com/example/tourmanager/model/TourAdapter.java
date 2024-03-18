package com.example.tourmanager.model;

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

import com.example.tourmanager.R;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private List<Tour> tours;
    private List<Tour> toursBackup;
    private Context context;
    private TourItemListener tourItemListener;

    public TourAdapter(Context context) {
        this.tours = new ArrayList<>();
        toursBackup = new ArrayList<>();
        this.context = context;
    }

    // Thêm interface bắt sự kiện vào adapter
    public void setTourClickListener(TourItemListener tourItemListener) {
        this.tourItemListener = tourItemListener;
    }

    public void filterList(List<Tour> filterList){
        tours = filterList;
        notifyDataSetChanged();
    }

    public List<Tour> getToursBackup() {
        return toursBackup;
    }


    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tour_item, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tour tour = tours.get(position);
        if (tour == null) {
            return;
        }
        holder.img.setImageResource(tour.getImg());
        holder.tvItinerary.setText(tour.getItinerary());
        holder.tvTime.setText(tour.getTime());
        holder.tvPrice.setText(tour.getPrice() + "");
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa tour " + tour.getItinerary() + " ?");
                builder.setIcon(R.drawable.remove);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tours.remove(position);
                        toursBackup.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public void add(Tour tour) {
        tours.add(tour);
        toursBackup.add(tour);
        notifyDataSetChanged();
    }

    public void update(int position, Tour tour) {
        tours.set(position, tour);
        tours.set(position, tour);
        notifyDataSetChanged();
    }

    public Tour getTourAtPosition(int position) {
        return tours.get(position);
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tvItinerary, tvTime, tvPrice;
        private Button btnRemove;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.vehicleImg);
            tvItinerary = itemView.findViewById(R.id.tourName);
            tvTime = itemView.findViewById(R.id.tourTime);
            tvPrice = itemView.findViewById(R.id.tourPrice);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            itemView.setOnClickListener(this);
        }

        // thêm sự kiện onClick vào mỗi phần tử ViewHolder trong RecyclerView bằng cách implement View.OnClickListener
        @Override
        public void onClick(View view) {
            if (tourItemListener != null) {
//                khi có sự kiện nhấp, gọi phương thức onTourItemClick xử lý tiếp
//                view: view của phần tử hiện tại
//                getAdapterPosition(): lấy ra vị trí view hiện tại trong list đang hiển thị
                tourItemListener.onTourItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface TourItemListener {
        void onTourItemClick(View view, int position);
    }
}
