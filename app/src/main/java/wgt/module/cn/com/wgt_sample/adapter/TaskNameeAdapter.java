package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wgt.module.cn.com.wgt_sample.R;


public class TaskNameeAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;
    private LayoutInflater inflater;

    public TaskNameeAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.taskname_adapter_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.personname.setText(list.get(position));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.personname)
        TextView personname;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
