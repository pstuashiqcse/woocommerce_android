package com.mcc.madokdrobyo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.objects.MainMenu;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter{

    private ArrayList<MainMenu> menus = new ArrayList<>();
    private Context mContext;

    public GridAdapter(Context context, ArrayList<MainMenu> menus){
        this.mContext = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
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
        GridHolder holder;
        View grid = convertView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (grid == null) {
            grid = inflater.inflate(R.layout.content_grid_view, null);

            holder = new GridHolder(grid);
            holder.textView.setText(menus.get(position).getName().toString());
            Glide.with(mContext)
                    .load(menus.get(position).getImage())
                    .into(holder.imageView);

        }
        return grid;
    }

    public class GridHolder {
        TextView textView;
        ImageView imageView;

        public GridHolder(View view){
            textView = (TextView) view.findViewById(R.id.grid_text);
            imageView = (ImageView) view.findViewById(R.id.grid_image);
        }
    }

}
