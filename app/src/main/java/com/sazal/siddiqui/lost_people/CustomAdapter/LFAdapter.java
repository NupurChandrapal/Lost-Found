package com.sazal.siddiqui.lost_people.CustomAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sazal.siddiqui.lost_people.Model.LostFound;
import com.sazal.siddiqui.lost_people.Model.LostFoundData;
import com.sazal.siddiqui.lost_people.R;

import java.util.List;

/**
 * Created by sazal on 2017-10-13.
 */

public class LFAdapter extends RecyclerView.Adapter<LFAdapter.MS_ViewHolder> {

    private Context context;
    private List<LostFoundData> lostFounds;
    private ItemClickListener clickListener;

    public LFAdapter(Context context, List<LostFoundData> lostFounds) {
        this.context = context;
        this.lostFounds = lostFounds;
    }

    @Override
    public MS_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MS_ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(MS_ViewHolder holder, int position) {
        LostFoundData lostFound = lostFounds.get(position);
        Resources resources = context.getResources();
        holder.name.setText(resources.getString(R.string.name, lostFound.getName()));
        holder.date.setText(resources.getString(R.string.date, lostFound.getDate()));
        holder.place.setText(resources.getString(R.string.place, lostFound.getLostPlace()));

        Glide.with(context).load(lostFound.getPictureUrl()).placeholder(R.drawable.placeholder).error(R.mipmap.ic_launcher).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return lostFounds.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    class MS_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatTextView name, date, place;
        AppCompatImageView thumbnail;

        MS_ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.appCompatTextViewName);
            date = itemView.findViewById(R.id.appCompatTextViewDate);
            place = itemView.findViewById(R.id.appCompatTextViewPlace);
            thumbnail = itemView.findViewById(R.id.appCompatImageViewPlaceHolder);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}
