package wgt.module.cn.com.wgt_sample.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.LoginRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.User;
import wgt.module.cn.com.wgt_sample.entity.VersionEntity;
import wgt.module.cn.com.wgt_sample.main.MainActivity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.FullUpdateDialog;
import wgt.module.cn.com.wgt_sample.utils.RuntimeRationaleUpdata;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    @BindView(R.id.username)
    EditText loginUsername;
    @BindView(R.id.password)
    EditText loginPassword;
    @BindView(R.id.loginsign)
    LinearLayout loginSign;

    private QMUITipDialog dataDialog;
    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
        BaseApplication.getInstance().addActivity(this);
        QMUIStatusBarHelper.translucent(LoginActivity.this);
        QMUIStatusBarHelper.setStatusBarLightMode(LoginActivity.this);

        onCreateNewDialog();
        initView();
    }

    private void initView() {
        mPresenter = new LoginPresenter(this, this);

        String userInfoJson = getIntent().getStringExtra("UserInfoJson");
        if (!TextUtils.isEmpty(userInfoJson)) {
            Map<String, String> map = new Gson().fromJson(userInfoJson, LinkedHashMap.class);
            loginUsername.setText(map.get("userName"));
            loginPassword.setText(map.get("usePwd"));

            dataDialog.show();
            Gson gson = new Gson();
            mPresenter.login(gson.toJson(new LoginRequestEntity(loginUsername.getText().toString(), loginPassword.getText().toString())));
        } else {
            SharedPreferences userSharedPreference = AppUtils.getUserSharedPreference(this);
            loginUsername.setText(userSharedPreference.getString("username", ""));
            loginPassword.setText(userSharedPreference.getString("password", ""));
        }
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        if (TextUtils.isEmpty(loginUsername.getText().toString())) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            dataDialogDissmis();
            return;
        }

        if (TextUtils.isEmpty(loginPassword.getText().toString())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            dataDialogDissmis();
            return;
        }

        dataDialog.show();
        Gson gson = new Gson();
        mPresenter.login(gson.toJson(new LoginRequestEntity(loginUsername.getText().toString(), loginPassword.getText().toString())));
    }

    @Override
    public void loginSuccess(User result) {
        SharedPreferences.Editor edit = AppUtils.getUserSharedPreference(this).edit();
        edit.putString("username", loginUsername.getText().toString());
        edit.putString("password", loginPassword.getText().toString());
        edit.putBoolean("jzmm", true);
        edit.apply();

        BaseApplication.token = result.getToken();
        BaseApplication.prm = result.getRoleId();
        BaseApplication.userid = result.getId();
        BaseApplication.username = result.getUserName();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
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
                .setTipWord("正在登录...")
                .create();
        dataDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
