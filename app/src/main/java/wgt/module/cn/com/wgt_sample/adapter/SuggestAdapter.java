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
import wgt.module.cn.com.wgt_sample.entity.SuggestEntity;
import wgt.module.cn.com.wgt_sample.utils.BaseAdapter;

/**
 * Created by skc on 2020/6/17.
 */
public class SuggestAdapter extends BaseAdapter<SuggestAdapter.ViewHolder> {

    private List<SuggestEntity.SuggestSonEntity> list;
    private Context context;

    public SuggestAdapter(List<SuggestEntity.SuggestSonEntity> list, Context context) {
        super(context);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(getInflater().inflate(R.layout.suggest_adapter_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SuggestEntity.SuggestSonEntity entity = list.get(position);
        holder.suggestAdapterPerson.setText(entity.getUserName());
        holder.suggestAdapterTime.setText(long2DateString(entity.getCreateTime()));
        holder.suggestAdapterContent.setText(entity.getContent());
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.suggest_adapter_person)
        TextView suggestAdapterPerson;
        @BindView(R.id.suggest_adapter_time)
        TextView suggestAdapterTime;
        @BindView(R.id.suggest_adapter_content)
        TextView suggestAdapterContent;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
