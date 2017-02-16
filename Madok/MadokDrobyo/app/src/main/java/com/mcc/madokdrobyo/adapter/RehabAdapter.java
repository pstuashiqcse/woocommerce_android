package com.mcc.madokdrobyo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.objects.RehabInfoModel;


public class RehabAdapter extends RecyclerView.Adapter<RehabAdapter.RehabViewHolder> {
    private RehabInfoModel rehab;

    public RehabAdapter(RehabInfoModel rehab){
        this.rehab = rehab;
    }

    @Override
    public RehabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_rehab_info_dialog, parent, false);
        return new RehabViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RehabViewHolder holder, int position) {

        holder.tvAddress.setText(rehab.getRehabAdderss());
        holder.tvcontact.setText(rehab.getRehabContactInfo());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class RehabViewHolder extends RecyclerView.ViewHolder{
        public TextView tvAddress;
        public TextView tvcontact;
        public ImageView imAddress;
        public ImageView imContact;

        public RehabViewHolder(View itemView) {
            super(itemView);

            imAddress = (ImageView) itemView.findViewById(R.id.imAddress);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);

            imContact = (ImageView) itemView.findViewById(R.id.imContact);
            tvcontact = (TextView) itemView.findViewById(R.id.tvContact);
        }
    }
}
