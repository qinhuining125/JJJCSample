package wgt.module.cn.com.wgt_sample.report;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.ReportUploadAudioAdapter;
import wgt.module.cn.com.wgt_sample.adapter.ReportUploadImageAdapter;
import wgt.module.cn.com.wgt_sample.adapter.ReportUploadVideoAdapter;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportSubmitRequestEntity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.FileProviderUtils;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class NewReportActivity extends AppCompatActivity implements ReportContract.addView {

    //图片
    public final static int GALLERY_REQUEST_CODE = 1;

    //语音
    public final static int AUDIO_REQUEST_CODE = 2;

    //视频
    public final static int VIDEO_REQUEST_CODE = 3;

    @BindView(R.id.newreport_type)
    TextView newreportType;
    @BindView(R.id.newreport_text)
    EditText newreportText;

    @BindView(R.id.newreport_tovillagemgr)
    CheckBox newreportToVillageMgr;

    private String toVillageMgrResult;

    @BindView(R.id.image_upload)
    TextView imageUpload;

    @BindView(R.id.recyclerview_image_upload1)
    RecyclerView imageRecylerView;


    @BindView(R.id.audio_upload)
    TextView audioUpload;

    @BindView(R.id.recyclerview_audio_upload1)
    RecyclerView audioRecylerView;

    @BindView(R.id.video_upload)
    TextView videoUpload;

    @BindView(R.id.recyclerview_video_upload1)
    RecyclerView videoRecylerView;

    private List<NewReportEntity> dataList;
    private String type;
    private QMUITipDialog dataDialog, typeDialog;
    private ReportContract.Presenter mPresenter;

    //图片
    private ReportUploadImageAdapter imageAdapter;

    //语音
    private ReportUploadAudioAdapter audioAdapter;

    //视频
    private ReportUploadVideoAdapter videoAdapter;


    //图片
    private List<File> fileList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();
    private List<String> uploadImageList = new ArrayList<>();


    //语音
    private List<File> fileAudioList = new ArrayList<>();
    private List<String> audioList = new ArrayList<>();
    private List<String> uploadAudioList = new ArrayList<>();

    //视频
    private List<File> fileVideoList = new ArrayList<>();
    private List<String> videoList = new ArrayList<>();
    private List<String> uploadVideoList = new ArrayList<>();



    //图片
    public List<String> getUploadImageList() {
        return uploadImageList;
    }

    public void setUploadImageList(List<String> uploadImageList) {
        this.uploadImageList = uploadImageList;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(final List<String> imageList) {
        this.imageList = imageList;
        //初始化图片列表布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        imageRecylerView.setLayoutManager(gridLayoutManager);
        imageAdapter = new ReportUploadImageAdapter(this,this);
        imageAdapter.setImageList(imageList);
        imageRecylerView.setAdapter(imageAdapter);
    }

    //语音
    public List<String> getUploadAudioList() {
        return uploadAudioList;
    }

    public void setUploadAudioList(List<String> uploadAudioList) {
        this.uploadAudioList = uploadAudioList;
    }

    public List<File> getFileAudioList() {
        return fileAudioList;
    }

    public void setFileAudioList(List<File> fileAudioList) {
        this.fileAudioList = fileAudioList;
    }

    public List<String> getAudioList() {
        return audioList;
    }

    public void setAudioList(final List<String> audioList) {
        this.audioList = audioList;
        //初始化语音列表布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        audioRecylerView.setLayoutManager(gridLayoutManager);
        audioAdapter = new ReportUploadAudioAdapter(this,this);
        audioAdapter.setAudioList(audioList);
        audioRecylerView.setAdapter(audioAdapter);
    }

    //视频

    public List<String> getUploadVideoList() {
        return uploadVideoList;
    }

    public void setUploadVideoList(List<String> uploadVideoList) {
        this.uploadVideoList = uploadVideoList;
    }

    public List<File> getFileVideoList() {
        return fileVideoList;
    }

    public void setFileVideoList(List<File> fileVideoList) {
        this.fileVideoList = fileVideoList;
    }

    public List<String> getVideoList() {
        return videoList;
    }

    public void setVideoList(final List<String> videoList) {
        this.videoList = videoList;
        //初始化视频列表布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        videoRecylerView.setLayoutManager(gridLayoutManager);
        videoAdapter = new ReportUploadVideoAdapter(this,this);
        videoAdapter.setVideoList(videoList);
        videoRecylerView.setAdapter(videoAdapter);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fullnewreport);
        ButterKnife.bind(this);

        onCreateNewDialog();
        initView();
        initData();

        newreportToVillageMgr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    toVillageMgrResult=newreportToVillageMgr.getText().toString();
                }else{
                    toVillageMgrResult="";
                }
            }
        });
    }

    private void initData() {
        typeDialog.show();
        mPresenter.getReportType(BaseApplication.prm + "");
    }

    private void initView() {
        mPresenter = new ReportPresenter(this, this);
        dataList = new ArrayList<>();
    }

    @OnClick({R.id.task_back, R.id.newreport_choose, R.id.newreport_ok,
            R.id.image_choose, R.id.image_upload,
            R.id.audio_choose, R.id.audio_upload,
            R.id.video_choose, R.id.video_upload
    })
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

                if (imageList.size()>0 && (uploadImageList==null||uploadImageList.size()==0)) {
                    Toast.makeText(getApplicationContext(), "您有图片未上传，请点击上传", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (audioList.size()>0 && (uploadAudioList==null||uploadAudioList.size()==0)) {
                    Toast.makeText(getApplicationContext(), "您有语音未上传，请点击上传", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (videoList.size()>0 && (uploadVideoList==null||uploadVideoList.size()==0)) {
                    Toast.makeText(getApplicationContext(), "您有视频未上传，请点击上传", Toast.LENGTH_SHORT).show();
                    return;
                }

                mPresenter.getAddReport(new Gson().toJson(new ReportSubmitRequestEntity(type, newreportText.getText().toString(),toVillageMgrResult,uploadImageList,uploadAudioList,uploadVideoList)));
                break;
            case R.id.image_choose://选择图片
                openPic(this,GALLERY_REQUEST_CODE);
                break;
            case R.id.image_upload://上传图片
                if (fileList==null || fileList.size()==0){
                    Toast.makeText(getApplicationContext(), "请选择图片后上传", Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.uploadImages(fileList);
                }
                break;

            case R.id.audio_choose://选择语音
                openAudio(this,AUDIO_REQUEST_CODE);
                break;
            case R.id.audio_upload://上传语音
                if (fileAudioList==null || fileAudioList.size()==0){
                    Toast.makeText(getApplicationContext(), "请选择语音后上传", Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.uploadAudios(fileAudioList);
                }
                break;

            case R.id.video_choose://选择视频
                openVideo(this,VIDEO_REQUEST_CODE);
                break;
            case R.id.video_upload://上传视频
                if (fileVideoList==null || fileVideoList.size()==0){
                    Toast.makeText(getApplicationContext(), "请选择视频后上传", Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.uploadVideos(fileVideoList);
                }
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
    public void setUploadImageListFromServer(List<String> imageList) {
        setUploadImageList(imageList);
        Toast.makeText(this, "图片上传成功", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setUploadAudioListFromServer(List<String> audioList) {
        setUploadAudioList(audioList);
        Toast.makeText(this, "语音上传成功", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setUploadVideoListFromServer(List<String> videoList) {
        setUploadVideoList(videoList);
        Toast.makeText(this, "视频上传成功", Toast.LENGTH_SHORT).show();
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


    /**
     * @param activity    当前activity
     * @param requestCode 打开相册的请求码
     */
    public  void openPic(Activity activity, int requestCode) {
        AppUtils.getPermission(activity);
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT );
        photoPickerIntent.setType("image/*");
        photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        activity.startActivityForResult(photoPickerIntent, requestCode);
    }

    /**
     * @param activity    当前activity
     * @param requestCode 打开本地语音的请求码
     */
    public  void openAudio(Activity activity, int requestCode) {
        AppUtils.getPermission(activity);
        Intent audioPickerIntent = new Intent(Intent.ACTION_GET_CONTENT );
        audioPickerIntent.setType("audio/*");
        audioPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        activity.startActivityForResult(audioPickerIntent, requestCode);
    }

    /**
     * @param activity    当前activity
     * @param requestCode 打开本地视频的请求码
     */
    public  void openVideo(Activity activity, int requestCode) {
        AppUtils.getPermission(activity);
        Intent videoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT );
        videoPickerIntent.setType("video/*");
        videoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        activity.startActivityForResult(videoPickerIntent, requestCode);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageList.clear();
        fileList.clear();
        audioList.clear();
        fileAudioList.clear();
        videoList.clear();
        fileVideoList.clear();
        Uri uri;
        if (requestCode == 301 && resultCode == RESULT_OK) {
            Log.e("NewReportActresult----",data.toString());
        }else if (requestCode==1 && resultCode == RESULT_OK){
            Log.e("NewReportActresult1----",data.toString());
            ClipData imageNames = data.getClipData();
            if (imageNames != null){
                for (int i=0; i<imageNames.getItemCount(); i++){
                    Uri imageUri = imageNames.getItemAt(i).getUri();
                    imageList.add(imageUri.toString());
                    String sPath1 = FileProviderUtils.getPath(this, imageUri);
//                    fileList.add(FileProviderUtils.uri2File(this,imageUri));
                    Log.e("sPath1-----",sPath1);
                    Log.e("file-----",String.valueOf(new File(sPath1).exists()));
                    fileList.add(new File(sPath1));
                }
//                Log.e("imageUri-----",imageList.toString());
                this.setImageList(imageList);
                this.setFileList(fileList);
            } else {
                uri = data.getData();
                String sPath1 = FileProviderUtils.getPath(this, uri);
                imageList.add(uri.toString());
                fileList.add(new File(sPath1));
                this.setImageList(imageList);
                this.setFileList(fileList);
            }
        }else if (requestCode==2 && resultCode == RESULT_OK){
            Log.e("NewReportActresult2----",data.toString());
            ClipData audioNames = data.getClipData();
            if (audioNames != null){
                for (int i=0; i<audioNames.getItemCount(); i++){
                    Uri audioUri = audioNames.getItemAt(i).getUri();
                    audioList.add(audioUri.toString());
                    String sPath1 = FileProviderUtils.getPath(this, audioUri);
                    Log.e("sPath1--Audio-----",sPath1);
                    Log.e("file---Audio-----",String.valueOf(new File(sPath1).exists()));
                    fileAudioList.add(new File(sPath1));
                }
                this.setAudioList(audioList);
                this.setFileAudioList(fileAudioList);
            } else {
                uri = data.getData();
                String sPath1 = FileProviderUtils.getPath(this, uri);
                audioList.add(uri.toString());
                fileAudioList.add(new File(sPath1));
                this.setAudioList(audioList);
                this.setFileAudioList(fileAudioList);
            }

        }else if (requestCode==3 && resultCode == RESULT_OK){
            Log.e("NewReportActresult3----",data.toString());
            ClipData videoNames = data.getClipData();
            if (videoNames != null){
                for (int i=0; i<videoNames.getItemCount(); i++){
                    Uri videoUri = videoNames.getItemAt(i).getUri();
                    videoList.add(videoUri.toString());
                    String sPath1 = FileProviderUtils.getPath(this, videoUri);
                    Log.e("sPath1--Video-----",sPath1);
                    Log.e("file---Video-----",String.valueOf(new File(sPath1).exists()));
                    fileVideoList.add(new File(sPath1));
                }
                this.setVideoList(videoList);
                this.setFileVideoList(fileVideoList);
            } else {
                uri = data.getData();
                String sPath1 = FileProviderUtils.getPath(this, uri);
                videoList.add(uri.toString());
                fileVideoList.add(new File(sPath1));
                this.setVideoList(videoList);
                this.setFileVideoList(fileVideoList);
            }

        }


    }
}
