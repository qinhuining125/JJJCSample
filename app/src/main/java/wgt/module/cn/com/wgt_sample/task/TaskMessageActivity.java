package wgt.module.cn.com.wgt_sample.task;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jelly.mango.ImageSelectListener;
import com.jelly.mango.Mango;
import com.jelly.mango.MultiplexImage;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.ImageAdapter;
import wgt.module.cn.com.wgt_sample.adapter.OnRecyclerItemClickListener;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskBJRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskSLRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskZPRequestEntity;
import wgt.module.cn.com.wgt_sample.xuncha.XunchaMessageActivity;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class TaskMessageActivity extends AppCompatActivity implements TaskContract.messageView {

    @BindView(R.id.taskmessage_person)
    TextView taskmessagePerson;
    @BindView(R.id.taskmessage_text)
    TextView taskmessageText;
    @BindView(R.id.taskmessage_time)
    TextView taskmessageTime;
    @BindView(R.id.taskmessage_zp)
    TextView taskmessageZp;
    @BindView(R.id.taskmessage_sl)
    TextView taskmessageSl;
    @BindView(R.id.taskmessage_bj)
    TextView taskmessageBj;
    @BindView(R.id.taskmessage_src)
    ImageView taskmessageSrc;
    @BindView(R.id.task_message_zp1time)
    TextView taskMessageZp1time;
    @BindView(R.id.task_message_zp1line)
    View taskMessageZp1line;
    @BindView(R.id.task_message_zp1person)
    TextView taskMessageZp1person;
    @BindView(R.id.task_message_zp2time)
    TextView taskMessageZp2time;
    @BindView(R.id.task_message_zp2line)
    View taskMessageZp2line;
    @BindView(R.id.task_message_zp2person)
    TextView taskMessageZp2person;
    @BindView(R.id.task_message_zp2layout)
    LinearLayout taskMessageZp2layout;
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
    @BindView(R.id.task_message_bjtext)
    TextView taskMessageBjtext;
    @BindView(R.id.task_message_bjlayout)
    LinearLayout taskMessageBjlayout;

    @BindView(R.id.taskmessage_image)
    RecyclerView imageRecylerView;
    @BindView(R.id.layout_images)
    LinearLayout layoutImages;



    // 获取列表传入的任务数据。
    private TaskEntity.TaskSonEntity data;
    // 办结窗口。
    private FullTaskFinish fullTaskFinish;
    // 第二级指派任务窗口。
    private FullZPTask zpTask;
    // 等待框。
    private QMUITipDialog dataDialog;
    // persenter。
    private TaskContract.Presenter mPresenter;

    //9.23日新增
    // 图片列表adapter。
    private ImageAdapter imageAdapter;
    // 图片列表数据。
    private List<MultiplexImage> imageList;
    private List<String> imageListNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_taskmessage);
        ButterKnife.bind(this);

        onCreateNewDialog();
        if (savedInstanceState != null) {
            //  9.24更新
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.username = savedInstanceState.getString("username");
            BaseApplication.userid = savedInstanceState.getString("userid");
            data = new Gson().fromJson(savedInstanceState.getString("data"), TaskEntity.TaskSonEntity.class);
        } else {
            data = new Gson().fromJson(getIntent().getStringExtra("data"), TaskEntity.TaskSonEntity.class);
        }
        initView();
    }

    private void initView() {
        mPresenter = new TaskPresenter(this, this);

        // 接受数据并赋值。
        taskmessagePerson.setText(data.getUserName());
        taskmessageText.setText(data.getContent());
        taskmessageTime.setText(long2DateString(data.getCreateTime()));


        if (data.getImgApp()!=null&&data.getImgApp().size()!=0){
            //9.23新增赋值图片列表
            imageListNormal = data.getImgApp();
            imageList = new ArrayList<>();
            //处理imagelist为List<MultiplexImage>的格式；
            for (int i=0;i<imageListNormal.size();i++){
                imageList.add(new MultiplexImage(imageListNormal.get(i),null,MultiplexImage.ImageType.NORMAL));
            }

            //初始化图片列表布局

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
            imageRecylerView.setLayoutManager(gridLayoutManager);
            imageAdapter = new ImageAdapter(imageList,getApplicationContext());
            imageAdapter.setItemClickListener(new OnRecyclerItemClickListener() {
                @Override
                public void click(View item, int position) {
                    Mango.setImages(imageList);
                    Mango.setPosition(position);
                    Mango.setIsShowLoading(false);
                    Mango.setImageSelectListener(new ImageSelectListener() {
                        @Override
                        public void select(int index) {
                            Log.d("Mango", "select: "+index);
                        }
                    });
                    try {
                        Mango.open(TaskMessageActivity.this);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            imageRecylerView.setAdapter(imageAdapter);
        }else {
            layoutImages.setVisibility(View.GONE);
        }

        //示例图片。
//        imageList.add(new MultiplexImage("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png",null,MultiplexImage.ImageType.NORMAL));


        // 根据任务状态判断显示资源。
        switch (data.getState()) {
            case 0:
                if (data.getFlows()!=null&& data.getFlows().size()!=0){
                    if (BaseApplication.prm == data.getFlows().get(0).getReceiveRoleId()) {
                        taskmessageSl.setVisibility(View.VISIBLE);
                        if (data.getFlows().get(0).getReceiveRoleId() == 1003) {
                            taskmessageZp.setVisibility(View.VISIBLE);
                        }
                    }
                }
                taskmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_wait));
                break;
            case 1:
                if (BaseApplication.prm == 1002 || BaseApplication.prm == 1003) {
                    if (BaseApplication.userid.equals(data.getAcceptUserId())) {
                        taskmessageBj.setVisibility(View.VISIBLE);
                    }
                }
                taskmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_accepted));
                break;
            case 2:
                taskmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_done));
                break;
            case 3:
                if (BaseApplication.prm == 1002) {
                    taskmessageSl.setVisibility(View.VISIBLE);
                }
                taskmessageSrc.setImageDrawable(getResources().getDrawable(R.drawable.ic_wait));
                break;
        }

        // 流程。
        if (data.getFlows() != null && data.getFlows().size() > 0) {
            for (int i = 0; i < data.getFlows().size(); i++) {
                TaskEntity.TaskFlowsEntity dataEntity = data.getFlows().get(i);
                switch (dataEntity.getState()) {
                    // 1已受理，2已办结，3已指派
                    case 0:
                        taskMessageZp1person.setText("指派人：" + data.getUserName());
                        taskMessageZp1time.setText(long2DateStringD(data.getCreateTime()) + "   指派给" + dataEntity.getReceiveName());
                        break;
                    case 3:
                        taskMessageZp1line.setVisibility(View.VISIBLE);
                        taskMessageZp2layout.setVisibility(View.VISIBLE);
                        taskMessageZp2person.setText("指派人：" + data.getFlows().get(0).getReceiveName());
                        taskMessageZp2time.setText(long2DateStringD(dataEntity.getCreateTime()) + "   指派给" + dataEntity.getReceiveName());
                        break;
                    case 1:
                        if (taskMessageZp2layout.getVisibility() == View.VISIBLE) {
                            taskMessageZp2line.setVisibility(View.VISIBLE);
                        } else {
                            taskMessageZp1line.setVisibility(View.VISIBLE);
                        }
                        taskMessageSllayout.setVisibility(View.VISIBLE);
                        taskMessageSlperson.setText("接受人：" + dataEntity.getReceiveName());
                        taskMessageSltime.setText(long2DateStringD(dataEntity.getCreateTime()) + "   已接受");
                        break;
                    case 2:
                        taskMessageSlline.setVisibility(View.VISIBLE);
                        taskMessageBjlayout.setVisibility(View.VISIBLE);
                        taskMessageBjperson.setText("反馈人：" + dataEntity.getReceiveName());
                        taskMessageBjtime.setText(long2DateStringD(dataEntity.getCreateTime()) + "   已办结");
                        taskMessageBjtext.setText(dataEntity.getRemark());
                        break;
                }
            }
        }
    }

    // 时间戳2字符串。
    private String long2DateString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));
    }

    private String long2DateStringD(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(time));
    }

    // 点击事件。
    @OnClick({R.id.taskmessage_back, R.id.taskmessage_zp, R.id.taskmessage_sl, R.id.taskmessage_bj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.taskmessage_back: // back。
                finish();
                break;
            case R.id.taskmessage_zp: // 指派。
                dataDialog.show();
                mPresenter.getMessagePerson(data.getId(), data.getContent());
                break;
            case R.id.taskmessage_sl: // 受理。
                dataDialog.show();
                mPresenter.getTaskMessageSL(new Gson().toJson(new TaskSLRequestEntity(data.getId())));
                break;
            case R.id.taskmessage_bj: // 办结。
                fullTaskFinish = new FullTaskFinish(TaskMessageActivity.this);
                fullTaskFinish.show();
                fullTaskFinish.setBJDATA(new FullTaskFinish.BJJG() {
                    @Override
                    public void getBJ(String text) {
                        dataDialog.show();
                        mPresenter.getTaskMessageBJ(new Gson().toJson(new TaskBJRequestEntity(data.getId(), text)));
                    }
                });
                break;

        }
    }

    @Override
    public void setTaskMessageSL(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(this, "受理成功", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void setTaskMessageZP(boolean isSuccess) {
        if (isSuccess) {
            zpTask.dismiss();
            Toast.makeText(this, "指派任务成功", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void setTaskMessageBJ(boolean isSuccess) {
        if (isSuccess) {
            fullTaskFinish.dismiss();
            Toast.makeText(this, "反馈成功", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void setPersonMessageZP(List<PersonEntity> dataList, final String id, String text) {
        zpTask = new FullZPTask(this, text, dataList);
        zpTask.show();
        zpTask.setZPTask(new FullZPTask.ZPTask() {
            @Override
            public void zpTask(String receiveId, String receiveRoleId) {
                dataDialog.show();
                mPresenter.getTaskMessageZP(new Gson().toJson(new TaskZPRequestEntity(id, receiveId, receiveRoleId)));
            }
        });
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
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