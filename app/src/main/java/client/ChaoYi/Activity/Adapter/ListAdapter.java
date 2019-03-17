package client.ChaoYi.Activity.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Test;
import client.ChaoYi.R;

/**
 * Created by WCY on 2019/3/16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;

    private List<Contenttable> content = new ArrayList<>();

    public ListAdapter(List<Contenttable> content) {
        this.content = content;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {//设置上下文环境
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
//        Test cat = content.get(position);
        Contenttable ct = content.get(position);
        holder.name.setText(ct.getCt_name());
//        Glide.with(context).load(ct.getImgId()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return content.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView name;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            image = (ImageView) itemView.findViewById(R.id.head_image);
            name = (TextView) itemView.findViewById(R.id.head_name);
            time = (TextView) itemView.findViewById(R.id.head_time);
        }
    }

}
