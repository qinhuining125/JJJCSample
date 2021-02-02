package wgt.module.cn.com.wgt_sample.report;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportSubmitRequestEntity;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class NewReportActivity extends AppCompatActivity implements ReportContract.addView {

    @BindView(R.id.newreport_type)
    TextView newreportType;
    @BindView(R.id.newreport_text)
    EditText newreportText;

    private List<NewReportEntity> dataList;
    private String type;
    private QMUITipDialog dataDialog, typeDialog;
    private ReportContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fullnewreport);
        ButterKnife.bind(this);

        onCreateNewDialog();
        initView();
        initData();
    }

    private void initData() {
        typeDialog.show();
        mPresenter.getReportType(BaseApplication.prm + "");
    }

    private void initView() {
        mPresenter = new ReportPresenter(this, this);
        dataList = new ArrayList<>();
    }

    @OnClick({R.id.task_back, R.id.newreport_choose, R.id.newreport_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.newreport_ok:
                if (TextUtils.isEmpty(type)) {
                    Toast.makeText(this, "请选择问题类型 (点击屏幕右上方蓝色按钮选择)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(newreportText.getText().toString())) {
                    newreportText.setText("暂无描述");
                }

                dataDialog.show();
                mPresenter.getAddReport(new Gson().toJson(new ReportSubmitRequestEntity(type, newreportText.getText().toString())));
                break;
            case R.id.task_back:
                finish();
                break;
            case R.id.newreport_choose:
                if (dataList != null && dataList.size() > 0) {
                    FullWGYReportTypeChoose fullWGYReportTypeChoose = new FullWGYReportTypeChoose(this, dataList);
                    fullWGYReportTypeChoose.setCanceledOnTouchOutside(false);
                    fullWGYReportTypeChoose.setCancelable(false);
                    fullWGYReportTypeChoose.show();
                    fullWGYReportTypeChoose.setType(new FullWGYReportTypeChoose.setReportType() {
                        @Override
                        public void chooseType(List<NewReportEntity> collcationFull) {
                            dataList.clear();
                            dataList.addAll(collcationFull);

                            StringBuffer textBuffer = new StringBuffer();
                            StringBuffer typeBuffer = new StringBuffer();
                            for (int i = 0; i < collcationFull.size(); i++) {
                                NewReportEntity data = collcationFull.get(i);
                                if (data.isCheck()) {
                                    textBuffer.append(data.getContent() + "\n\n");
                                    typeBuffer.append(data.getSortNo() + ",");
                                }
                            }
                            if (typeBuffer.length() > 0) {
                                type = typeBuffer.deleteCharAt(typeBuffer.length() - 1).toString();
                            } else {
                                type = "";
                            }
                            newreportType.setText(textBuffer.toString());
                        }
                    });
                } else {
                    Toast.makeText(this, "未查询到上报类型", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void setReportType(List<NewReportEntity> data) {
        if (data != null && data.size() > 0) {
            dataList.addAll(data);
        }
    }

    @Override
    public void setAddReport(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void setPresenter(ReportContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void dataDialogDissmis() {
        dataDialog.dismiss();
        typeDialog.dismiss();
    }

    @Override
    public void ERROR(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
        dataDialogDissmis();
    }

    /**
     * 等待框。
     */
    private void onCreateNewDialog() {
        dataDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在提交...")
                .create();
        dataDialog.setCancelable(false);
        typeDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("加载中...")
                .create();
        typeDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
