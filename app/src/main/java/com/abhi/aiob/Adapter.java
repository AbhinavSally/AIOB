package com.abhi.aiob;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    Context c;
    List<news> news;
    Adapter(Context c, List<news> news) {
        this.c = c;
        this.news = news;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.resultlist, parent, false);
        Holder h = new Holder(v);

        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        news up = news.get(position);

        holder.t.setText(up.getSs());

        Glide.with(c).load(up.getSti()).into(holder.i);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        ImageView i;
        TextView t;

        public Holder(@NonNull View itemView) {
            super(itemView);
            i = itemView.findViewById(R.id.imageView2);
            t = itemView.findViewById(R.id.textView38);

        }

    }
}
