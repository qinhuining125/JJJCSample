package wgt.module.cn.com.wgt_sample.report;

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
import wgt.module.cn.com.wgt_sample.entity.ReportpersonEntity;

/**
 * Created by skc on 2020/6/17.
 */
public class FullZBReport extends Dialog {

    public View view;
    Unbinder unbinder;

    @BindView(R.id.zptask_zpperson)
    TextView zptaskZpperson;
    @BindView(R.id.zptask_text)
    TextView zptaskText;

    private ZPTask task;
    private String text;
    private String receiveId, receiveRoleId;
    private List<ReportpersonEntity> personList;

    public FullZBReport(Context context, String text, List<ReportpersonEntity> personList) {
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
                optionsPick(zptaskZpperson, personList);
                break;
            case R.id.zptask_ok:
                if (TextUtils.isEmpty(zptaskZpperson.getText().toString())
                        || TextUtils.isEmpty(receiveRoleId)) {
                    Toast.makeText(getContext(), "请选择接受人", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if (TextUtils.isEmpty(zptaskText.getText().toString())) {
//                    Toast.makeText(getContext(), "请输入任务描述", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                task.zpTask( receiveRoleId);
                break;
        }
    }

    private void optionsPick(final TextView et, final List<ReportpersonEntity> dataList) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                et.setText(dataList.get(options1).getValue());
//                receiveId = dataSonList.get(options1).get(option2).getUserId();
                receiveRoleId = dataList.get(options1).getKey();
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
        try{
            pvOptions.setPicker(dataList);
            pvOptions.show();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public interface ZPTask {
        void zpTask(String roleId);
    }
    /**
     * 造一组数据
     */
    private List getData() {
        List list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new PersonEntity("1","1","1","1"));
            list.add(new PersonEntity("1","1","1","1"));
            list.add(new PersonEntity("1","1","1","1"));
            list.add(new PersonEntity("1","1","1","1"));
        }
        return list;

    }
}
