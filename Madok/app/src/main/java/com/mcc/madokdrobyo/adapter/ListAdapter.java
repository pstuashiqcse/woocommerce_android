package com.mcc.madokdrobyo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.objects.Content;
import com.mcc.madokdrobyo.objects.ContentFile;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private ArrayList<Content> contents;
    private Context mContext;

    public ListAdapter(Context mContext, ArrayList<Content> contents){
        this.mContext = mContext;
        this.contents = contents;
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContentHolder holder;
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (row == null){
            row = inflater.inflate(R.layout.content_list, null);
            holder = new ContentHolder(row);
            row.setTag(holder);
            holder.tvContent.setText(contents.get(position).getDetails());
        }
        return row;
    }

    public class ContentHolder{
        TextView tvContent;

        public ContentHolder(View view){
            tvContent = (TextView) view.findViewById(R.id.tvContent);
        }

    }
}
