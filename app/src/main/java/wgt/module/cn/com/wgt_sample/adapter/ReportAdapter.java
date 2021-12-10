package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.utils.BaseAdapter;

/**
 * Created by skc on 2020/6/17.
 */
public class ReportAdapter extends BaseAdapter<ReportAdapter.ViewHolder> {

    private List<ReportEntity.ReportSonEntity> list;
    private Context context;
    private SP sp;

    public ReportAdapter(List<ReportEntity.ReportSonEntity> list, Context context) {
        super(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public void notifyDataSetChanged(List<String> dataList) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(getInflater().inflate(R.layout.report_adapter_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ReportEntity.ReportSonEntity data = list.get(position);
        switch (data.getState()) {
            case 0:
                holder.reportAdapterType.setText("待处理");
                if (BaseApplication.prm == 1003 || BaseApplication.prm == 1004 || BaseApplication.prm == 1005 || BaseApplication.prm == 1012) {
                    if (BaseApplication.userid.equals(data.getReceiveId())) {
                        holder.reportAdapterJs.setVisibility(View.VISIBLE);
                        holder.reportAdapterZhixiao.setVisibility(View.VISIBLE);
                        holder.reportAdapterZhuanban.setVisibility(View.VISIBLE);
                        holder.reportAdapterFinish.setVisibility(View.GONE);
                    }else{
                        holder.reportAdapterJs.setVisibility(View.GONE);
                        holder.reportAdapterZhixiao.setVisibility(View.GONE);
                        holder.reportAdapterZhuanban.setVisibility(View.GONE);
                        holder.reportAdapterFinish.setVisibility(View.GONE);
                    }
                } else if( (BaseApplication.prm >=3000  && BaseApplication.prm<=3999)){
                    holder.reportAdapterZhixiao.setVisibility(View.GONE);
                    holder.reportAdapterZhuanban.setVisibility(View.GONE);
                    holder.reportAdapterJs.setVisibility(View.VISIBLE);
                    holder.reportAdapterFinish.setVisibility(View.GONE);
                }else {
                    holder.reportAdapterZhixiao.setVisibility(View.GONE);
                    holder.reportAdapterZhuanban.setVisibility(View.GONE);
                    holder.reportAdapterJs.setVisibility(View.GONE);
                    holder.reportAdapterFinish.setVisibility(View.GONE);
                }
                holder.reportAdapterType.setTextColor(context.getResources().getColor(R.color.task_type_wait));
                holder.reportAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskwait));
                break;
            case 1:
                holder.reportAdapterType.setText("已接受");
                if (BaseApplication.userid.equals(data.getReceiveId())) {
                    holder.reportAdapterJs.setVisibility(View.GONE);
                    holder.reportAdapterZhixiao.setVisibility(View.GONE);
                    holder.reportAdapterZhuanban.setVisibility(View.GONE);
                    holder.reportAdapterFinish.setVisibility(View.VISIBLE);
                } else {
                    holder.reportAdapterZhixiao.setVisibility(View.GONE);
                    holder.reportAdapterZhuanban.setVisibility(View.GONE);
                    holder.reportAdapterJs.setVisibility(View.GONE);
                    holder.reportAdapterFinish.setVisibility(View.GONE);
                }
                holder.reportAdapterType.setTextColor(context.getResources().getColor(R.color.task_type_work));
                holder.reportAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskwork));
                break;
            case 2:
                holder.reportAdapterType.setText("已办结");
                holder.reportAdapterZhixiao.setVisibility(View.GONE);
                holder.reportAdapterZhuanban.setVisibility(View.GONE);
                holder.reportAdapterJs.setVisibility(View.GONE);
                holder.reportAdapterFinish.setVisibility(View.GONE);
                holder.reportAdapterType.setTextColor(context.getResources().getColor(R.color.task_type_finish));
                holder.reportAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskfinish));
                break;
            case 3://已知晓
                holder.reportAdapterType.setText("已知晓");
                holder.reportAdapterJs.setVisibility(View.GONE);
                holder.reportAdapterFinish.setVisibility(View.GONE);
                holder.reportAdapterZhixiao.setVisibility(View.GONE);
                holder.reportAdapterZhuanban.setVisibility(View.GONE);
                holder.reportAdapterType.setTextColor(context.getResources().getColor(R.color.task_type_zhixiao));
                holder.reportAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskzhixiao));
                break;

            case 4://已转办
                holder.reportAdapterType.setText("已转办");
                holder.reportAdapterJs.setVisibility(View.GONE);
                holder.reportAdapterFinish.setVisibility(View.GONE);
                holder.reportAdapterZhixiao.setVisibility(View.GONE);
                holder.reportAdapterZhuanban.setVisibility(View.GONE);
                holder.reportAdapterType.setTextColor(context.getResources().getColor(R.color.task_type_zhuanban));
                holder.reportAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskzhuanban));
                break;

        }

        holder.reportAdapterTime.setText(long2DateString(data.getCreateTime()));
        holder.reportAdapterText.setText(data.getClueDescribe());
        holder.reportAdapterPerson.setText(data.getClueNo());

        holder.reportAdapterJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.JS(data.getId());

            }
        });
        holder.reportAdapterZhixiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.ZX(data.getId());
            }
        });
        holder.reportAdapterZhuanban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.ZB(data.getId(),data.getClueDescribe());
            }
        });
        holder.reportAdapterFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.Finish(data.getId());
            }
        });
    }

    private String long2DateString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date(time));
    }

    public void setReportSp(SP sp) {
        this.sp = sp;
    }

    public interface SP {
        void JS(String id);
        void ZB(String id,String text);
        void ZX(String id);
        void Finish(String id);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.report_adapter_person)
        TextView reportAdapterPerson;
        @BindView(R.id.report_adapter_type)
        TextView reportAdapterType;
        @BindView(R.id.report_adapter_text)
        TextView reportAdapterText;
        @BindView(R.id.report_adapter_time)
        TextView reportAdapterTime;
        @BindView(R.id.report_adapter_js)
        TextView reportAdapterJs;
        @BindView(R.id.report_adapter_finish)
        TextView reportAdapterFinish;
        @BindView(R.id.task_adapter_zx)
        TextView reportAdapterZhixiao;
        @BindView(R.id.task_adapter_zb)
        TextView reportAdapterZhuanban;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
