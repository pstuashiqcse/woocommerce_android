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
import com.mcc.eshopper.activity.product.ProductList;
import com.mcc.eshopper.model.CategoryModel;

import java.util.ArrayList;

public class ProductCategoryAdapter extends RecyclerView.Adapter <ProductCategoryAdapter.ProductCategoryHolder>{

    private Context mContext;
    private ArrayList<CategoryModel> mCategoryList = new ArrayList<>();

    public ProductCategoryAdapter (Context mContext, ArrayList <CategoryModel> mCategoryList){

        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
    }

    public void updateList (ArrayList<CategoryModel> updatedList){

        mCategoryList = updatedList;
        this.notifyDataSetChanged();
    }

    public class ProductCategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView im_category;
        private TextView tv_category;

        public ProductCategoryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            im_category = (ImageView) itemView.findViewById(R.id.im_product_category);
            tv_category = (TextView) itemView.findViewById(R.id.tv_product_category);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, ProductList.class);
            intent.putExtra("selected category", mCategoryList.get(getAdapterPosition()).getName());
            mContext.startActivity(intent);
        }
    }

    @Override
    public ProductCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_category, parent, false);
        return new ProductCategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductCategoryHolder holder, int position) {
        holder.tv_category.setText(mCategoryList.get(position).getName());
        String url = getImageUrl(position);

        Glide.with(mContext)
                .load(url)
                .thumbnail(.1f)
                .into(holder.im_category);
    }

    private String getImageUrl(int position) {
        return mCategoryList.get(position).getImage();
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

}
