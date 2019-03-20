package com.stationsfinder.administrator.stationsfinder.adapter;

/**
 * Created by Administrator on 12/12/2017.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stationsfinder.administrator.stationsfinder.R;

public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.Viewholder> {
    String[] arrray_str;
    int[] array_images;
    customclick cc;


 public interface customclick{
        void click(int pos);
    }
    public void onCustomClick(customclick cc){
        this.cc=cc;
    }

    public Recycleradapter(int[] image, String[] str) {

        array_images=image;
        arrray_str=str;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.tv.setText(arrray_str[position]);
        holder.iv.setImageResource(array_images[position]);

    }

    @Override
    public int getItemCount() {
        return arrray_str.length;
    }

    public class Viewholder extends RecyclerView.ViewHolder  {

        public TextView tv;
        public ImageView iv;
        CardView cv;
        public Viewholder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.title);
            iv= (ImageView) itemView.findViewById(R.id.image);
            cv= (CardView) itemView.findViewById(R.id.cv);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cc.click(getAdapterPosition());
                }
            });
        }
    }
}

