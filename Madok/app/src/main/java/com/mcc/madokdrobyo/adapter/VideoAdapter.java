package com.mcc.madokdrobyo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mcc.madokdrobyo.R;
import com.mcc.madokdrobyo.constants.AppConsts;
import com.mcc.madokdrobyo.objects.ContentFile;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>  {
    private ArrayList<ContentFile> videos;
    private Context mContext;

    public VideoAdapter(Context mContext, ArrayList<ContentFile> videos){
        this.mContext = mContext;
        this.videos = videos;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_video_adapter, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, final int position) {

        holder.tvVideo.setText(videos.get(position).getTitle());
        holder.position = position;
        Glide.with(mContext)
                .load(getThumbnailUrl(getVideoId(position)))
                .thumbnail(.1f)
                .into(holder.imVideo);
    }

    private String getVideoId(int position) {
        String [] videoLink = videos.get(position).getYtLink().split("v=");
        return videoLink[1];
    }

    private String getThumbnailUrl(String videoId) {
        return AppConsts.YOUTUBE_LINK + videoId + AppConsts.YOUTUBE_IMAGE;
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvVideo;
        ImageView imVideo;
        int position;

        public VideoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvVideo = (TextView) itemView.findViewById(R.id.tvVideo);
            imVideo = (ImageView) itemView.findViewById(R.id.imVideo);String videoId = getVideoId(position);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videos.get(position).getYtLink().toString()));
            intent.putExtra("force_fullscreen", true);
            mContext.startActivity(intent);
        }

    }
}


