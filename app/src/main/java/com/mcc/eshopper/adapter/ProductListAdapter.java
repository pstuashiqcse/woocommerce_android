package com.mcc.eshopper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcc.eshopper.R;
import com.mcc.eshopper.activity.product.ProductDetails;
import com.mcc.eshopper.model.ProductListModel;

import java.util.ArrayList;

/**
 * Created by nitul on 4/3/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter <ProductListAdapter.ProductListHolder>{
    private ArrayList <ProductListModel> products;
    private Context mContext;

    public ProductListAdapter (Context mContext, ArrayList <ProductListModel> products) {
        this.mContext = mContext;
        this.products = products;
    }

    public void updateList(ArrayList <ProductListModel> productlist){
        products = productlist;
        notifyDataSetChanged();
    }

    @Override
    public ProductListAdapter.ProductListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_productlist, parent, false);
        return new ProductListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.ProductListHolder holder, int position) {
        holder.tv_productlist.setText(products.get(position).getName());
        String url = products.get(position).getImage();

        Glide.with(mContext)
                .load(url)
                .thumbnail(.1f)
                .into(holder.im_productlist);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView im_productlist;
        private TextView tv_productlist;private int mProductId;

        public ProductListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            im_productlist = (ImageView) itemView.findViewById(R.id.im_productlist);
            tv_productlist = (TextView) itemView.findViewById(R.id.tv_productlist);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, ProductDetails.class);
            intent.putExtra("id", products.get(getAdapterPosition()).getId());
            mContext.startActivity(intent);
        }
    }
}
