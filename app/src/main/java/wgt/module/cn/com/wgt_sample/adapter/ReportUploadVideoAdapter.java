package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.report.NewReportActivity;
import wgt.module.cn.com.wgt_sample.report.VideoViewActivity;
import wgt.module.cn.com.wgt_sample.utils.BaseAdapter;
import wgt.module.cn.com.wgt_sample.utils.UriUtils;

import static android.support.constraint.Constraints.TAG;

public class ReportUploadVideoAdapter extends  BaseAdapter<ReportUploadVideoAdapter.ViewHolder>{

    private List<String> videoList;
    private List<String> upVideoList = null;

    private Context context;
    private OnRecyclerItemClickListener itemClickListener;
    private NewReportActivity newReport;

    private List<File> fileVideoList = null;

    public List<String> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<String> videoList) {
        this.videoList = videoList;
    }

    public ReportUploadVideoAdapter(Context context, NewReportActivity newReport) {
        super(context);
        this.context = context;
        this.newReport = newReport;
    }

    public ReportUploadVideoAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void notifyDataSetChanged(List<String> dataList) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ReportUploadVideoAdapter.ViewHolder(getInflater().
                inflate(R.layout.act_task_upload_video, viewGroup, false),itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//        Picasso.get().load(videoList.get(i)).resizeDimen(R.dimen.image_width,R.dimen.image_height).centerCrop().error(R.drawable.error).into(viewHolder.photoView);


        final ImageView videoStart = viewHolder.videoStart;
        final ImageView videoPause = viewHolder.videoPause;

        fileVideoList = newReport.getFileVideoList();
        Log.d(TAG, "onBindViewHolder: "+videoList.get(i));
        File file = fileVideoList.get(i);
        String fileName = file.getName();
        final Uri videoUri =Uri.parse(videoList.get(i));
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();//实例化MediaMetadataRetriever对象
        mmr.setDataSource(context,videoUri);
        Bitmap bitmap = mmr.getFrameAtTime(0); //0表示首帧图片
        mmr.release();
        String path= UriUtils.getFilePathFromURI(context,videoUri);
        viewHolder.plVideoTextureView.setVideoPath(path);
        final ImageView v1 = viewHolder.photoView;
        v1.setTag(fileName);
        v1.setImageBitmap(bitmap);
        v1.setVisibility(View.VISIBLE);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoViewActivity.start(v1.getContext(), videoList.get(i));
            }
        });



        viewHolder.photoClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.e("点击了关闭","点击了删除按钮");
                videoList.remove(i);

                notifyItemRemoved(i);//刷新被删除的地方
                notifyItemRangeChanged(i, getItemCount()); //刷新被删除数据，以及其后面的数据
                upVideoList = newReport.getUploadVideoList();
                if (upVideoList!=null && upVideoList.size()!=0){
                    upVideoList.remove(i);
                    newReport.setUploadVideoList(upVideoList);
                }

                fileVideoList = newReport.getFileVideoList();
                if (fileVideoList!=null && fileVideoList.size()!=0){
                    fileVideoList.remove(i);
                    newReport.setFileVideoList(fileVideoList);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
    public void setItemClickListener(OnRecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.upload_video_view)
        ImageView photoView;

        @BindView(R.id.video_view_close)
        ImageView photoClose;

        @BindView(R.id.video_view_start)
        ImageView videoStart;

        @BindView(R.id.video_view_pause)
        ImageView videoPause;

        @BindView(R.id.plvVideo)
        PLVideoTextureView plVideoTextureView;
        private OnRecyclerItemClickListener itemClickListener;


        public ViewHolder(@NonNull View itemView,OnRecyclerItemClickListener itemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if(itemClickListener == null) return;
            itemClickListener.click(itemView,getAdapterPosition());
        }
    }

}
