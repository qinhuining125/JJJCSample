package wgt.module.cn.com.wgt_sample.jubao;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wgt.module.cn.com.wgt_sample.R;

/**
 * Created by skc on 2020/6/17.
 */
public class FullJubaoFinish extends Dialog {

    public View view;

    Unbinder unbinder;
    @BindView(R.id.finishtask_text)
    EditText finishtaskText;

    private BJJG bj;

    public FullJubaoFinish(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = LayoutInflater.from(getContext()).inflate(R.layout.finishtask_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    public void setBJDATA(BJJG bj) {
        this.bj = bj;
    }

    public interface BJJG {
        void getBJ(String data);
    }

    @OnClick({R.id.taskfinih_dialog_canel, R.id.taskfinih_dialog_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.taskfinih_dialog_canel:
                dismiss();
                break;
            case R.id.taskfinih_dialog_ok:
                if (TextUtils.isEmpty(finishtaskText.getText().toString())) {
                    Toast.makeText(getContext(), "请输入办结结果描述", Toast.LENGTH_SHORT).show();
                    return;
                }
                bj.getBJ(finishtaskText.getText().toString());
                break;
        }
    }
}
