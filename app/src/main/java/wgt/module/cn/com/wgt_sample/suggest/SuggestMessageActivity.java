package wgt.module.cn.com.wgt_sample.suggest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.SuggestEntity;

public class SuggestMessageActivity extends AppCompatActivity {

    @BindView(R.id.suggestmessage_person)
    TextView suggestmessagePerson;
    @BindView(R.id.suggestmessage_time)
    TextView suggestmessageTime;
    @BindView(R.id.suggestmessage_content)
    TextView suggestmessageContent;

    private SuggestEntity.SuggestSonEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_suggestmessage);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.username = savedInstanceState.getString("username");
            BaseApplication.userid = savedInstanceState.getString("userid");
            data = new Gson().fromJson(savedInstanceState.getString("data"), SuggestEntity.SuggestSonEntity.class);
        } else {
            data = new Gson().fromJson(getIntent().getStringExtra("data"), SuggestEntity.SuggestSonEntity.class);
        }
        initView();
    }

    private void initView() {
        suggestmessagePerson.setText(data.getRoleName() + " - " + data.getUserName());
        suggestmessageTime.setText(long2DateString(data.getCreateTime()));
        suggestmessageContent.setText(data.getContent());
    }

    private String long2DateString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(time));
    }

    @OnClick(R.id.suggestmessage_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("prm", BaseApplication.prm);
        outState.putString("token", BaseApplication.token);
        outState.putString("userid", BaseApplication.userid);
        outState.putString("username", BaseApplication.username);
        outState.putString("data", new Gson().toJson(data));
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
