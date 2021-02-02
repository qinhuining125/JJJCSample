package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.entity.TaskEntity;
import wgt.module.cn.com.wgt_sample.utils.BaseAdapter;

public class FlowAdapter extends BaseAdapter<FlowAdapter.ViewHolder> {
    private List<TaskEntity.TaskFlowsEntity> flows;
    private TaskEntity.TaskSonEntity data;
    private Context context;

    public FlowAdapter(Context context,TaskEntity.TaskSonEntity data) {
        super(context);
        this.context = context;
        this.data = data;
        this.flows = data.getFlows();
    }


    public List<TaskEntity.TaskFlowsEntity> getFlows() {
        return flows;
    }

    public void setFlows(List<TaskEntity.TaskFlowsEntity> flows) {
        this.flows = flows;
    }


    @Override
    public void notifyDataSetChanged(List<String> dataList) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        FlowAdapter.ViewHolder viewHolder = null;
        ViewHolder viewHolder = new ViewHolder(getInflater().inflate(R.layout.layout_flows, viewGroup, false));
//        ViewHolder viewHolder2 = new ViewHolder(getInflater().inflate(R.layout.layout_zhixiao, viewGroup, false));
//        ViewHolder viewHolder1 = new ViewHolder(getInflater().inflate(R.layout.layout_fenpei, viewGroup, false));
//        ViewHolder viewHolder0 = new ViewHolder(getInflater().inflate(R.layout.layout_zp, viewGroup, false));
        return viewHolder;
    }
    private String long2DateStringD(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(time));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TaskEntity.TaskFlowsEntity flowsEntity = flows.get(i);
        switch (flows.get(i).getState()){
            case 0:
                viewHolder.layoutZp.setVisibility(View.VISIBLE);
                viewHolder.taskMessageSllayout.setVisibility(View.GONE);
                viewHolder.taskMessageBjlayout.setVisibility(View.GONE);
                String person = TextUtils.isEmpty(data.getUserName())?"匿名举报":data.getUserName();
                viewHolder.taskMessageZp1person.setText("上报人：" + person);
                viewHolder.taskMessageZp1time.setText(long2DateStringD(data.getCreateTime()) + "   上报给" + flowsEntity.getReceiveName());
                if (i==flows.size()-1){
                    viewHolder.taskMessageZp1line.setVisibility(View.GONE);
                }
                break;
            case 1:
                viewHolder.taskMessageSllayout.setVisibility(View.VISIBLE);
                viewHolder.layoutZp.setVisibility(View.GONE);
                viewHolder.taskMessageBjlayout.setVisibility(View.GONE);

                viewHolder.taskMessageSllayout.setVisibility(View.VISIBLE);
                viewHolder.taskMessageSlperson.setText("接受人：" + flowsEntity.getReceiveName());
                viewHolder.taskMessageSltime.setText(long2DateStringD(flowsEntity.getCreateTime()) + "   已分配");
                if (i==flows.size()-1){
                    viewHolder.taskMessageSlline.setVisibility(View.GONE);
                }
                break;
            case 2:
//                        Log.e("case=2","state=="+dataEntity.getState());
                viewHolder.taskMessageBjlayout.setVisibility(View.VISIBLE);
                viewHolder.layoutZp.setVisibility(View.GONE);
                viewHolder.taskMessageSllayout.setVisibility(View.GONE);

                viewHolder.taskMessageBjlayout.setVisibility(View.VISIBLE);
                viewHolder.taskMessageBjperson.setText("反馈人：" + flowsEntity.getReceiveName());
                viewHolder.taskMessageBjtime.setText(long2DateStringD(data.getCreateTime()) + "   已知晓");
//                viewHolder.taskMessageBjtext.setText(flowsEntity.getRemark());
                //处理最后一根线
                if (i==flows.size()-1){
                    viewHolder.taskMessageBjline.setVisibility(View.GONE);
                    viewHolder.taskMessageBjline2.setVisibility(View.GONE);
                }
                break;
            case 3://已知晓

                break;
            case 4://已转办

                break;
        }
    }

    @Override
    public int getItemCount() {
        return flows.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.task_message_zp1time)
        TextView taskMessageZp1time;
        @BindView(R.id.task_message_zp1line)
        View taskMessageZp1line;
        @BindView(R.id.task_message_zp1person)
        TextView taskMessageZp1person;
        @BindView(R.id.task_message_sltime)
        TextView taskMessageSltime;
        @BindView(R.id.task_message_slline)
        View taskMessageSlline;
        @BindView(R.id.task_message_slperson)
        TextView taskMessageSlperson;
        @BindView(R.id.task_message_sllayout)
        LinearLayout taskMessageSllayout;
        @BindView(R.id.task_message_bjtime)
        TextView taskMessageBjtime;
        @BindView(R.id.task_message_bjperson)
        TextView taskMessageBjperson;
//        @BindView(R.id.task_message_bjtext)
//        TextView taskMessageBjtext;
        @BindView(R.id.task_message_bjlayout)
        LinearLayout taskMessageBjlayout;
        @BindView(R.id.task_message_bjline)
        View taskMessageBjline;
         @BindView(R.id.task_message_bjline2)
         View taskMessageBjline2;
        @BindView(R.id.layout_zp)
        LinearLayout layoutZp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

}
