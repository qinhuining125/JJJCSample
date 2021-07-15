package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.report.NewReportActivity;
import wgt.module.cn.com.wgt_sample.utils.BaseAdapter;

public class ReportUploadImageAdapter extends BaseAdapter<ReportUploadImageAdapter.ViewHolder> {



    private List<String> imageList;
    private List<String> upImageList = null;
    private Context context;
    private OnRecyclerItemClickListener itemClickListener;
    private NewReportActivity newReport;
    private List<File> fileList = null;


    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public ReportUploadImageAdapter(Context context,NewReportActivity newReport) {
        super(context);
        this.context = context;
        this.newReport = newReport;

    }

    public ReportUploadImageAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void notifyDataSetChanged(List<String> dataList) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ReportUploadImageAdapter.ViewHolder(getInflater().
                inflate(R.layout.act_task_upload_image, viewGroup, false),itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Picasso.get().load(imageList.get(i)).resizeDimen(R.dimen.image_width,R.dimen.image_height).centerCrop().error(R.drawable.error).into(viewHolder.photoView);
        viewHolder.photoClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.e("点击了关闭","点击了删除按钮");
                imageList.remove(i);
                notifyItemRemoved(i);//刷新被删除的地方
                notifyItemRangeChanged(i, getItemCount()); //刷新被删除数据，以及其后面的数据
                //更新uploadimageList得数据。
                upImageList = newReport.getUploadImageList();
                if (upImageList!=null && upImageList.size()!=0){
                    upImageList.remove(i);
                    newReport.setUploadImageList(upImageList);
                }

                fileList = newReport.getFileList();
                if (fileList!=null && fileList.size()!=0){
                    fileList.remove(i);
                    newReport.setFileList(fileList);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
    public void setItemClickListener(OnRecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.photo_view)
        ImageView photoView;

        @BindView(R.id.photo_view_close)
        ImageView photoClose;
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
