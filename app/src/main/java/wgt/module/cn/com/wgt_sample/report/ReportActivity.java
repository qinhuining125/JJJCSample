package wgt.module.cn.com.wgt_sample.report;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.ReportAdapter;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportFinishRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportListRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportStateEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportWorkRequestEntity;
import wgt.module.cn.com.wgt_sample.task.FullTaskFinish;
import wgt.module.cn.com.wgt_sample.utils.MyDrawerLayout;
import wgt.module.cn.com.wgt_sample.utils.MyLoadMoreView;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ReportActivity extends AppCompatActivity implements ReportContract.View {

    @BindView(R.id.report_allreporttext)
    TextView reportAllreporttext;
    @BindView(R.id.report_allreportline)
    View reportAllreportline;
    @BindView(R.id.report_waitreporttext)
    TextView reportWaitreporttext;
    @BindView(R.id.report_waitreportline)
    View reportWaitreportline;
    @BindView(R.id.report_workreporttext)
    TextView reportWorkreporttext;
    @BindView(R.id.report_workreportline)
    View reportWorkreportline;
    @BindView(R.id.report_finishreporttext)
    TextView reportFinishreporttext;
    @BindView(R.id.report_finishreportline)
    View reportFinishreportline;
    @BindView(R.id.report_zhixiaoreporttext)
    TextView reportZhixiaoreporttext;
    @BindView(R.id.report_zhixiaoreportline)
    View reportZhixiaoreportline;
    @BindView(R.id.report_zhuanbanreporttext)
    TextView reportZhuanbanreporttext;
    @BindView(R.id.report_zhuanbanreportline)
    View reportZhuanbanreportline;


    @BindView(R.id.report_list)
    SwipeRecyclerView reportList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh;
    @BindView(R.id.report_add)
    ImageView reportAdd;
    @BindView(R.id.report_waitred)
    View reportWaitred;
    @BindView(R.id.report_workred)
    View reportWorkred;
    @BindView(R.id.report_drawerlayout)
    MyDrawerLayout reportDrawerlayout;
    @BindView(R.id.report_starttime)
    TextView reportStarttime;
    @BindView(R.id.report_endtime)
    TextView reportEndtime;
    @BindView(R.id.report_area)
    TextView reportArea;
    @BindView(R.id.report_arealayout)
    LinearLayout reportArealayout;

    private ReportAdapter adapter;
    private List<ReportEntity.ReportSonEntity> dataList;
    private int currentPage = 1;
    private String reportType = "", startTime, endTime, area = "";

    private QMUITipDialog dataDialog, spDialog;
    private ReportContract.Presenter mPresenter;
    private FullTaskFinish fullTaskFinish;

    private List<PersonEntity> personList;
    private List<List<PersonEntity.PersonSonEntity>> personSonList;

    /**
     * wait刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            onRefrush();
        }
    };
    /**
     * wait加载更多。
     */
    private SwipeRecyclerView.LoadMoreListener loadMoreListener = new SwipeRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            refresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentPage++;
                    // 加载数据。
                    dataDialog.show();
                    mPresenter.getReportList(new Gson().toJson(new ReportListRequestEntity(currentPage, new ReportListRequestEntity.ReportListRequestSonEntity(reportType, startTime, endTime, area))));
                }
            }, 1000);
        }
    };

    private void onRefrush() {
        dataList.clear();
        adapter.notifyDataSetChanged();
        currentPage = 1;

        reportList.loadMoreFinish(false, true);
        // 加载数据。
        dataDialog.show();
        mPresenter.getReportList(new Gson().toJson(new ReportListRequestEntity(currentPage, new ReportListRequestEntity.ReportListRequestSonEntity(reportType, startTime, endTime, area))));
        mPresenter.getState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        onCreateNewDialog();
        initView();
        onClickListen();

        if (savedInstanceState != null) {
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.userid = savedInstanceState.getString("userid");
            BaseApplication.username = savedInstanceState.getString("username");
            currentPage = savedInstanceState.getInt("currentPage");
            reportType = savedInstanceState.getString("reportType");
            dataList.add((ReportEntity.ReportSonEntity) new Gson().fromJson(savedInstanceState.getString("dataList"), new TypeToken<List<ReportEntity.ReportSonEntity>>() {
            }.getType()));
            adapter.notifyDataSetChanged();
        } else {
            initData();
        }
        mPresenter.getState();
    }

    private void initData() {
        dataDialog.show();
        mPresenter.getReportList(new Gson().toJson(new ReportListRequestEntity(currentPage, new ReportListRequestEntity.ReportListRequestSonEntity(reportType, startTime, endTime, area))));
    }

    private void onClickListen() {
        reportList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ReportActivity.this, ReportMessageActivity.class);
                intent.putExtra("data", new Gson().toJson(dataList.get(position)));
                startActivityForResult(intent, 201);
            }
        });

        // 在点击事件监听钱setadapter。
        reportList.setAdapter(adapter);
    }

    private void initView() {
        mPresenter = new ReportPresenter(this, this);
        reportEndtime.setText(getTime(new Date()));
        startTime = reportStarttime.getText().toString() + " 00:00:00";
        endTime = reportEndtime.getText().toString() + " 23:59:59";
        personList = new ArrayList<>();
        personSonList = new ArrayList<>();

        // 刷新监听。
        refresh.setOnRefreshListener(refreshListener);
        // 加载更多的监听。
        reportList.setLoadMoreListener(loadMoreListener);
        reportList.setLayoutManager(new LinearLayoutManager(this));
        MyLoadMoreView loadmoreView = new MyLoadMoreView(this);
        reportList.addFooterView(loadmoreView);
        reportList.setLoadMoreView(loadmoreView);

        dataList = new ArrayList<>();
        adapter = new ReportAdapter(dataList, this);
        adapter.setReportSp(new ReportAdapter.SP() {
            @Override
            public void JS(String id) {
                spDialog.show();
                mPresenter.getReportJS(new Gson().toJson(new ReportWorkRequestEntity(id)));
            }

            @Override
            public void ZB(String id) {
                spDialog.show();
                mPresenter.getReportZB(new Gson().toJson(new ReportWorkRequestEntity(id)));
            }

            @Override
            public void ZX(String id) {
                spDialog.show();
                mPresenter.getReportZX(new Gson().toJson(new ReportWorkRequestEntity(id)));
            }

            @Override
            public void Finish(final String id) {
                fullTaskFinish = new FullTaskFinish(ReportActivity.this);
                fullTaskFinish.show();
                fullTaskFinish.setBJDATA(new FullTaskFinish.BJJG() {
                    @Override
                    public void getBJ(String data) {
                        spDialog.show();
                        mPresenter.getReportBJ(new Gson().toJson(new ReportFinishRequestEntity(id, data)));
                    }
                });
            }
        });

        // 上报权限。
        if (BaseApplication.prm == 1001 || BaseApplication.prm == 1002) {
            reportAdd.setVisibility(View.VISIBLE);
        }

        // 筛选权限。
        if (BaseApplication.prm >= 1003) {
            reportArealayout.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.report_starttime, R.id.report_endtime, R.id.report_area,
            R.id.report_add, R.id.report_back, R.id.report_menu,
            R.id.report_allreport, R.id.report_waitreport,
            R.id.report_workreport, R.id.report_finishreport, R.id.report_qx, R.id.report_cz, R.id.report_qd,
            R.id.report_zhixiaoreport,R.id.report_zhuanbanreport})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.report_starttime:
                timePick(reportStarttime, "开始时间");
                break;
            case R.id.report_endtime:
                timePick(reportEndtime, "结束时间");
                break;
            case R.id.report_area:
                optionsPick(reportArea, personList, personSonList);
                break;
            case R.id.report_add:
                Intent intent = new Intent(ReportActivity.this, NewReportActivity.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.report_back:
                finish();
                break;
            case R.id.report_menu:
                if (BaseApplication.prm < 1003) {
                    reportDrawerlayout.openDrawer(GravityCompat.END);
                } else {
                    if (personList.size() > 0 && personSonList.size() > 0) {
                        reportDrawerlayout.openDrawer(GravityCompat.END);
                    } else {
                        dataDialog.show();
                        mPresenter.getPerson();
                    }
                }
                break;
            case R.id.report_allreport:
                if (reportAllreportline.getVisibility() == View.GONE) {
                    reportType = "";
                    reportChoose(reportAllreporttext, reportAllreportline);
                    onRefrush();
                }
                break;
            case R.id.report_waitreport:
                if (reportWaitreportline.getVisibility() == View.GONE) {
                    reportType = "0";
                    reportChoose(reportWaitreporttext, reportWaitreportline);
                    onRefrush();
                }
                break;
            case R.id.report_workreport:
                if (reportWorkreportline.getVisibility() == View.GONE) {
                    reportType = "1";
                    reportChoose(reportWorkreporttext, reportWorkreportline);
                    onRefrush();
                }
                break;
            case R.id.report_finishreport:
                if (reportFinishreportline.getVisibility() == View.GONE) {
                    reportType = "2";
                    reportChoose(reportFinishreporttext, reportFinishreportline);
                    onRefrush();
                }
                break;
            case R.id.report_zhixiaoreport:
                if (reportZhixiaoreportline.getVisibility() == View.GONE) {
                    reportType = "3";
                    reportChoose(reportZhixiaoreporttext, reportZhixiaoreportline);
                    onRefrush();
                }
                break;
            case R.id.report_zhuanbanreport:
                if (reportZhuanbanreportline.getVisibility() == View.GONE) {
                    reportType = "4";
                    reportChoose(reportZhuanbanreporttext, reportZhuanbanreportline);
                    onRefrush();
                }
                break;
            case R.id.report_qx:
                reportDrawerlayout.closeDrawer(GravityCompat.END);
                break;
            case R.id.report_cz:
                reportStarttime.setText("2020-01-01");
                reportEndtime.setText(getTime(new Date()));
                reportArea.setText("全部");
                break;
            case R.id.report_qd:
                startTime = reportStarttime.getText().toString() + " 00:00:00";
                endTime = reportEndtime.getText().toString() + " 23:59:59";
                if ("全部".equals(reportArea.getText().toString())) {
                    area = "";
                }
                onRefrush();
                reportDrawerlayout.closeDrawer(GravityCompat.END);
                break;
        }
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void reportChoose(TextView reporttext, View reportline) {
        reportAllreporttext.setTextColor(getResources().getColor(R.color.afharfwrite));
        reportWaitreporttext.setTextColor(getResources().getColor(R.color.afharfwrite));
        reportWorkreporttext.setTextColor(getResources().getColor(R.color.afharfwrite));
        reportFinishreporttext.setTextColor(getResources().getColor(R.color.afharfwrite));
        reportZhixiaoreporttext.setTextColor(getResources().getColor(R.color.afharfwrite));
        reportZhuanbanreporttext.setTextColor(getResources().getColor(R.color.afharfwrite));
        reportAllreportline.setVisibility(View.GONE);
        reportWaitreportline.setVisibility(View.GONE);
        reportWorkreportline.setVisibility(View.GONE);
        reportZhixiaoreportline.setVisibility(View.GONE);
        reportZhuanbanreportline.setVisibility(View.GONE);
        reportFinishreportline.setVisibility(View.GONE);
        reporttext.setTextColor(getResources().getColor(R.color.write));
        reportline.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            onRefrush();
        } else if (requestCode == 201 && resultCode == RESULT_OK) {
            onRefrush();
        }
    }

    @Override
    public void setReportList(ReportEntity data) {
        dataList.addAll(data.getRows());
        refresh.setRefreshing(false);
        adapter.notifyDataSetChanged();

        if (dataList.size() >= data.getTotal()) {
            reportList.loadMoreFinish(false, false);
        } else {
            reportList.loadMoreFinish(false, true);
        }
    }

    @Override
    public void setReportJS(boolean isSuccess) {
        if (isSuccess) {
            onRefrush();
            Toast.makeText(this, "接受成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setReportBJ(boolean isSuccess) {
        if (isSuccess) {
            onRefrush();
            fullTaskFinish.dismiss();
            Toast.makeText(this, "办结成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setReportZB(boolean isSuccess) {
        if (isSuccess) {
            onRefrush();
            Toast.makeText(this, "转办成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setReportZX(boolean isSuccess) {
        if (isSuccess) {
            onRefrush();
            Toast.makeText(this, "已知晓", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void spDialogDissmis() {
        spDialog.dismiss();
    }

    @Override
    public void setState(ReportStateEntity data) {
        if (data.isAccept()) {
            reportWaitred.setVisibility(View.VISIBLE);
        } else {
            reportWaitred.setVisibility(View.GONE);
        }

        if (data.isClose()) {
            reportWorkred.setVisibility(View.VISIBLE);
        } else {
            reportWorkred.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPerson(List<PersonEntity> dataList) {
        personList.addAll(dataList);
        for (int i = 0; i < personList.size(); i++) {
            if (BaseApplication.prm > 1003) {
                PersonEntity personEntity = personList.get(i);
                PersonEntity.PersonSonEntity sonEntity = new PersonEntity.PersonSonEntity(personEntity.getAreaName(), personEntity.getAreaCode());
                personList.get(i).getChilds().add(0, sonEntity);
            }
            personSonList.add(personList.get(i).getChilds());
        }
        personList.add(0, new PersonEntity("全部"));
        List<PersonEntity.PersonSonEntity> pList = new ArrayList<>();
        pList.add(new PersonEntity.PersonSonEntity("全部", ""));
        personSonList.add(0, pList);

        reportDrawerlayout.openDrawer(GravityCompat.END);
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
        spDialogDissmis();
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

        spDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载...")
                .create();
        spDialog.setCancelable(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("prm", BaseApplication.prm);
        outState.putString("token", BaseApplication.token);
        outState.putString("userid", BaseApplication.userid);
        outState.putString("username", BaseApplication.username);
        outState.putInt("currentPage", currentPage);
        outState.putString("reportType", reportType);
        outState.putString("dataList", new Gson().toJson(dataList));
        super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * 时间选择器。
     *
     * @param title 标题。
     */
    public void timePick(final TextView et, String title) {
        //时间选择器。
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                et.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                //标题文字。
                .isDialog(true)
                .setTitleText(title)
                //确定按钮文字颜色。
                .setSubCalSize(14)
                .setSubmitColor(getResources().getColor(R.color.colorAccent))
                //取消按钮文字颜色。
                .setCancelColor(getResources().getColor(R.color.colorAccent))
                .build();
        pvTime.show();
    }

    private void optionsPick(final TextView et, final List<PersonEntity> dataList, final List<List<PersonEntity.PersonSonEntity>> dataSonList) {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                et.setText(dataSonList.get(options1).get(option2).getAreaName());
                area = dataSonList.get(options1).get(option2).getAreaCode();
            }
        })
                //标题文字。
                .setTitleText("请选择")
                //确定按钮文字颜色。
                .setSubCalSize(14)
                .isDialog(true)
                .setSubmitColor(getResources().getColor(R.color.colorAccent))
                //取消按钮文字颜色。
                .setCancelColor(getResources().getColor(R.color.colorAccent))
                .build();
        pvOptions.setPicker(dataList, dataSonList);
        pvOptions.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
