package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pili.pldroid.player.widget.PLVideoTextureView;


import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.report.NewReportActivity;
import wgt.module.cn.com.wgt_sample.utils.BaseAdapter;
import wgt.module.cn.com.wgt_sample.utils.UriUtils;

import static android.support.constraint.Constraints.TAG;

public class ReportUploadAudioAdapter extends BaseAdapter<ReportUploadAudioAdapter.ViewHolder> {

    private List<String> audioList;
    private List<String> upAudioList = null;

    private Context context;
    private OnRecyclerItemClickListener itemClickListener;
    private NewReportActivity newReport;


    private List<File> fileAudioList = null;


    private ProgressBar progressBar;
    private MyHandler myHandler;//新写的Handler类

    private TextView tv_main_desc;//显示文本的控件

    private PLVideoTextureView plVideoTextureView;

    long current;
    long ttl;
    float percent;//当前进度

    DecimalFormat df1 = new DecimalFormat("0.0000");
    DecimalFormat df2 = new DecimalFormat("0.00");


    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public TextView getTv_main_desc() {
        return tv_main_desc;
    }

    public void setTv_main_desc(TextView tv_main_desc) {
        this.tv_main_desc = tv_main_desc;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public PLVideoTextureView getPlVideoTextureView() {
        return plVideoTextureView;
    }

    public void setPlVideoTextureView(PLVideoTextureView plVideoTextureView) {
        this.plVideoTextureView = plVideoTextureView;
    }



    public List<String> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<String> audioList) {
        this.audioList = audioList;
    }

    public ReportUploadAudioAdapter(Context context, NewReportActivity newReport) {
        super(context);
        this.context = context;
        this.newReport = newReport;
    }

    public ReportUploadAudioAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void notifyDataSetChanged(List<String> dataList) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ReportUploadAudioAdapter.ViewHolder(getInflater().
                inflate(R.layout.act_task_upload_audio, viewGroup, false),itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//        Picasso.get().load(videoList.get(i)).resizeDimen(R.dimen.image_width,R.dimen.image_height).centerCrop().error(R.drawable.error).into(viewHolder.photoView);
        final PLVideoTextureView plVideoTextureView = viewHolder.plVideoTextureView;

        final ImageView audioStart = viewHolder.audioStart;
        final ImageView audioPause = viewHolder.audioPause;


        final ProgressBar progressBar = viewHolder.progressBar;
        final TextView tv_main_desc = viewHolder.tv_main_desc;


        fileAudioList = newReport.getFileAudioList();

        this.setPlVideoTextureView(plVideoTextureView);



        Log.d(TAG, "onBindViewHolder: "+audioList.get(i));
        File file = fileAudioList.get(i);
        String fileName = file.getName();
        Uri audioUri =Uri.parse(audioList.get(i));
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();//实例化MediaMetadataRetriever对象
        mmr.setDataSource(context,audioUri);
        Bitmap bitmap = mmr.getFrameAtTime(0); //0表示首帧图片
        mmr.release();
        String path= UriUtils.getFilePathFromURI(context,audioUri);
        viewHolder.plVideoTextureView.setVideoPath(path);
        final ImageView v1 = viewHolder.photoView;
        v1.setTag(fileName);
        v1.setImageBitmap(bitmap);
        v1.setVisibility(View.VISIBLE);


        viewHolder.audioStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myHandler = new MyHandler();

                Log.e("点击了start","点击了start按钮");
                plVideoTextureView.start();

                audioStart.setVisibility(View.GONE);
                audioPause.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
//                tv_main_desc.setVisibility(View.GONE);

                if(0==percent){//如果当前进度为0
                    new MyThread().start();//开启线程
                }

            }
        });


        viewHolder.audioPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.e("点击了pause","点击了pause按钮");
                plVideoTextureView.pause();
                audioPause.setVisibility(View.GONE);
                audioStart.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
//                tv_main_desc.setVisibility(View.GONE);
                MyThread.currentThread().interrupt();

            }
        });

        viewHolder.audioClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.e("点击了关闭","点击了删除按钮");
                audioList.remove(i);

                notifyItemRemoved(i);//刷新被删除的地方
                notifyItemRangeChanged(i, getItemCount()); //刷新被删除数据，以及其后面的数据
                upAudioList = newReport.getUploadAudioList();
                if (upAudioList!=null && upAudioList.size()!=0){
                    upAudioList.remove(i);
                    newReport.setUploadAudioList(upAudioList);
                }

                fileAudioList = newReport.getFileAudioList();
                if (fileAudioList!=null && fileAudioList.size()!=0){
                    fileAudioList.remove(i);
                    newReport.setFileAudioList(fileAudioList);
                }

            }
        });

    }




    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int code=msg.what;//接受处理码
            switch (code){
                case 1:
                    percent++;
                    current = plVideoTextureView.getCurrentPosition();
                    ttl = plVideoTextureView.getDuration();
                    String str_percent = df1.format((float)current / ttl );
                    percent =Float.parseFloat(str_percent) * 100;
//                    progressBar.setProgress(p);//给进度条的当前进度赋值
                    tv_main_desc.setText(df2.format(percent)+"%");//显示当前进度为多少
                    break;
            }
        }
    }

    public class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(true){
                try {
                    Thread.sleep(100);//使线程休眠0.1秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(current==ttl){//当前进度等于总进度时退出循环
                    percent=0;
                    break;
                }
                Message msg=new Message();
                msg.what=1;
                myHandler.handleMessage(msg);//发送处理码
            }
        }
    }


    @Override
    public int getItemCount() {
        return audioList.size();
    }
    public void setItemClickListener(OnRecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.audio_view)
        ImageView photoView;

        @BindView(R.id.audio_view_close)
        ImageView audioClose;

        @BindView(R.id.audio_view_start)
        ImageView audioStart;

        @BindView(R.id.audio_view_pause)
        ImageView audioPause;

        @BindView(R.id.progressBar)
        ProgressBar progressBar;


        @BindView(R.id.tv_main_desc)
        TextView tv_main_desc;

        @BindView(R.id.plvAudio)
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
