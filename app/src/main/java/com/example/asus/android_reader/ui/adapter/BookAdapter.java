package com.example.asus.android_reader.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.android_reader.R;
import com.example.asus.android_reader.domain.entities.BookMeta;
import com.example.asus.android_reader.ui.IFileDisplayer;
import com.example.asus.android_reader.ui.ReadBookActivity;

import java.util.List;

/**
 * Created by Asus on 02.10.2017.
 */

public class BookAdapter extends
        RecyclerView.Adapter<BookAdapter.CardViewHolder> implements View.OnClickListener {

    public View view;

    List<BookMeta> bookMetas;
    private IFileDisplayer fileDisplayer;


    public BookAdapter(List<BookMeta> list) {
        this.bookMetas = list;
        notifyDataSetChanged();
    }


    @Override
    public BookAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_cards_layout, parent, false);
        return new BookAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookAdapter.CardViewHolder holder, final int position) {
        BookMeta bookMeta = bookMetas.get(position);
        String bookName = bookMeta.getName().substring(0, bookMeta.getName().lastIndexOf('.'));

        holder.bookNameTextView.setText(bookName);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);

    }

    public void removeItem(int position) {
        bookMetas.remove(position);
        notifyItemChanged(position);
    }


    @Override
    public int getItemCount() {
        return bookMetas.size();
    }

    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        Intent intent = new Intent(view.getContext(), ReadBookActivity.class);
        intent.putExtra("name", bookMetas.get(pos).getName());
        view.getContext().startActivity(intent);
        System.out.println("Hello world");
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {
        protected TextView bookNameTextView;


        public CardViewHolder(final View itemView) {
            super(itemView);
            bookNameTextView = (TextView) itemView.findViewById(R.id.book_name);


        }

    }

    public void setBookMetas(List<BookMeta> bookMetas) {
        this.bookMetas = bookMetas;
        notifyDataSetChanged();
    }

}