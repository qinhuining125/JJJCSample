package wgt.module.cn.com.wgt_sample.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.api.ApiInterface;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.JubaoStateEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskStateEntity;
import wgt.module.cn.com.wgt_sample.entity.XunchaProcessEntity;
import wgt.module.cn.com.wgt_sample.jubao.JubaoActivity;
import wgt.module.cn.com.wgt_sample.jubao.NewjubaiBaixingActivity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;
import wgt.module.cn.com.wgt_sample.xuncha.XunchaActivity;

public class MainXunchaActivity extends AppCompatActivity {
    @BindView(R.id.main_xuncha)
    LinearLayout layoutXuncha;
    @BindView(R.id.main_jubao)
    LinearLayout layoutJubao;
    @BindView(R.id.view_one)
    View viewOnePlaceHolder;
    @BindView(R.id.view_two)
    View viewTwoPlaceHolder;
    @BindView(R.id.main_jubao_baixing)
    LinearLayout layoutJubaoBaixing;
    @BindView(R.id.xuncha_back)
    ImageView xunchaBack;

    private String XunchaID;
    private String xunchaName;
    private Badge xunchaBadge,jubaoBadge;
    @BindView(R.id.xuncha)
    ImageView xuncha;
    @BindView(R.id.jubao)
    ImageView jubao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main_xuncha);
        ButterKnife.bind(this);
        BaseApplication.getInstance().addActivity(this);
        if (savedInstanceState != null) {
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.username = savedInstanceState.getString("username");
            BaseApplication.userid = savedInstanceState.getString("userid");
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
        }
        //11.10权限设置。
        if ( BaseApplication.prm >= 1007){ //巡察办工作人员和超级管理员
            layoutJubaoBaixing.setVisibility(View.GONE);
            viewOnePlaceHolder.setVisibility(View.VISIBLE);

        }else if (BaseApplication.prm==1001){//网格员
            layoutXuncha.setVisibility(View.GONE);
            layoutJubao.setVisibility(View.GONE);
            viewOnePlaceHolder.setVisibility(View.VISIBLE);
            viewTwoPlaceHolder.setVisibility(View.VISIBLE);

        }else if(BaseApplication.prm==1002){//联络员
            layoutJubao.setVisibility(View.GONE);
            viewOnePlaceHolder.setVisibility(View.VISIBLE);
        }
        else if(BaseApplication.prm==1003){//乡镇纪委管理员
            layoutJubaoBaixing.setVisibility(View.GONE);
            layoutJubao.setVisibility(View.GONE);
            viewOnePlaceHolder.setVisibility(View.VISIBLE);
            viewTwoPlaceHolder.setVisibility(View.VISIBLE);
        }
    }
    @OnClick({R.id.main_xuncha,R.id.main_jubao,R.id.main_jubao_baixing,R.id.xuncha_back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.main_xuncha:
                Intent XunchaIntent = new Intent(this, XunchaActivity.class);
                startActivity(XunchaIntent);
                break;
            case R.id.main_jubao:
                Intent JubaoIntent = new Intent(this, JubaoActivity.class);
                startActivity(JubaoIntent);
                break;
            case R.id.main_jubao_baixing:
                getProcessState();

                break;
            case R.id.xuncha_back:
                finish();
                break;
        }


    }
    @Override
    protected void onResume() {
        super.onResume();
        getJubaoState();
        getXunchaState();
    }
    public void getXunchaState(){
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getXunchaState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<TaskStateEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HttpResult<TaskStateEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            setXunchaState(httpResult.getResult());
                        }
                    }
                });
    }
    public void getJubaoState(){
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getJubaoState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<JubaoStateEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HttpResult<JubaoStateEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            setJubaoState(httpResult.getResult());
                        }
                    }
                });
    }
    public void getProcessState() {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getisHaveProcess()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<XunchaProcessEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HttpResult<XunchaProcessEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            XunchaID = httpResult.getResult().getId();
                            xunchaName = httpResult.getResult().getPatrolName();
                            Intent JubaoBaixingIntent = new Intent(MainXunchaActivity.this, NewjubaiBaixingActivity.class);
                            JubaoBaixingIntent.putExtra("id",XunchaID);
                            JubaoBaixingIntent.putExtra("name",xunchaName);
                            startActivity(JubaoBaixingIntent);

                        }else {
                            setNoProcess(httpResult.getMessage());
                        }
                    }
                });
    }
    private  void setNoProcess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    private void setJubaoState(JubaoStateEntity result) {
        if (jubaoBadge == null) {
            jubaoBadge = new QBadgeView(this).bindTarget(jubao).setBadgeText("!");
        }
        if (result.getPending() || result.getAssign()) {
            jubaoBadge.setShowShadow(false);
            jubaoBadge.setBadgeText("!");
        } else {
            jubaoBadge.hide(false);
        }
    }
    private void setXunchaState(TaskStateEntity result) {
        if (xunchaBadge == null) {
            xunchaBadge = new QBadgeView(this).bindTarget(xuncha).setBadgeText("!");
        }
        if (result.isAccept()) {
            xunchaBadge.setShowShadow(false);
            xunchaBadge.setBadgeText("!");
        } else {
            xunchaBadge.hide(false);
        }
    }
}
