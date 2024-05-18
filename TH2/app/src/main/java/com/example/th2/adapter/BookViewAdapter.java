package com.example.th2.adapter;

import static com.example.th2.Utils.bookListShow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.R;

public class BookViewAdapter extends RecyclerView.Adapter<BookViewAdapter.BookViewHolder>{
    private Context context;

    public BookViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        view.setOnClickListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bookListShow.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, author, publishDate;
        RadioButton radio1, radio2;
        CheckBox check1, check2, check3;
        RatingBar rating;
        public BookViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.tv_name);
            author = view.findViewById(R.id.tv_author);
            publishDate = view.findViewById(R.id.tv_publish_date);
            radio1 = view.findViewById(R.id.radio1);
            radio2 = view.findViewById(R.id.radio2);
            check1 = view.findViewById(R.id.check1);
            check2 = view.findViewById(R.id.check2);
            check3 = view.findViewById(R.id.check3);
            rating = view.findViewById(R.id.rating_bar);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
