package Test.test_1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Test.test_1.R;

public class DuAnAdapter extends RecyclerView.Adapter<DuAnAdapter.DuAnHolder> {
    private Context context;
    private List<DuAn> lst;
    private DuAnListener listener;

    public DuAnAdapter(Context context) {
        this.context = context;
        lst = new ArrayList<>();
    }

    public void setListener(DuAnListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DuAnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle_view, parent, false);
        return new DuAnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuAnHolder holder, int position) {
        DuAn duAn = lst.get(position);
        if(duAn == null)
            return;
        holder.duAn.setText(duAn.getTenDA());
        holder.start.setText("Từ " + duAn.getNgayBD());
        holder.end.setText("Đến " + duAn.getNgayKT());
        holder.cb.setActivated(duAn.isHoanThanh());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lst.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lst == null ? 0 : lst.size();
    }

    public DuAn getDuAn(int position) {
        return lst.get(position);
    }

    public void add(DuAn duAn) {
        lst.add(duAn);
        notifyDataSetChanged();
    }

    public void update(DuAn duAn, int position) {
        lst.set(position, duAn);
        notifyDataSetChanged();
    }

    public class DuAnHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView duAn, start, end;
        private CheckBox cb;
        private Button remove;
        public DuAnHolder(@NonNull View itemView) {
            super(itemView);
            duAn = itemView.findViewById(R.id.duAn);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            cb = itemView.findViewById(R.id.cb);
            remove = itemView.findViewById(R.id.xoa);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener == null)
                return;
            listener.onClickListener(v, getAdapterPosition());
        }
    }

    public interface DuAnListener {
        public void onClickListener(View view, int position);
    }
}
