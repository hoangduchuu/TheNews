package com.example.hoang.thenews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.myapplication.R;
import com.example.hoang.thenews.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hoang on 10/22/16.
 */

public class ArticleAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<News> mArrayList;
    int mLayout;
    public ArticleAdapter(Context context, int layout, ArrayList<News> docArrayList){
        mContext = context;
        mArrayList = docArrayList;
        mLayout = layout;
        notifyDataSetChanged();
    }
    private class ViewHolder{
        ImageView imgImgImg;
        TextView textViewTexView;
    }
    public void addAllItem(){
        mArrayList.addAll(mArrayList);
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View viewRow = convertView;
        if (viewRow == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewRow = inflater.inflate(R.layout.view_layout, parent, false);
            ViewHolder holder = new ViewHolder();

            holder.imgImgImg = (ImageView) viewRow.findViewById(R.id.ivImg);
            holder.textViewTexView = (TextView) viewRow.findViewById(R.id.tvSnip);
            viewRow.setTag(holder);
        }
        final ViewHolder holder = (ViewHolder) viewRow.getTag();
        holder.textViewTexView.setText(mArrayList.get(position).getSnippet());
        Picasso.with(mContext).load(mArrayList.get(position).getImageUrlvcl()).into(holder.imgImgImg);
        return viewRow;
    }
}
