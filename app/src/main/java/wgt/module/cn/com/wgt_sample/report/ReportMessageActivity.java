package wgt.module.cn.com.wgt_sample.report;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportFinishRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportWorkRequestEntity;
import wgt.module.cn.com.wgt_sample.task.FullTaskFinish;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ReportMessageActivity extends AppCompatActivity implements ReportContract.messageView {

    @BindView(R.id.reportmessage_person)
    TextView reportmessagePerson;
    @BindView(R.id.reportmessage_type)
    TextView reportmessageType;
    @BindView(R.id.reportmessage_text)
    TextView reportmessageText;
    @BindView(R.id.reportmessage_time)
    TextView reportmessageTime;
    @BindView(R.id.reportmessage_js)
    TextView reportmessageJs;
    @BindView(R.id.reportmessage_zhixiao)
    TextView reportmessageZX;
    @BindView(R.id.reportmessage_zhuanban)
    TextView reportmessageZB;

    @BindView(R.id.reportmessage_finish)
    TextView reportmessageFinish;
    @BindView(R.id.reportmessage_sbtime)
    TextView reportmessageSbtime;
    @BindView(R.id.reportmessage_sbline)
    View reportmessageSbline;
    @BindView(R.id.reportmessage_sbperson)
    TextView reportmessageSbperson;
    @BindView(R.id.reportmessage_jstime)
    TextView reportmessageJstime;
    @BindView(R.id.reportmessage_jsline)
    View reportmessageJsline;
    @BindView(R.id.reportmessage_jsperson)
    TextView reportmessageJsperson;
    @BindView(R.id.reportmessage_jslayout)
    LinearLayout reportmessageJslayout;
    @BindView(R.id.reportmessage_bjtime)
    TextView reportmessageBjtime;
    @BindView(R.id.reportmessage_bjperson)
    TextView reportmessageBjperson;
    @BindView(R.id.reportmessage_bjtext)
    TextView reportmessageBjtext;
    @BindView(R.id.reportmessage_bjlayout)
    LinearLayout reportmessageBjlayout;
    @BindView(R.id.reportmessage_zxtime)
    TextView reportmessageZxtime;
    @BindView(R.id.reportmessage_zxperson)
    TextView reportmessageZxperson;

    @BindView(R.id.reportmessage_zxlayout)
    LinearLayout reportmessageZxlayout;
    @BindView(R.id.reportmessage_zbtime)
    TextView reportmessageZbtime;
    @BindView(R.id.reportmessage_zbperson)
    TextView reportmessageZbperson;
    @BindView(R.id.reportmessage_zblayout)
    LinearLayout reportmessageZblayout;
    @BindView(R.id.reportmessage_src)
    ImageView reportmessageSrc;

    private ReportEntity.ReportSonEntity data;

    private FullTaskFinish fullTaskFinish;
    private QMUITipDialog dataDialog;
    private ReportContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reportmessage);
        ButterKnife.bind(this);

        onCreateNewDialog();
        if (savedInstanceState != null) {
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.username = savedInstanceState.getString("username");
            BaseApplication.userid = savedInstanceState.getString("userid");
            data = new Gson().fromJson(savedInstanceState.getString("data"), ReportEntity.ReportSonEntity.class);
        } else {
            data = new Gson().fromJson(getIntent().getStringExtra("data"), ReportEntity.ReportSonEntity.class);
        }
        initView();
    }

    private void initView() {
        mPresenter = new ReportPresenter(this, this);

        data = new Gson().fromJson(getIntent().getStringExtra("data"), ReportEntity.ReportSonEntity.class);
        reportmessagePerson.setText(data.getUserId());
        reportmessageText.setText(data.getClueDescribe());
        reportmessagePerson.setText(data.getClueNo());
        reportmessageTime.setText(long2DateString(data.getCreateTime()));

        switch (data.getState()) {
            case 0:
                if (BaseApplication.prm == 1003 || BaseApplication.prm == 1004 || BaseApplication.prm == 1005) {
                    reportmessageJs.setVisibility(View.VISIBLE);
                    reportmessageZX.setVisibility(View.VISIBLE);
                    reportmessageZB.setVisibility(View.VISIBLE);
                }
                reportmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_wait));
                break;
            case 1:
                if (BaseApplication.userid.equals(data.getReceiveId())) {
                    reportmessageFinish.setVisibility(View.VISIBLE);
                }
                reportmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_yjs));
                break;
            case 2:
                reportmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_done));
                break;
            case 3://已知晓
                reportmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_zhixiao_report));
                break;
            case 4://已转办
                reportmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_zhuanban));
                break;
        }

        StringBuilder stringBuffer = new StringBuilder();
        List<ReportEntity.Dto> dtoList = data.getDtoList();
        for (int i = 0; i < dtoList.size(); i++) {
            stringBuffer.append(dtoList.get(i).getValue() + "\n\n");
        }
        reportmessageType.setText(stringBuffer.toString());

        if (data.getFlows() != null && data.getFlows().size() > 0) {
//            if (data.getFlows().size() > 1) {
//                reportmessageSbline.setVisibility(View.VISIBLE);
//                reportmessageJslayout.setVisibility(View.VISIBLE);
//            }
//            if (data.getFlows().size() > 2) {
//                reportmessageBjlayout.setVisibility(View.VISIBLE);
//                reportmessageJsline.setVisibility(View.VISIBLE);
//            }
            for (int i = 0; i < data.getFlows().size(); i++) {
                ReportEntity.ReportFlowsEntity dataEntity = data.getFlows().get(i);
                switch (dataEntity.getState()) {
                    // 1已受理，2已办结
                    case 0:
                        reportmessageSbtime.setText(long2DateStringD(data.getCreateTime()) + "   上报给" + dataEntity.getReceiveName());
                        reportmessageSbperson.setText("上报人：" + data.getUserName());
                        break;
                    case 1:
                        reportmessageJslayout.setVisibility(View.VISIBLE);
                        reportmessageSbline.setVisibility(View.VISIBLE);
                        reportmessageJsperson.setText("接受人：" + dataEntity.getReceiveName());
                        reportmessageJstime.setText(long2DateStringD(dataEntity.getCreateTime()) + "   已接受");
                        break;
                    case 2:
                        reportmessageBjlayout.setVisibility(View.VISIBLE);
                        reportmessageJsline.setVisibility(View.VISIBLE);
                        reportmessageBjperson.setText("办结人：" + dataEntity.getReceiveName());
                        reportmessageBjtime.setText(long2DateStringD(dataEntity.getCreateTime()) + "   已办结");
                        reportmessageBjtext.setText(dataEntity.getRemark());
                        break;
                    case 3:
                        reportmessageZxlayout.setVisibility(View.VISIBLE);
                        reportmessageSbline.setVisibility(View.VISIBLE);
                        reportmessageZxperson.setText("知晓人：" + dataEntity.getReceiveName());
                        reportmessageZxtime.setText(long2DateStringD(dataEntity.getCreateTime()) + "   已知晓");
                        break;
                    case 4:
                        reportmessageSbline.setVisibility(View.VISIBLE);
                        reportmessageZblayout.setVisibility(View.VISIBLE);
                        reportmessageZbperson.setText("转办人：" + dataEntity.getReceiveName());
                        reportmessageZbtime.setText(long2DateStringD(dataEntity.getCreateTime()) + "   已转办");
                        break;
                }
            }
        }
    }

    private String long2DateString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));
    }

    private String long2DateStringD(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(time));
    }

    @OnClick({R.id.reportmessage_back, R.id.reportmessage_js, R.id.reportmessage_finish,R.id.reportmessage_zhuanban,R.id.reportmessage_zhixiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reportmessage_back:
                finish();
                break;
            case R.id.reportmessage_js:
                dataDialog.show();
                mPresenter.getReportMessageJS(new Gson().toJson(new ReportWorkRequestEntity(data.getId())));
                break;
            case R.id.reportmessage_zhixiao:
                dataDialog.show();
                mPresenter.getReportMessageZX(new Gson().toJson(new ReportWorkRequestEntity(data.getId())));
                break;
            case R.id.reportmessage_zhuanban:
                dataDialog.show();
                mPresenter.getReportMessageZB(new Gson().toJson(new ReportWorkRequestEntity(data.getId())));
                break;
            case R.id.reportmessage_finish:
                fullTaskFinish = new FullTaskFinish(ReportMessageActivity.this);
                fullTaskFinish.show();
                fullTaskFinish.setBJDATA(new FullTaskFinish.BJJG() {
                    @Override
                    public void getBJ(String text) {
                        dataDialog.show();
                        mPresenter.getReportMessageBJ(new Gson().toJson(new ReportFinishRequestEntity(data.getId(), text)));
                    }
                });
                break;
        }
    }

    @Override
    public void setReportMessageJS(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(this, "接受成功", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void setReportMessageBJ(boolean isSuccess) {
        if (isSuccess) {
            fullTaskFinish.dismiss();
            Toast.makeText(this, "办结成功", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void setReportMessageZX(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(this, "已知晓", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void setReportMessageZB(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(this, "转办成功", Toast.LENGTH_SHORT).show();
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
                .setTipWord("正在加载...")
                .create();
        dataDialog.setCancelable(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("prm", BaseApplication.prm);
        outState.putString("token", BaseApplication.token);
        outState.putString("userid", BaseApplication.userid);
        outState.putString("username", BaseApplication.username);
        outState.putString("data", new Gson().toJson(data));
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
