package wgt.module.cn.com.wgt_sample.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.changepassword.ChangePassWordActivity;

public class PersonActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_person);
        ButterKnife.bind(this);

        username.setText(BaseApplication.username);

        if (savedInstanceState != null) {
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.username = savedInstanceState.getString("username");
            BaseApplication.userid = savedInstanceState.getString("userid");
            username.setText(BaseApplication.username);
//            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
        }
    }

    @OnClick({R.id.person_back, R.id.changepassword, R.id.loginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_back:
                finish();
                break;
            case R.id.changepassword:
                Intent intent = new Intent(this, ChangePassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.loginout:
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("确定退出程序？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BaseApplication.getInstance().addActivity(PersonActivity.this);
                                dialog.dismiss();
                                BaseApplication.getInstance().exit(getApplicationContext());
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("prm", BaseApplication.prm);
        outState.putString("token", BaseApplication.token);
        outState.putString("userid", BaseApplication.userid);
        outState.putString("username", BaseApplication.username);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
