package com.sunny.beauty.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunny.beauty.R;

import java.util.Map;

import sunny.basemodel.refresh.BaseRecyclerAdapter;

/**
 * author : wyy
 * time   : 2017/09/11
 * desc   :
 */

public class RVAdapter extends BaseRecyclerAdapter<Map<Object, String>> {
    private Context mContext;

    public RVAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_item, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Map<Object, String> data) {
        if (viewHolder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) viewHolder;
            myHolder.titleTv.setText(data.get("title"));
        }
    }

    class MyHolder extends Holder {
        private TextView titleTv;

        public MyHolder(View convertView) {
            super(convertView);
            titleTv = (TextView) convertView.findViewById(R.id.title_tv);
        }
    }
}
