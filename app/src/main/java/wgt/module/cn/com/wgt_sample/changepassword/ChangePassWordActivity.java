package wgt.module.cn.com.wgt_sample.changepassword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.entity.ChengePasswordEntity;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ChangePassWordActivity extends AppCompatActivity implements ChangePasswordContract.View {

    @BindView(R.id.changepassword_newpassword)
    EditText changepasswordNewpassword;
    @BindView(R.id.changepassword_newpasswordture)
    EditText changepasswordNewpasswordture;
    @BindView(R.id.changepassword_loglayout)
    LinearLayout changepasswordLoglayout;
    @BindView(R.id.changepassword_oldpassword)
    EditText changepasswordOldpassword;
    @BindView(R.id.changepassword_back)
    ImageView changepasswordBack;

    // 等待框。
    private QMUITipDialog dataDialog;
    // Presenter。
    private ChangePasswordContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_psd);
        ButterKnife.bind(this);

        onCreateNewDialog();
        initView();
        onClickListen();
    }

    @OnClick({R.id.changepassword_back, R.id.changepassword_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changepassword_back:
                finish();
                break;
            case R.id.changepassword_button:

                if (TextUtils.isEmpty(changepasswordOldpassword.getText().toString())) {
                    Toast.makeText(this, "请输入旧密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(changepasswordNewpassword.getText().toString())) {
                    Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(changepasswordNewpasswordture.getText().toString())) {
                    Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (changepasswordLoglayout.getVisibility() == View.VISIBLE) {
                    Toast.makeText(this, "请保持确认密码一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                dataDialog.show();
                Gson gson = new Gson();
                mPresenter.changePassword(gson.toJson(new ChengePasswordEntity(changepasswordOldpassword.getText().toString(), changepasswordNewpassword.getText().toString())));
                break;
        }
    }

    private void initView() {
        mPresenter = new ChangePasswordPresenter(this, this);
    }

    private void onClickListen() {
        changepasswordNewpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(changepasswordNewpasswordture.getText().toString()) && !TextUtils.isEmpty(changepasswordNewpassword.getText().toString())) {
                    if (changepasswordNewpassword.getText().toString().equals(changepasswordNewpasswordture.getText().toString())) {
                        changepasswordLoglayout.setVisibility(View.GONE);
                    } else {
                        changepasswordLoglayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    changepasswordLoglayout.setVisibility(View.GONE);
                }
            }
        });

        changepasswordNewpasswordture.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(changepasswordNewpassword.getText().toString()) && !TextUtils.isEmpty(changepasswordNewpasswordture.getText().toString())) {
                    if (changepasswordNewpassword.getText().toString().equals(changepasswordNewpasswordture.getText().toString())) {
                        changepasswordLoglayout.setVisibility(View.GONE);
                    } else {
                        changepasswordLoglayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    changepasswordLoglayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void setPresenter(ChangePasswordContract.Presenter presenter) {
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

    @Override
    public void success(boolean isSuccess) {
        Toast.makeText(this, "修改成功。", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 等待框。
     */
    private void onCreateNewDialog() {
        dataDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("修改中...")
                .create();
        dataDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
