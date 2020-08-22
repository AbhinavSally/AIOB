package com.abhi.aiob;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    int s;
    Context c;

    int img[] = {
            R.drawable.fire,
            R.drawable.car,
            R.drawable.hs,
            R.drawable.terror,
            R.drawable.nd,
            R.drawable.mmd,
            R.drawable.robb,
            R.drawable.oth
    };

    String data[] = {
            "FIRE",
            "ACCIDENT",
            "ASSAULT",
            "TERRORIST",
            "NATURAL DISASTER",
            "MAN MADE DISASTER",
            "ROBBERY",
            "OTHERS"
    };

    MyAdapter(Context c) {
        this.c = c;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.optlis, parent, false);
        Holder d = new Holder(v);
        return d;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.i.setImageResource(img[position]);
        holder.t.setText(data[position]);
        holder.setPositionData(position);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView i;
        TextView t;

        View v;
        int position;

        public Holder(@NonNull View itemView) {
            super(itemView);
            i = itemView.findViewById(R.id.imageView);
            t = itemView.findViewById(R.id.textView3);
            v = itemView;
        }

        void setPositionData(int p) {
            position = p;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = null;
                    switch (position) {
                        case 0:
                            intent = new Intent(c, Fire.class);
                            break;
                        case 1:
                            intent = new Intent(c, Accident.class);
                            break;
                        case 2:
                            intent = new Intent(c, Assault.class);
                            break;
                        case 3:
                            intent = new Intent(c, Terrorist.class);
                            break;
                        case 4:
                            intent = new Intent(c, NaturalDisaster.class);
                            break;
                        case 5:
                            intent = new Intent(c, ManMadeDisaster.class);
                            break;
                        case 6:
                            intent = new Intent(c, Robbery.class);
                            break;
                        case 7:
                            intent = new Intent(c, Others.class);
                            break;
                    }
                    c.startActivity(intent);
                    System.exit(0);
                }
            });
        }
    }
}

