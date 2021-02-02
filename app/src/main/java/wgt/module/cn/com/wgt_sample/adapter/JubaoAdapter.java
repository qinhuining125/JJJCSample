package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import wgt.module.cn.com.wgt_sample.entity.TaskEntity;
import wgt.module.cn.com.wgt_sample.utils.BaseAdapter;

/**
 * Created by skc on 2020/6/17.
 */
public class JubaoAdapter extends BaseAdapter<JubaoAdapter.ViewHolder> {

    private List<TaskEntity.TaskSonEntity> list;
    private Context context;
    private SP sp;


    public JubaoAdapter(List<TaskEntity.TaskSonEntity> list, Context context) {
        super(context);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(getInflater().inflate(R.layout.jubao_adapter_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TaskEntity.TaskSonEntity data = list.get(position);
        Log.e("data.getState()----",""+data.getState());
        switch (data.getState()) {
            case 0:
                holder.taskAdapterType.setText("待处理");
                holder.taskAdapterType.setTextColor(context.getResources().getColor(R.color.task_type_wait));
                holder.taskAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskwait));
                break;
            case 1:
                holder.taskAdapterType.setText("已分配");
                holder.taskAdapterType.setTextColor(context.getResources().getColor(R.color.uploadColor));
                holder.taskAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskfenpei));
                break;
            case 2:
                holder.taskAdapterType.setText("已知晓");
                holder.taskAdapterType.setTextColor(context.getResources().getColor(R.color.task_type_finish));
                holder.taskAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskfinish));
                break;
            case 3:
                holder.taskAdapterType.setText("待受理");
                holder.taskAdapterType.setTextColor(context.getResources().getColor(R.color.task_type_wait));
                holder.taskAdapterType.setBackground(context.getResources().getDrawable(R.drawable.shp_taskwait));
                break;
        }

        switch (data.getState()) {
            case 0://未处理，巡察办主任1008显示指派和已知晓。
                // 当状态为0，新建指派和当前登录用户一致时，显示审批按钮。
                if ( data.getFlows()!=null && data.getFlows().size()!=0 ){
                    if (BaseApplication.userid.equals(data.getFlows().get(0).getReceiveId())) {
                        holder.taskAdapterSl.setVisibility(View.VISIBLE);
                        if (data.getFlows().get(0).getReceiveRoleId() == 1008) {
                            holder.taskAdapterZp.setVisibility(View.VISIBLE);
                        } else {
                            holder.taskAdapterZp.setVisibility(View.GONE);
                        }
//                    holder.taskAdapterFinish.setVisibility(View.GONE);
                    } else {
                        holder.taskAdapterSl.setVisibility(View.GONE);
                        holder.taskAdapterZp.setVisibility(View.GONE);
//                    holder.taskAdapterFinish.setVisibility(View.GONE);
                    }
                }

                break;
            case 1://已分配，主任1008状态已分配，无按钮；巡察组1009，1010，1011显示已知晓按钮
                if (BaseApplication.prm == 1009 || BaseApplication.prm == 1010|| BaseApplication.prm == 1011) {
                    holder.taskAdapterSl.setVisibility(View.VISIBLE);
                    holder.taskAdapterZp.setVisibility(View.GONE);
//                    if (BaseApplication.userid.equals(data.getAcceptUserId())) {
//                        holder.taskAdapterFinish.setVisibility(View.VISIBLE);
//                    } else {
//                        holder.taskAdapterFinish.setVisibility(View.GONE);
//                    }
                } else {
                    holder.taskAdapterSl.setVisibility(View.GONE);
                    holder.taskAdapterZp.setVisibility(View.GONE);
//                    holder.taskAdapterFinish.setVisibility(View.GONE);
                }
                break;
            case 2://已知晓
                holder.taskAdapterSl.setVisibility(View.GONE);
                holder.taskAdapterZp.setVisibility(View.GONE);
//                holder.taskAdapterFinish.setVisibility(View.GONE);
                break;
//            case 3:
//                if (BaseApplication.prm == 1002) {
//                    holder.taskAdapterSl.setVisibility(View.VISIBLE);
//                    holder.taskAdapterZp.setVisibility(View.GONE);
////                    holder.taskAdapterFinish.setVisibility(View.GONE);
//                } else {
//                    holder.taskAdapterSl.setVisibility(View.GONE);
//                    holder.taskAdapterZp.setVisibility(View.GONE);
////                    holder.taskAdapterFinish.setVisibility(View.GONE);
//                }
//                break;
        }
        if (TextUtils.isEmpty(data.getUserName())){
            holder.taskAdapterPerson.setText("匿名举报");
        }else {
            holder.taskAdapterPerson.setText(data.getUserName());
        }
        holder.taskAdapterText.setText(data.getContent());
        holder.taskAdapterTime.setText(long2DateString(data.getCreateTime()));

        holder.taskAdapterSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.SL(data.getId());
            }
        });
        holder.taskAdapterZp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.ZP(data.getId(), data.getContent());
            }
        });
//        holder.taskAdapterFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sp.BJ(data.getId());
//            }
//        });
    }

    private String long2DateString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date(time));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void notifyDataSetChanged(List<String> dataList) {

    }

    public void setTaskSp(SP sp) {
        this.sp = sp;
    }

    public interface SP {
        void SL(String id);

        void ZP(String id, String text);

//        void BJ(String id);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_adapter_person)
        TextView taskAdapterPerson;
        @BindView(R.id.task_adapter_type)
        TextView taskAdapterType;
        @BindView(R.id.task_adapter_text)
        TextView taskAdapterText;
        @BindView(R.id.task_adapter_time)
        TextView taskAdapterTime;
        @BindView(R.id.task_adapter_sl)
        TextView taskAdapterSl;
        @BindView(R.id.task_adapter_zp)
        TextView taskAdapterZp;
        @BindView(R.id.task_adapter_finish)
        TextView taskAdapterFinish;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
