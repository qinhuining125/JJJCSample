package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jelly.mango.MultiplexImage;
import com.jelly.mango.progressGlide.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.utils.BaseAdapter;

public class VideoAdapter extends BaseAdapter<VideoAdapter.ViewHolder> {
    public void setImageList(List<MultiplexImage> imageList) {
        this.imageList = imageList;
    }

    public List<MultiplexImage> getImageList() {
        return imageList;
    }

    private List<MultiplexImage> imageList;

    private Context context;
    private OnRecyclerItemClickListener itemClickListener;


    public VideoAdapter(List<MultiplexImage> imageList, Context context) {
        super(context);
        this.imageList = imageList;
        this.context = context;

    }

    @Override
    public void notifyDataSetChanged(List<String> dataList) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VideoAdapter.ViewHolder(getInflater().inflate(R.layout.act_messagevideo, viewGroup, false),itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,  int i) {
//
        GlideApp.with(context).load(imageList.get(i).getTPath()).centerCrop().error(R.drawable.error).into(viewHolder.photoView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setItemClickListener(OnRecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.video_view_message)
        ImageView photoView;
        private OnRecyclerItemClickListener itemClickListener;


        public ViewHolder(@NonNull View itemView,OnRecyclerItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
//            photoView.isZoomEnabled();

        }

        @Override
        public void onClick(View view) {
            if(itemClickListener == null) return;
            itemClickListener.click(itemView,getAdapterPosition());
        }
    }
}

