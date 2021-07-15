package wgt.module.cn.com.wgt_sample.jubao;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.JubaoUploadImageAdapter;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.JubaoBaixingSubmitEntivity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.FileProviderUtils;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class NewjubaiBaixingActivity  extends AppCompatActivity implements JubaoContract.BaixingJubaoView{
    public final static int GALLERY_REQUEST_CODE = 1;
    @BindView(R.id.jubao_text)
    EditText jubaoText;
    @BindView(R.id.jubao_object)
    EditText jubaoObject;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.xuncha_name)
    TextView xunchaName;
    @BindView(R.id.recyclerview_image_upload)
    RecyclerView recyclerView;

    JubaoContract.Presenter mPresenter;

    private List<String> imageList = new ArrayList<>();
    private List<String> uploadImageList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();
    private String xunchaID;
    private String xunchaNameformMain;

    private JubaoUploadImageAdapter imageAdapter;

    public List<String> getUploadImageList() {
        return uploadImageList;
    }

    public void setUploadImageList(List<String> uploadImageList) {
        this.uploadImageList = uploadImageList;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jubao_form);
        ButterKnife.bind(this);
        mPresenter = new JubaoPresenter(this,this);
        xunchaID = getIntent().getStringExtra("id");
        xunchaNameformMain = getIntent().getStringExtra("name");
        xunchaName.setText("巡查轮次："+xunchaNameformMain);
        AppUtils.getPermission(this);
        if (savedInstanceState != null) {
//            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.userid = savedInstanceState.getString("userid");
            BaseApplication.username = savedInstanceState.getString("username");
        }
    }

    @OnClick({R.id.task_back, R.id.image_choose, R.id.newreport_ok,R.id.image_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.newreport_ok:
                //提交
                if (TextUtils.isEmpty(editPhone.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "请输入联系方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(jubaoObject.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "请输入举报对象", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(jubaoText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "请输入举报内容", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (imageList.size()>0 && (uploadImageList==null||uploadImageList.size()==0)) {
                    Toast.makeText(getApplicationContext(), "您有图片未上传，请点击上传", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.saveData(new Gson().toJson(
                        new JubaoBaixingSubmitEntivity(BaseApplication.userid,
                                BaseApplication.username,editPhone.getText().toString(),
                                jubaoObject.getText().toString(),jubaoText.getText().toString(),"1","","",BaseApplication.prm,xunchaID,uploadImageList)));
                break;
            case R.id.image_choose:
                openPic(this,GALLERY_REQUEST_CODE);

                break;
            case R.id.image_upload:
                if (fileList==null || fileList.size()==0){
                    Toast.makeText(getApplicationContext(), "请选择图片后上传", Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.uploadImages(fileList);
                }
                break;
            case R.id.task_back:
                finish();
                break;
        }
    }

    @Override
    public void saveData(String jsonData) {
        //提示成功
        Toast.makeText(this, jsonData, Toast.LENGTH_SHORT).show();
        //刷新页面
        onRefrush();
    }
    private void onRefrush() {
       editPhone.setText("");
       jubaoObject.setText("");
       jubaoText.setText("");
       imageList.clear();
       fileList.clear();
       imageAdapter.setImageList(imageList);
    }

    @Override
    public void setUploadImageListFromServer(List<String> imageList) {
        setUploadImageList(imageList);
        Toast.makeText(this, "图片上传成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(JubaoContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void dataDialogDissmis() {

    }

    @Override
    public void ERROR(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageList.clear();
        fileList.clear();
        Uri uri;
        if (requestCode==1 && resultCode == RESULT_OK){
            //原来的代码
            if (data.getData() == null){
                ClipData imageNames = data.getClipData();
                for (int i=0; i<imageNames.getItemCount(); i++){
                    Uri imageUri = imageNames.getItemAt(i).getUri();
                    imageList.add(imageUri.toString());
                    String sPath1 = FileProviderUtils.getPath(this, imageUri);
                    fileList.add(new File(sPath1));
                }
            } else {
                uri = data.getData();
                String sPath1 = FileProviderUtils.getPath(this, uri);
                imageList.add(uri.toString());
                fileList.add(new File(sPath1));
            }
            setImageList(imageList);
        }
    }
    /**
     * 解决小米手机上获取图片路径为null的情况
     * @param intent
     * @return
     */
    public Uri geturi(android.content.Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/*"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri.parse("content://media/external/images/media/" + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }
    public void setImageList(final List<String> imageList) {
        this.imageList = imageList;
        //初始化图片列表布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        imageAdapter = new JubaoUploadImageAdapter(this,this);
        imageAdapter.setImageList(imageList);
        recyclerView.setAdapter(imageAdapter);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("prm", BaseApplication.prm);
        outState.putString("token", BaseApplication.token);
        outState.putString("userid", BaseApplication.userid);
        outState.putString("username", BaseApplication.username);
        super.onSaveInstanceState(outState, outPersistentState);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

}
