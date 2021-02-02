package wgt.module.cn.com.wgt_sample.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;


public class NewReportAdapter extends BaseAdapter {

    private List<NewReportEntity> list;
    private static SparseBooleanArray isSelected;
    private Context context;
    private LayoutInflater inflater;

    public NewReportAdapter(List<NewReportEntity> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        isSelected = new SparseBooleanArray();
        initData();
    }

    public static SparseBooleanArray getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(SparseBooleanArray isSelected) {
        NewReportAdapter.isSelected = isSelected;
    }

    private void initData() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, list.get(i).isCheck());
        }
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
            convertView = inflater.inflate(R.layout.newreport_adapter_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final NewReportEntity data = list.get(position);
        holder.newreportCheckbox.setText(data.getContent());
        holder.newreportCheckbox.setChecked(isSelected.get(position));

        holder.newreportCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    isSelected.put(position,isChecked);
                    data.setCheck(isChecked);
                }
            }
        });

        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.newreport_checkbox)
        CheckBox newreportCheckbox;
        @BindView(R.id.newreport_layout)
        LinearLayout newreportLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
