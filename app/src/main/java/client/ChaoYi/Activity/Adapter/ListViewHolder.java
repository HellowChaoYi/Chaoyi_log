package client.ChaoYi.Activity.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import client.ChaoYi.R;

/**
 * Created by WCY on 2019/3/20.
 */

public class ListViewHolder extends RecyclerView.ViewHolder  {//implements View.OnClickListener

    public CardView cardView;
    @BindView(R.id.head_image)
    public ImageView image;
    @BindView(R.id.head_name)
    public TextView name;
    @BindView(R.id.head_time)
    public TextView time;
    @BindView(R.id.head_title)
    public TextView title_name;
    private OnItemClickListener mListener;// 声明自定义的接口
    public ListViewHolder(View itemView) {//,OnItemClickListener listener
        super(itemView);
//        mListener = listener;
        // 为ItemView添加点击事件
//        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);

        cardView = (CardView) itemView;
//        image = (ImageView) itemView.findViewById(R.id.head_image);
//        name = (TextView) itemView.findViewById(R.id.head_name);
//        time = (TextView) itemView.findViewById(R.id.head_time);
//        title_name = (TextView) itemView.findViewById(R.id.head_title);
    }

//    @Override
//    public void onClick(View v) {
//        mListener.onItemClick(v, getPosition());
//    }
}
