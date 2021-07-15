package wgt.module.cn.com.wgt_sample.report;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.AudioAdapter;
import wgt.module.cn.com.wgt_sample.adapter.ImageAdapter;
import wgt.module.cn.com.wgt_sample.adapter.OnRecyclerItemClickListener;
import wgt.module.cn.com.wgt_sample.adapter.VideoAdapter;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportFinishRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportWorkRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportZBRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportpersonEntity;
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

    // 指派任务窗口。
    private FullZBReport zpTask;

    private ReportEntity.ReportSonEntity data;

    private FullTaskFinish fullTaskFinish;
    private QMUITipDialog dataDialog;
    private ReportContract.Presenter mPresenter;


    @BindView(R.id.reportmessage_image)
    RecyclerView imageRecylerView;
    @BindView(R.id.layout_images1)
    LinearLayout layoutImages;

    // 图片列表adapter。
    private ImageAdapter imageAdapter;
    // 图片列表数据。
    private List<MultiplexImage> imageList;
    private List<String> imageListNormal;


    //语音
    @BindView(R.id.reportmessage_audio)
    RecyclerView audioRecylerView;
    @BindView(R.id.layout_audio)
    LinearLayout layoutAudios;

    // 语音列表adapter。
    private AudioAdapter audioAdapter;
    // 语音列表数据。
    private List<MultiplexImage> audioList;
    private List<String> audioListNormal;

    //视频
    @BindView(R.id.reportmessage_video)
    RecyclerView videoRecylerView;
    @BindView(R.id.layout_video)
    LinearLayout layoutVideos;

    // 视频列表adapter。
    private VideoAdapter videoAdapter;
    // 视频列表数据。

    private List<MultiplexImage> videoList;
    private List<String> videoListNormal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reportmessage);
        ButterKnife.bind(this);

        onCreateNewDialog();
        if (savedInstanceState != null) {
//            BaseApplication.baseURL = "http://183.201.252.83:49012/";
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

        //图片
        if (data.getImgApp()!=null&&data.getImgApp().size()!=0){
            //新增赋值图片列表
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
                        Mango.open(ReportMessageActivity.this);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            imageRecylerView.setAdapter(imageAdapter);
        }else {
            layoutImages.setVisibility(View.GONE);
        }


        if (data.getAudioApp()!=null&&data.getAudioApp().size()!=0){
            //新增赋值语音列表
            audioListNormal = data.getAudioApp();
            audioList = new ArrayList<>();
            //处理imagelist为List<MultiplexImage>的格式；
            for (int i=0;i<audioListNormal.size();i++){
                audioList.add(new MultiplexImage(audioListNormal.get(i),null,MultiplexImage.ImageType.NORMAL));
            }

            //初始化语音列表布局
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
            audioRecylerView.setLayoutManager(gridLayoutManager);

            audioAdapter = new AudioAdapter(audioListNormal,getApplicationContext());

            audioRecylerView.setAdapter(audioAdapter);
        }else {
            layoutAudios.setVisibility(View.GONE);
        }


        if (data.getVideoApp()!=null&&data.getVideoApp().size()!=0){
            //新增赋值视频列表
            videoListNormal = data.getVideoApp();
            videoList = new ArrayList<>();
            //处理imagelist为List<MultiplexImage>的格式；
            int size = videoListNormal.size();
            for (int i=0;i<size;i++){
                videoList.add(new MultiplexImage(videoListNormal.get(i),null,MultiplexImage.ImageType.NORMAL));
            }

            //初始化视频列表布局
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            videoRecylerView.setLayoutManager(gridLayoutManager);
            videoAdapter = new VideoAdapter(videoList, getApplicationContext());
            videoAdapter.setItemClickListener(new OnRecyclerItemClickListener() {
                @Override
                public void click(View item, int position) {
                    Log.d("video", "click: " + videoListNormal.get(position));
                    VideoViewActivity.start(ReportMessageActivity.this, videoListNormal.get(position));
                }
            });
            videoRecylerView.setAdapter(videoAdapter);
        }else {
            layoutVideos.setVisibility(View.GONE);
        }

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
                mPresenter.getMessagePerson(data.getId(), data.getClueDescribe());
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
    public void setPersonMessageZB(List<ReportpersonEntity> dataList, final String id, String text) {
        Log.e("messageactity------",""+dataList.toString());
        zpTask = new FullZBReport(this, text, dataList);
        zpTask.show();
        zpTask.setZPTask(new FullZBReport.ZPTask() {
            @Override
            public void zpTask(String receiveRoleId) {
                dataDialog.show();
                mPresenter.getReportMessageZB(new Gson().toJson(new ReportZBRequestEntity(id, Integer.parseInt(receiveRoleId))));
            }
        });
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
