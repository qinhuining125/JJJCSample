package wgt.module.cn.com.wgt_sample.jubao;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import wgt.module.cn.com.wgt_sample.adapter.JubaoAdapter;
import wgt.module.cn.com.wgt_sample.adapter.TaskAdapter;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.JubaoSLRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.JubaoStateEntity;
import wgt.module.cn.com.wgt_sample.entity.JubaoSubmitRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.JubaoZPRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.JubaopersonEntity;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskBJRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskListRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskSLRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskStateEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskSubmitRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskZPRequestEntity;
import wgt.module.cn.com.wgt_sample.utils.MyDrawerLayout;
import wgt.module.cn.com.wgt_sample.utils.MyLoadMoreView;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class JubaoActivity extends AppCompatActivity implements JubaoContract.View {

    @BindView(R.id.task_alltasktext)
    TextView taskAlltasktext;
    @BindView(R.id.task_alltaskline)
    View taskAlltaskline;
    @BindView(R.id.task_waittasktext)
    TextView taskWaittasktext;
    @BindView(R.id.task_waittaskline)
    View taskWaittaskline;
    @BindView(R.id.task_worktasktext)
    TextView taskWorktasktext;
    @BindView(R.id.task_worktaskline)
    View taskWorktaskline;
    @BindView(R.id.task_finishtasktext)
    TextView taskFinishtasktext;
    @BindView(R.id.task_finishtaskline)
    View taskFinishtaskline;
    @BindView(R.id.task_list)
    SwipeRecyclerView taskList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh;
//    @BindView(R.id.task_add)
//    ImageView taskAdd;
    @BindView(R.id.task_waitred)
    View taskWaitred;
    @BindView(R.id.task_workred)
    View taskWorkred;
    @BindView(R.id.task_back)
    ImageView taskBack;
    @BindView(R.id.task_alltask)
    RelativeLayout taskAlltask;
    @BindView(R.id.task_waittask)
    RelativeLayout taskWaittask;
    @BindView(R.id.task_worktask)
    RelativeLayout taskWorktask;
    @BindView(R.id.task_finishtask)
    RelativeLayout taskFinishtask;
    @BindView(R.id.task_starttime)
    TextView taskStarttime;
    @BindView(R.id.task_endtime)
    TextView taskEndtime;
    @BindView(R.id.task_area)
    TextView taskArea;
    @BindView(R.id.yask_arealayout)
    LinearLayout taskArealayout;
    @BindView(R.id.task_drawerlayout)
    MyDrawerLayout taskDrawerlayout;

    // adapter。
    private JubaoAdapter adapter;
    // 列表数据。
    private List<TaskEntity.TaskSonEntity> dataList;
    // 分页。
    private int currentPage = 1;
    // 列表查询条件。
    private String taskType = "", startTime, endTime, area = "";
    // 列表人员筛选数据。
    private List<PersonEntity> personList;
    private List<List<PersonEntity.PersonSonEntity>> personSonList;

    // 等待框。
    private QMUITipDialog dataDialog, spDialog;
    // persenter。
    private JubaoContract.Presenter mPresenter;
    // 办结框。
    private FullJubaoFinish fullJubaoFinish;
    // 新建指派框。
    private FullNewJubao newTask;
    // 第二次指派框。
    private FullZPJubao zpTask;

    // 刷新。
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            onRefrush();
        }
    };

    // 加载更多。
    private SwipeRecyclerView.LoadMoreListener loadMoreListener = new SwipeRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            refresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentPage++;
                    // 加载数据。
                    dataDialog.show();
                    mPresenter.getJubaoList(new Gson().toJson(new TaskListRequestEntity(currentPage, new TaskListRequestEntity.TaskListRequestSonEntity(taskType, startTime, endTime, area))));
                }
            }, 1000);
        }
    };

    // 刷新。
    private void onRefrush() {
        dataList.clear();
        adapter.notifyDataSetChanged();
        currentPage = 1;

        taskList.loadMoreFinish(false, true);
        // 加载数据。
        dataDialog.show();
        mPresenter.getJubaoList(new Gson().toJson(new TaskListRequestEntity(currentPage, new TaskListRequestEntity.TaskListRequestSonEntity(taskType, startTime, endTime, area))));
        mPresenter.getState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是什么状态然后再加载布局，是联络员或网格员加载webview打开举报页面；
//        2.是其他角色则打开列表页。
        setContentView(R.layout.act_jubaotask);
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
            taskType = savedInstanceState.getString("taskType");
            dataList.add((TaskEntity.TaskSonEntity) new Gson().fromJson(savedInstanceState.getString("dataList"), new TypeToken<List<TaskEntity.TaskSonEntity>>() {
            }.getType()));
            adapter.notifyDataSetChanged();
        } else {
            initData();
        }
        mPresenter.getState();
    }

    private void initData() {
        dataDialog.show();
        mPresenter.getJubaoList(new Gson().toJson(new TaskListRequestEntity(currentPage, new TaskListRequestEntity.TaskListRequestSonEntity(taskType, startTime, endTime, area))));
    }

    private void onClickListen() {
        taskList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(JubaoActivity.this, JubaoMessageActivity.class);
                intent.putExtra("data", new Gson().toJson(dataList.get(position)));
                startActivityForResult(intent, 301);
            }
        });
        taskList.setAdapter(adapter);
    }

    private void initView() {
        mPresenter = new JubaoPresenter(this, this);
        taskEndtime.setText(getTime(new Date()));
        startTime = taskStarttime.getText().toString() + " 00:00:00";
        endTime = taskEndtime.getText().toString() + " 23:59:59";
        personList = new ArrayList<>();
        personSonList = new ArrayList<>();

        // 刷新监听。
        refresh.setOnRefreshListener(refreshListener);
        // 加载更多的监听。
        taskList.setLoadMoreListener(loadMoreListener);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        MyLoadMoreView loadmoreView = new MyLoadMoreView(this);
        taskList.addFooterView(loadmoreView);
        taskList.setLoadMoreView(loadmoreView);

        dataList = new ArrayList<>();
        adapter = new JubaoAdapter(dataList, this);
        // 审批。
        adapter.setTaskSp(new JubaoAdapter.SP() {
            @Override
            public void SL(String id) {
                spDialog.show();
                mPresenter.getJubaoSL(id);
            }

            @Override
            public void ZP(final String id, String text) {
                dataDialog.show();
                mPresenter. getJubaoPerson(1, id, text);
            }

//            @Override
//            public void BJ(final String id) {
//                fullJubaoFinish = new FullJubaoFinish(JubaoActivity.this);
//                fullJubaoFinish.show();
//                fullJubaoFinish.setBJDATA(new FullJubaoFinish.BJJG() {
//                    @Override
//                    public void getBJ(String data) {
//                        spDialog.show();
//                        mPresenter.getJubaoBJ(new Gson().toJson(new TaskBJRequestEntity(id, data)));
//                    }
//                });
//            }
        });

//        if (BaseApplication.prm == 1003 || BaseApplication.prm == 1004) {
//            taskAdd.setVisibility(View.VISIBLE);
//        }

        // 筛选权限。
        if (BaseApplication.prm >= 1008) {
            taskArealayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 301 && resultCode == RESULT_OK) {
//            Log.e("onactivityresult","301,onrefresh");
            onRefrush();
        }
    }

    @OnClick({R.id.task_starttime, R.id.task_endtime, R.id.task_area, R.id.task_menu, R.id.yask_arealayout, R.id.task_qx, R.id.task_cz, R.id.task_qd, R.id.task_back, R.id.task_alltask, R.id.task_waittask, R.id.task_worktask,R.id.task_finishtask})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_starttime:
                timePick(taskStarttime, "开始时间");
                break;
            case R.id.task_endtime:
                timePick(taskEndtime, "结束时间");
                break;
            case R.id.task_area:
                optionsPick(taskArea, personList, personSonList);
                break;
            case R.id.task_menu:
                if (BaseApplication.prm < 1003) {
                    taskDrawerlayout.openDrawer(GravityCompat.END);
                } else {
                    if (personList.size() > 0 && personSonList.size() > 0) {
                        taskDrawerlayout.openDrawer(GravityCompat.END);
                    } else {
                        dataDialog.show();
                        mPresenter.getPerson();
                    }
                }
                break;
            case R.id.task_qx:
                taskDrawerlayout.closeDrawer(GravityCompat.END);
                break;
            case R.id.task_cz:
                taskStarttime.setText("2020-01-01");
                taskEndtime.setText(getTime(new Date()));
                taskArea.setText("全部");
                break;
            case R.id.task_qd:
                startTime = taskStarttime.getText().toString() + " 00:00:00";
                endTime = taskEndtime.getText().toString() + " 23:59:59";
                if ("全部".equals(taskArea.getText().toString())) {
                    area = "";
                }
                onRefrush();
                taskDrawerlayout.closeDrawer(GravityCompat.END);
                break;
