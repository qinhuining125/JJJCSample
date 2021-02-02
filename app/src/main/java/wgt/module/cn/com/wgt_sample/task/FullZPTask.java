package wgt.module.cn.com.wgt_sample.task;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
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
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;

/**
 * Created by skc on 2020/6/17.
 */
public class FullZPTask extends Dialog {

    public View view;
    Unbinder unbinder;

    @BindView(R.id.zptask_zpperson)
    TextView zptaskZpperson;
    @BindView(R.id.zptask_text)
    TextView zptaskText;

    private ZPTask task;
    private String text;
    private String receiveId, receiveRoleId;
    private List<PersonEntity> personList;
    private List<List<PersonEntity.PersonSonEntity>> personSonList;

    public FullZPTask(Context context, String text, List<PersonEntity> personList) {
        super(context);
        this.text = text;
        this.personList = personList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = LayoutInflater.from(getContext()).inflate(R.layout.zptask_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        zptaskText.setText(text);
        personSonList = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++) {
            personSonList.add(personList.get(i).getChilds());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    public void setZPTask(ZPTask task) {
        this.task = task;
    }

    @OnClick({R.id.zptask_back, R.id.zptask_choose, R.id.zptask_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zptask_back:
                dismiss();
                break;
            case R.id.zptask_choose:
                optionsPick(zptaskZpperson, personList, personSonList);
                break;
            case R.id.zptask_ok:
                if (TextUtils.isEmpty(zptaskZpperson.getText().toString())
                        || TextUtils.isEmpty(receiveId)
                        || TextUtils.isEmpty(receiveRoleId)) {
                    Toast.makeText(getContext(), "请选择接受人", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(zptaskText.getText().toString())) {
                    Toast.makeText(getContext(), "请输入任务描述", Toast.LENGTH_SHORT).show();
                    return;
                }

                task.zpTask(receiveId, receiveRoleId);
                break;
        }
    }

    private void optionsPick(final TextView et, final List<PersonEntity> dataList, final List<List<PersonEntity.PersonSonEntity>> dataSonList) {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                et.setText(dataList.get(options1).getAreaName() + " - " + dataSonList.get(options1).get(option2).getAreaName());
                receiveId = dataSonList.get(options1).get(option2).getUserId();
                receiveRoleId = dataSonList.get(options1).get(option2).getRoleId();
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

    public interface ZPTask {
        void zpTask(String receiveId, String receiveRoleId);
    }

}
