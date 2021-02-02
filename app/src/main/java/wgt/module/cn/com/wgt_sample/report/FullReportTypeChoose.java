package wgt.module.cn.com.wgt_sample.report;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wgt.module.cn.com.wgt_sample.R;

/**
 * Created by skc on 2020/6/17.
 */
public class FullReportTypeChoose extends Dialog {

    public View view;
    // buttnife绑定。
    Unbinder unbinder;
    @BindView(R.id.reportType1)
    CheckBox reportType1;
    @BindView(R.id.reportType2)
    CheckBox reportType2;
    @BindView(R.id.reportType3)
    CheckBox reportType3;
    @BindView(R.id.reportType4)
    CheckBox reportType4;
    @BindView(R.id.reportType5)
    CheckBox reportType5;
    @BindView(R.id.reportType6)
    CheckBox reportType6;
    @BindView(R.id.reportType7)
    CheckBox reportType7;
    @BindView(R.id.reportType8)
    CheckBox reportType8;
    @BindView(R.id.reportType9)
    CheckBox reportType9;
    @BindView(R.id.reportType10)
    CheckBox reportType10;
    @BindView(R.id.reportType11)
    CheckBox reportType11;

    private String type;
    private setReportType srt;

    public FullReportTypeChoose(Context context, String type) {
        super(context);
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = LayoutInflater.from(getContext()).inflate(R.layout.reporttypechoose_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        if (!TextUtils.isEmpty(type)) {
            if (type.contains(",")) {
                String[] split = type.split(",");
                for (String aSplit : split) {
                    setCheckChoose(aSplit);
                }
            } else {
                setCheckChoose(type);
            }
        }
    }

    private void setCheckChoose(String type) {
        switch (type) {
            case "1":
                reportType1.setChecked(true);
                break;
            case "2":
                reportType2.setChecked(true);
                break;
            case "3":
                reportType3.setChecked(true);
                break;
            case "4":
                reportType4.setChecked(true);
                break;
            case "5":
                reportType5.setChecked(true);
                break;
            case "6":
                reportType6.setChecked(true);
                break;
            case "7":
                reportType7.setChecked(true);
                break;
            case "8":
                reportType8.setChecked(true);
                break;
            case "9":
                reportType9.setChecked(true);
                break;
            case "10":
                reportType10.setChecked(true);
                break;
            case "11":
                reportType11.setChecked(true);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    @OnClick({R.id.reporttypechoose_back, R.id.reportType_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reporttypechoose_back:
                dismiss();
                break;
            case R.id.reportType_ok:
                type = null;
                List<Integer> typeList = new ArrayList<>();
                if (reportType1.isChecked()) {
                    type = "1";
                    typeList.add(0);
                }
                if (reportType2.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "2";
                    } else {
                        type = type + ",2";
                    }
                    typeList.add(1);
                }
                if (reportType3.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "3";
                    } else {
                        type = type + ",3";
                    }
                    typeList.add(2);
                }
                if (reportType4.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "4";
                    } else {
                        type = type + ",4";
                    }
                    typeList.add(3);
                }
                if (reportType5.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "5";
                    } else {
                        type = type + ",5";
                    }
                    typeList.add(4);
                }
                if (reportType6.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "6";
                    } else {
                        type = type + ",6";
                    }
                    typeList.add(5);
                }
                if (reportType7.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "7";
                    } else {
                        type = type + ",7";
                    }
                    typeList.add(6);
                }
                if (reportType8.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "8";
                    } else {
                        type = type + ",8";
                    }
                    typeList.add(7);
                }
                if (reportType9.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "9";
                    } else {
                        type = type + ",9";
                    }
                    typeList.add(8);
                }
                if (reportType10.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "10";
                    } else {
                        type = type + ",10";
                    }
                    typeList.add(9);
                }

                if (reportType11.isChecked()) {
                    if (TextUtils.isEmpty(type)) {
                        type = "11";
                    } else {
                        type = type + ",11";
                    }
                    typeList.add(10);
                }

                if (!TextUtils.isEmpty(type)) {
                    srt.chooseType(type, typeList);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "请选择问题类型", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void setType(setReportType srt) {
        this.srt = srt;
    }

    public interface setReportType {
        void chooseType(String type, List<Integer> typeList);
    }
}
