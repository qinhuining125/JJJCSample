package wgt.module.cn.com.wgt_sample.jubao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.TaskNameeAdapter;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.JubaopersonEntity;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.utils.MyListView;

/**
 * Created by skc on 2020/6/17.
 */
public class FullNewJubao extends Dialog {

    public View view;
    Unbinder unbinder;

    @BindView(R.id.newtask_zpperson)
    MyListView newtaskZpperson;
    @BindView(R.id.newtask_text)
    EditText newtaskText;

    private Task task;
    private List<String> personnameList, receiveRoleIdList;
    private TaskNameeAdapter adapter;
    private List<JubaopersonEntity> personList;
//    private List<List<PersonEntity.PersonSonEntity>> personSonList;
    // 相册选择回传吗
    public final static int GALLERY_REQUEST_CODE = 1;
    private JubaoActivity jubaoActivity;

    public FullNewJubao(Context context, List<JubaopersonEntity> personList, JubaoActivity jubaoActivity) {
        super(context);
        this.personList = personList;
        this.jubaoActivity = jubaoActivity;
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
                        .setPositiveButton("确定", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                personnameList.remove(position);
                                receiveRoleIdList.remove(position);
                                adapter.notifyDataSetChanged();



                            }
                        })
                        .setNegativeButton("取消", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });

//        personSonList = new ArrayList<>();
//        for (int i = 0; i < personList.size(); i++) {
//            if (BaseApplication.prm == 1004) {
//                PersonEntity personEntity = personList.get(i);
//                PersonEntity.PersonSonEntity sonEntity = new PersonEntity.PersonSonEntity();
//                sonEntity.setAreaName(personEntity.getAreaName());
//                sonEntity.setRoleId(personEntity.getRoleId());
//                sonEntity.setUserId(personEntity.getUserId());
//                personList.get(i).getChilds().add(0, sonEntity);
//            }
//            personSonList.add(personList.get(i).getChilds());
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @OnClick({R.id.task_back, R.id.newtask_choose, R.id.newtask_ok,R.id.image_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_back:
                dismiss();
                break;
            case R.id.newtask_choose:
                optionsPick(personList);
                break;
            case R.id.newtask_ok:
                if (personnameList.size() == 0 || receiveRoleIdList.size() == 0) {
                    Toast.makeText(getContext(), "请选择接受人", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(newtaskText.getText().toString())) {
                    Toast.makeText(getContext(), "请输入任务描述", Toast.LENGTH_SHORT).show();
                    return;
                }

                task.addTask(newtaskText.getText().toString(), receiveRoleIdList);
                break;
            case R.id.image_upload:
                openPic(jubaoActivity,GALLERY_REQUEST_CODE);
                break;
        }
    }
    /**
     * @param activity    当前activity
     * @param requestCode 打开相册的请求码
     */
    public static void openPic(Activity activity, int requestCode) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        activity.startActivityForResult(photoPickerIntent, requestCode);
    }

    private void optionsPick(final List<JubaopersonEntity> dataList) {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    personnameList.add(dataList.get(options1).getValue());
                    receiveRoleIdList.add(dataList.get(options1).getKey());
                    adapter.notifyDataSetChanged();
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
        pvOptions.setPicker(dataList);
        pvOptions.show();
    }

    public interface Task {
        void addTask(String data, List<String> receiveRoleId);
    }

}
