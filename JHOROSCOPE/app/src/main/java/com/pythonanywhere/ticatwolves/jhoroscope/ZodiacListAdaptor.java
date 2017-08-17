package com.pythonanywhere.ticatwolves.jhoroscope;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ticat on 2/7/17.
 */

public class ZodiacListAdaptor extends RecyclerView.Adapter<ZodiacListAdaptor.MyOwnHolder>{
    String list1[],list2[],list3[];
    int image1[],image2[],image3[];
    Context ctx;
    public ZodiacListAdaptor(Context ct, String l1[], String l2[],String l3[],int a[],int b[],int c[]){
        list1 = l1;
        list2 = l2;
        list3 = l3;
        image1 = a;
        image2 = b;
        image3 = c;
        ctx = ct;
    }


    @Override
    public MyOwnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myinflat = LayoutInflater.from(ctx);
        View myOwnView = myinflat.inflate(R.layout.layout_row_one,parent,false);
        return new MyOwnHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(final MyOwnHolder holder, final int position) {
        holder.t1.setText(list1[position]);
        holder.t2.setText(list2[position]);
        holder.t3.setText(list3[position]);
        holder.i1.setBackgroundResource(image1[position]);
        //holder.i1.setImageResource(image1[position]);
        holder.i2.setBackgroundResource(image2[position]);
        //holder.i2.setImageResource(image2[position]);
        holder.i3.setBackgroundResource(image3[position]);

        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.context,ZodiacViewHolder.class);
                intent.putExtra("ZodiacName",list1[position]);
                intent.putExtra("zimage",image1[position]);
                holder.context.startActivity(intent);
            }
        });
        holder.t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.context,ZodiacViewHolder.class);
                intent.putExtra("ZodiacName",list2[position]);
                intent.putExtra("zimage",image2[position]);
                holder.context.startActivity(intent);
            }
        });
        holder.t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.context,ZodiacViewHolder.class);
                intent.putExtra("ZodiacName",list3[position]);
                intent.putExtra("zimage",image3[position]);
                holder.context.startActivity(intent);
            }
        });
        //holder.i3.setImageResource(image3[position]);
    }


    @Override
    public int getItemCount() {
        return list1.length;
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder {
        Context context;
        Button t1,t2,t3;
        ImageButton i1,i2,i3;
        public MyOwnHolder(View itemView) {

            super(itemView);
            t1 = (Button) itemView.findViewById(R.id.one);
            t2 = (Button) itemView.findViewById(R.id.two);
            t3 = (Button) itemView.findViewById(R.id.three);
            i1 = (ImageButton)itemView.findViewById(R.id.ione);
            i2 = (ImageButton)itemView.findViewById(R.id.itwo);
            i3 = (ImageButton)itemView.findViewById(R.id.ithree);
            context = itemView.getContext();
        }
    }
}
