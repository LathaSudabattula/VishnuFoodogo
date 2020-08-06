package com.svecw.vishnufoodogo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.svecw.vishnufoodogo.Modal.Stall;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomRecyclerViewHolder> {

    List<Stall> mstalls;

    private  ClickListener clicklistener = null;


    Context mContext;

    public UserAdapter(Context context, List<Stall> items){
        mContext = context;
        mstalls = items;
    }

    @Override
    public void onBindViewHolder(final CustomRecyclerViewHolder holder, final int position) {
        Glide.with(mContext).load(mstalls.get(position).getImageURL()).into(holder.mAvatarView);
        holder.mMsg1.setText(mstalls.get(position).getName().toString());

        //Log.v("p", mstalls.get(position));




    }
    @Override
    public int getItemCount() {

        return mstalls.size();
    }

    public class CustomRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView mMsg1;
        ImageView mAvatarView;
        CardView cardView;
        public CustomRecyclerViewHolder(View itemView) {
            super(itemView);
            mMsg1 = (TextView)itemView.findViewById(R.id.stallname);

            mAvatarView = (ImageView)itemView.findViewById(R.id.stallimage);


            cardView = (CardView)itemView.findViewById(R.id.card_holder);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clicklistener !=null){
                        clicklistener.itemClicked(view,getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setClickListener(ClickListener clickListener){
        this.clicklistener = clickListener;
    }

    public CustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.stall_card, parent, false);
        //set the margin if any, will be discussed in next blog
        return new CustomRecyclerViewHolder(v);
    }
}
