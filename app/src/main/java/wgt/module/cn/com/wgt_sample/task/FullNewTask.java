package wgt.module.cn.com.wgt_sample.task;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jelly.mango.ImageSelectListener;
import com.jelly.mango.Mango;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.TaskImageAdapter;
import wgt.module.cn.com.wgt_sample.adapter.TaskNameeAdapter;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.MyListView;


/**
 * Created by skc on 2020/6/17.
 */
public class FullNewTask extends Dialog {

    public View view;
    Unbinder unbinder;

    @BindView(R.id.newtask_zpperson)
    MyListView newtaskZpperson;
    @BindView(R.id.newtask_text)
    EditText newtaskText;

    @BindView(R.id.image_upload)
    TextView imageUpload;

    @BindView(R.id.recyclerview_image_upload)
    RecyclerView imageRecylerView;

    private Task task;
    private List<String> personnameList, receiveIdList, receiveRoleIdList;
    private TaskNameeAdapter adapter;
    private List<PersonEntity> personList;
    private List<List<PersonEntity.PersonSonEntity>> personSonList;
    // 相册选择回传吗
    public final static int GALLERY_REQUEST_CODE = 1;
    private TaskActivity taskActivity;

    private TaskImageAdapter imageAdapter;

    //本地获取得图片列表
    private List<String> imageList=new ArrayList<>();
    private List<File> fileList  = new ArrayList<>();
    //上传到服务器后得图片列表
    private List<String> uploadImageList = new ArrayList<>();

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        imageRecylerView.setLayoutManager(gridLayoutManager);
        imageAdapter = new TaskImageAdapter(getContext(),this);
        imageAdapter.setImageList(imageList);
        imageRecylerView.setAdapter(imageAdapter);
    }

    public FullNewTask(Context context, List<PersonEntity> personList,TaskActivity taskActivity) {
        super(context);
        this.personList = personList;
        this.taskActivity = taskActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = LayoutInflater.from(getContext()).inflate(R.layout.newtask_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        personnameList = new ArrayList<>();
        receiveIdList = new ArrayList<>();
        receiveRoleIdList = new ArrayList<>();
        adapter = new TaskNameeAdapter(personnameList, getContext());
        newtaskZpperson.setAdapter(adapter);

        newtaskZpperson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getContext())
                        .setCancelable(false)
                        .setTitle("是否删除指派人？")
                        .setMessage(personnameList.get(position))
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                personnameList.remove(position);
                                receiveIdList.remove(position);
                                receiveRoleIdList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });

        personSonList = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++) {
            if (BaseApplication.prm == 1004) {
                PersonEntity personEntity = personList.get(i);
                PersonEntity.PersonSonEntity sonEntity = new PersonEntity.PersonSonEntity();
                sonEntity.setAreaName(personEntity.getAreaName());
                sonEntity.setRoleId(personEntity.getRoleId());
                sonEntity.setUserId(personEntity.getUserId());
                personList.get(i).getChilds().add(0, sonEntity);
            }
            personSonList.add(personList.get(i).getChilds());
        }
        //9.23添加预览图片
        //从taskactivity中获取图片列表


    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @OnClick({R.id.task_back, R.id.newtask_choose, R.id.newtask_ok,R.id.image_choose,R.id.image_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_back:
                dismiss();
                break;
            case R.id.newtask_choose:
                optionsPick(personList, personSonList);
                break;
            case R.id.newtask_ok:
                if (personnameList.size() == 0 || receiveIdList.size() == 0 || receiveRoleIdList.size() == 0) {
                    Toast.makeText(getContext(), "请选择接受人", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(newtaskText.getText().toString())) {
                    Toast.makeText(getContext(), "请输入任务描述", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (imageList.size()>0 && (uploadImageList==null||uploadImageList.size()==0)) {
                    Toast.makeText(getContext(), "您有图片未上传，请点击上传", Toast.LENGTH_SHORT).show();
                    return;
                }
                String imagelist = uploadImageList.toString();
                task.addTask(newtaskText.getText().toString(), receiveIdList, receiveRoleIdList,imagelist.substring(1,imagelist.length()-1));

                break;
            case R.id.image_upload:
                if (fileList==null||fileList.size()==0){
                    Toast.makeText(getContext(), "请选择图片后上传", Toast.LENGTH_SHORT).show();
                } else {
                    //上传图片
                    task.uploadImages(fileList);
                }
                break;
            case R.id.image_choose:
                openPic(taskActivity,GALLERY_REQUEST_CODE);
                break;
        }
    }
    /**
     * @param activity    当前activity
     * @param requestCode 打开相册的请求码
     */
    public  void openPic(Activity activity, int requestCode) {
        AppUtils.getPermission(activity);
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        activity.startActivityForResult(photoPickerIntent, requestCode);

    }

    private void optionsPick(final List<PersonEntity> dataList, final List<List<PersonEntity.PersonSonEntity>> dataSonList) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                if (receiveIdList.contains(dataSonList.get(options1).get(option2).getUserId())
                        && receiveRoleIdList.contains(dataSonList.get(options1).get(option2).getRoleId())) {
                    Toast.makeText(getContext(), "已添加相同指派人员，请勿重复添加", Toast.LENGTH_SHORT).show();
                } else {
                    personnameList.add(dataList.get(options1).getAreaName() + " - " + dataSonList.get(options1).get(option2).getAreaName());
                    receiveIdList.add(dataSonList.get(options1).get(option2).getUserId());
                    receiveRoleIdList.add(dataSonList.get(options1).get(option2).getRoleId());
                    adapter.notifyDataSetChanged();
                }
            }
        })
                //标题文字。
                .setTitleText("指派给")
                //确定按钮文字颜色。
                .setSubCalSize(14)
                .isDialog(true)
                .setSubmitColor(getContext().getResources().getColor(R.color.colorAccent))
                //取消按钮文字颜色。
                .setCancelColor(getContext().getResources().getColor(R.color.colorAccent))
                .build();
        pvOptions.setPicker(dataList, dataSonList);
        pvOptions.show();
    }

    public interface Task {
        //9.22日改动加图片字段
        void addTask(String data, List<String> receiveId, List<String> receiveRoleId, String images);
        void uploadImages(List<File> fileList);
    }

}