//            case R.id.task_add:
//                dataDialog.show();
//                mPresenter.getJubaoPerson(0, "", "");
//                break;
            case R.id.task_back:
                finish();
                break;
            case R.id.task_alltask:
                if (taskAlltaskline.getVisibility() == View.GONE) {
                    taskType = "";
                    taskChoose(taskAlltasktext, taskAlltaskline);
                    onRefrush();
                }
                break;
            case R.id.task_waittask:
                if (taskWaittaskline.getVisibility() == View.GONE) {
                    taskType = "0";
                    taskChoose(taskWaittasktext, taskWaittaskline);
                    onRefrush();
                }
                break;
            case R.id.task_worktask:
                if (taskWorktaskline.getVisibility() == View.GONE) {
                    taskType = "1";
                    taskChoose(taskWorktasktext, taskWorktaskline);
                    onRefrush();
                }
                break;
            case R.id.task_finishtask:
                if (taskFinishtaskline.getVisibility() == View.GONE) {
                    taskType = "2";
                    taskChoose(taskFinishtasktext, taskFinishtaskline);
                    onRefrush();
                }
                break;
        }
    }

    private void taskChoose(TextView tasktext, View taskline) {
        taskAlltasktext.setTextColor(getResources().getColor(R.color.afharfwrite));
        taskWaittasktext.setTextColor(getResources().getColor(R.color.afharfwrite));
        taskWorktasktext.setTextColor(getResources().getColor(R.color.afharfwrite));
        taskFinishtasktext.setTextColor(getResources().getColor(R.color.afharfwrite));
        taskAlltaskline.setVisibility(View.GONE);
        taskWaittaskline.setVisibility(View.GONE);
        taskWorktaskline.setVisibility(View.GONE);
        taskFinishtaskline.setVisibility(View.GONE);
        tasktext.setTextColor(getResources().getColor(R.color.write));
        taskline.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTask(boolean isSuccess) {
        if (isSuccess) {
            onRefrush();
            newTask.dismiss();
            Toast.makeText(this, "指派任务成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setTaskSL(boolean isSuccess) {
        if (isSuccess) {
            onRefrush();
            Toast.makeText(this, "受理成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setTaskZP(boolean isSuccess) {
        if (isSuccess) {
            onRefrush();
            zpTask.dismiss();
            Toast.makeText(this, "指派成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setTaskBJ(boolean isSuccess) {
        if (isSuccess) {
            onRefrush();
            fullJubaoFinish.dismiss();
            Toast.makeText(this, "办结成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setTaskList(TaskEntity data) {
        dataList.addAll(data.getRows());
        refresh.setRefreshing(false);
        adapter.notifyDataSetChanged();

        if (dataList.size() >= data.getTotal()) {
            taskList.loadMoreFinish(false, false);
        } else {
            taskList.loadMoreFinish(false, true);
        }
    }

    @Override
    public void spDialogDissmis() {
        spDialog.dismiss();
    }

    @Override
    public void setPersonNEW(List<JubaopersonEntity> dataList) {
        newTask = new FullNewJubao(this, dataList,this);
        newTask.show();
        // 新指派任务回调
        newTask.setTask(new FullNewJubao.Task() {
            @Override
            public void addTask(String data,  List<String> receiveRoleId) {
                spDialog.show();
//                mPresenter.addTask(new Gson().toJson(new JubaoSubmitRequestEntity(data, receiveRoleId)));
            }
        });
    }

    @Override
    public void setPersonZP(List<JubaopersonEntity> dataList, final String id, String text) {
        zpTask = new FullZPJubao(this, text, dataList);
        zpTask.show();
        zpTask.setZPTask(new FullZPJubao.ZPTask() {
            @Override
            public void zpTask(String roleId) {
                spDialog.show();
                mPresenter.getJubaoZP(new Gson().toJson(new JubaoZPRequestEntity(id, Integer.parseInt(roleId))));
            }
        });
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

        taskDrawerlayout.openDrawer(GravityCompat.END);
    }

    @Override
    public void setState(JubaoStateEntity data) {
//        ├─ pending	boolean	必须		是否有未处理
//├─ assign	boolean	必须		是否有已分配
//├─ accept	boolean	必须		是否有已知晓
        //是否有未处理
        if (data.getPending()) {
            taskWaitred.setVisibility(View.VISIBLE);
        } else {
            taskWaitred.setVisibility(View.GONE);
        }
      //是否有已分配
        if (data.getAssign()) {
            taskWorkred.setVisibility(View.VISIBLE);
        } else {
            taskWorkred.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresenter(JubaoContract.Presenter presenter) {
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

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("prm", BaseApplication.prm);
        outState.putString("token", BaseApplication.token);
        outState.putString("userid", BaseApplication.userid);
        outState.putString("username", BaseApplication.username);
        outState.putInt("currentPage", currentPage);
        outState.putString("taskType", taskType);
        outState.putString("dataList", new Gson().toJson(dataList));
        super.onSaveInstanceState(outState, outPersistentState);
    }

}
