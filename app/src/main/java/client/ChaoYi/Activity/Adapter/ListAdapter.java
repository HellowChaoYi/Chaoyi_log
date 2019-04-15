package client.ChaoYi.Activity.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.R;

/**
 * Created by WCY on 2019/3/16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private Context context;
    private OnItemClickListener mClickListener;
    private List<Contenttable> content = new ArrayList<>();
    private Map map;
    public ListAdapter(List<Contenttable> content) {
        this.content = content;
    }

//    public ListAdapter(Map<String, String> mmap) {
//    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {//设置上下文环境
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        return new ListViewHolder(view,mClickListener);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
//        Test cat = content.get(position);
        Contenttable ct = content.get(position);
        holder.title_name.setText(ct.getCt_title());
        holder.time.setText(ct.getCt_data());
        holder.name.setText(ct.getCt_name());
//        Glide.with(context).load(ct.getImgId()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

}
