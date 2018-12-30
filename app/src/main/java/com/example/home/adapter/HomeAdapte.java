package com.example.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day04_test.R;
import com.example.home.bean.HomeBean;

public class HomeAdapte extends BaseAdapter {
    Context context;
    HomeBean.ResultBean focusBean;
    LayoutInflater mInflater;
    final  int TYPE0 = 0;
    final  int TYPE1 = 1;

    public HomeAdapte(Context context) {
        this.context = context;
    }

    public HomeAdapte(Context context, HomeBean.ResultBean focusBean){
        this.context=context;
        this.focusBean=focusBean;
        mInflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return focusBean.getMlss().size();
    }

    @Override
    public Object getItem(int position) {
        return focusBean.getMlss().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //获取条目类型
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE0;
            case 1:
                return TYPE1;

            default:
        }
        return position%2;
    }
    //条目类型数量
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeAdapte.ViewHolder holder = null;
        int type = getItemViewType(position);
        holder=  new HomeAdapte.ViewHolder();
        if (convertView == null) {
            switch (type){
                case TYPE0:
                    convertView=View.inflate(context,R.layout.item_listview,null);
                    holder.leftimageView  = convertView.findViewById(R.id.lf_imageView);
                    holder.lefttv1  = convertView.findViewById(R.id.lf_textView1);
                    holder.lefttv2  = convertView.findViewById(R.id.lf_textView2);

                    convertView.setTag(holder);
                    break;
                case TYPE1:
                    convertView=View.inflate(context,R.layout.item_listviewr,null);
                    holder.rightimageView  = convertView.findViewById(R.id.rt_imageView);
                    holder.rttv1  = convertView.findViewById(R.id.rt_textView);
                    holder.rttv2  = convertView.findViewById(R.id.rt_textView4);
                    convertView.setTag(holder);
                    break;
            }
        }  else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (HomeAdapte.ViewHolder) convertView.getTag();
        }
        switch (type){
            case 0:
                //ImageLoader加载图片
             /*   ImageLoader.getInstance().displayImage(focusBean.getMlss().
                        get(position).,holder.leftimageView);*/
                //veiw 和数据绑定
                holder.lefttv1.setText(focusBean.getMlss().get(position).getName());
                //holder.lefttv2.setText(focusBean.getData().get(position).getDate());
                return convertView;
            case 1:
                // holder.rttv1.setText(focusBean.getData().get(position).getTitle());
                // holder.rttv2.setText(focusBean.getData().get(position).getDate());;
                return convertView;
            default:
        }
        return convertView;
    }

    private class ViewHolder {
        TextView lefttv1,lefttv2,textView3,rttv1,rttv2;
        ImageView leftimageView,rightimageView;

    }
}
