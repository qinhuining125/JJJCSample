package wgt.module.cn.com.wgt_sample.report;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.NewReportAdapter;
import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;

/**
 * Created by skc on 2020/6/17.
 */
public class FullWGYReportTypeChoose extends Dialog {

    public View view;
    // buttnife绑定。
    Unbinder unbinder;
    @BindView(R.id.asdf)
    ListView asdf;
    @BindView(R.id.reporttypechoose_back)
    ImageView reporttypechooseBack;

    private NewReportAdapter adapter;
    private List<NewReportEntity> type = new ArrayList<>();
    private setReportType srt;

    public FullWGYReportTypeChoose(Context context, List<NewReportEntity> type) {
        super(context);
        this.type.addAll(type);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = LayoutInflater.from(getContext()).inflate(R.layout.reportwgytypechoose_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        adapter = new NewReportAdapter(type, getContext());
        asdf.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }


    public void setType(setReportType srt) {
        this.srt = srt;
    }

    @OnClick({R.id.reportType_ok,R.id.reporttypechoose_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reportType_ok:
                srt.chooseType(type);
                dismiss();
                break;
            case R.id.reporttypechoose_back:
                dismiss();
                break;
        }

    }

    public interface setReportType {
        void chooseType(List<NewReportEntity> type);
    }
}
