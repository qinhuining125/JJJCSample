package wgt.module.cn.com.wgt_sample.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

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
import wgt.module.cn.com.wgt_sample.entity.ReportStateEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskStateEntity;
import wgt.module.cn.com.wgt_sample.entity.VersionEntity;
import wgt.module.cn.com.wgt_sample.report.ReportActivity;
import wgt.module.cn.com.wgt_sample.suggest.SuggestActivity;
import wgt.module.cn.com.wgt_sample.task.TaskActivity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.FullUpdateDialog;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;
import wgt.module.cn.com.wgt_sample.utils.RuntimeRationaleUpdata;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_layout)
    LinearLayout mainLayout;
    @BindView(R.id.reportimg)
    ImageView report;
    @BindView(R.id.taskimg)
    ImageView task;
    @BindView(R.id.xunchawork)
    ImageView xuncha;
    @BindView(R.id.main_mk_layout)
    LinearLayout mainMkLayout;
    @BindView(R.id.main_report)
    LinearLayout layoutReport;
    @BindView(R.id.main_suggest)
    LinearLayout layoutuggest;
    @BindView(R.id.main_task)
    LinearLayout layoutTask;

    @BindView(R.id.view_one)
    View viewOnePlaceHolder;
    @BindView(R.id.view_two)
    View viewTwoPlaceHolder;


    @BindView(R.id.main_xunchawork)
    LinearLayout layoutXunchaWork;


    private Badge reportBadge, taskBadge,xunchaBadge;

    private VersionEntity loadVersion;
    private FullUpdateDialog fullUpdateDialog;


    @Override
    protected void onResume() {
        super.onResume();
        if(BaseApplication.prm>=1007){
            if(BaseApplication.prm == 1012
                    || (BaseApplication.prm >=3000  && BaseApplication.prm<=3999)){
                getReportState();
            }else {
                getJubaoState();
                getXunchaState();
            }
        } else if(BaseApplication.prm==1001){
            getReportState();
            getTaskState();
        }else if(BaseApplication.prm == 1002){
            getReportState();
            getTaskState();
            getXunchaState();
        }else if(BaseApplication.prm == 1003){
            getReportState();
            getTaskState();
            getXunchaState();
        }else{
            getReportState();
            getTaskState();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        BaseApplication.getInstance().addActivity(this);

        if (savedInstanceState != null) {
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.username = savedInstanceState.getString("username");
            BaseApplication.userid = savedInstanceState.getString("userid");
//            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
        }
        //11.10首页权限设置。
            if ( BaseApplication.prm >= 1008){
                if(BaseApplication.prm == 1012
                        || (BaseApplication.prm >=3000  && BaseApplication.prm<=3999)){
                    layoutReport.setVisibility(View.VISIBLE);
                    layoutTask.setVisibility(View.GONE);
                    layoutuggest.setVisibility(View.GONE);
                    layoutXunchaWork.setVisibility(View.GONE);
                    viewOnePlaceHolder.setVisibility(View.VISIBLE);
                }else {
                    layoutReport.setVisibility(View.GONE);
                    layoutTask.setVisibility(View.GONE);
                    layoutuggest.setVisibility(View.GONE);
                    layoutXunchaWork.setVisibility(View.VISIBLE);
                    viewOnePlaceHolder.setVisibility(View.VISIBLE);
                }
            } else if((BaseApplication.prm>=1001 && BaseApplication.prm<=1003) || BaseApplication.prm==1007){
                viewOnePlaceHolder.setVisibility(View.VISIBLE);
            }else{
                layoutXunchaWork.setVisibility(View.GONE);
                viewOnePlaceHolder.setVisibility(View.VISIBLE);
                viewTwoPlaceHolder.setVisibility(View.VISIBLE);
            }

            getVersion();
    }

    @OnClick({R.id.main_report, R.id.main_suggest, R.id.main_task, R.id.main_person,R.id.main_xunchawork})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_report:
                Intent reportIntent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(reportIntent);
                break;
            case R.id.main_suggest:
                Intent suggestIntent = new Intent(MainActivity.this, SuggestActivity.class);
                startActivity(suggestIntent);
                break;
            case R.id.main_task:
                Intent taskIntent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(taskIntent);
                break;
            case R.id.main_person:
                Intent personIntent = new Intent(MainActivity.this, PersonActivity.class);
                startActivity(personIntent);
                break;
            case R.id.main_xunchawork:
                Intent xunchaworkIntent = new Intent(MainActivity.this, MainXunchaActivity.class);
                startActivity(xunchaworkIntent);
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

    public void getReportState() {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<ReportStateEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HttpResult<ReportStateEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            setReportState(httpResult.getResult());
                        }
                    }
                });
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

    public void getTaskState() {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getTaskState()
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
                            setTaskState(httpResult.getResult());
                        }
                    }
                });
    }

    public void getVersion() {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getVS()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<VersionEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
                            ERROR("获取版本失败，请确认网络环境后重试。");
                        } else {
                            ERROR("获取版本失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<VersionEntity> userHttpResult) {
                        if (userHttpResult.isSuccess()) {
                            setVersion(userHttpResult.getResult());
                        } else {
                            ERROR(TextUtils.isEmpty(userHttpResult.getMessage()) ? "检测更新失败" : userHttpResult.getMessage());
                            VersionEntity entity = new VersionEntity();
                            entity.setVersion("0.0");
                            setVersion(entity);
                        }
                    }
                });
    }

    public void setVersion(VersionEntity version) {
        double vs = Double.parseDouble(version.getVersion());
        double appVersionCode = Double.parseDouble(getAppVersionName(this).substring(1));
        if (vs > appVersionCode) {
            mainMkLayout.setVisibility(View.GONE);
            loadVersion = version;
            setInstallPermission();
        }
    }


    /**
     * 获取当前app version name
     */
    public static String getAppVersionName(Context context) {
        String appVersionName = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            appVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        return appVersionName;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1110) {
            setInstallPermission();
        }
    }

    /**
     * 8.0以上系统设置安装未知来源权限
     */
    public void setInstallPermission() {
        boolean haveInstallPermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先判断是否有安装未知来源应用的权限
            haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                //弹框提示用户手动打开
                new QMUIDialog.MessageDialogBuilder(this)
                        .setTitle("安装权限")
                        .setMessage("需要打开允许来自此来源，请去设置中开启此权限")
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog2, int index) {
                                dialog2.dismiss();
                                //此方法需要API>=26才能使用
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    toInstallPermissionSettingIntent();
                                }
                            }
                        })
                        .setCanceledOnTouchOutside(false)
                        .setCancelable(false)
                        .show();
            } else {
                // 先验证是否有存储权限。
                requestPermission(Permission.Group.STORAGE);
            }
        } else {
            // 先验证是否有存储权限。
            requestPermission(Permission.Group.STORAGE);
        }
    }

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationaleUpdata())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        fullUpdateDialog = new FullUpdateDialog(MainActivity.this, MainActivity.this, loadVersion.getVersion(), getAppVersionName(MainActivity.this), loadVersion.getVersionName());
                        fullUpdateDialog.setCanceledOnTouchOutside(false);
                        fullUpdateDialog.setCancelable(false);
                        fullUpdateDialog.show();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, permissions)) {
                            showSettingDialog(MainActivity.this, permissions);
                        }
                    }
                })
                .start();
    }

    private void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_updata, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Set permissions.
     */
    private void setPermission() {
        AndPermission.with(this)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {
                        requestPermission(Permission.Group.STORAGE);
                    }
                })
                .start();
    }

    /**
     * 开启安装未知来源权限
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void toInstallPermissionSettingIntent() {
        Uri packageURI = Uri.parse("package:" + getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        startActivityForResult(intent, 1110);
    }

    public void ERROR(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    private void setReportState(ReportStateEntity result) {
        if (reportBadge == null) {
            reportBadge = new QBadgeView(this).bindTarget(report).setBadgeText("!");
        }
        if (result.isClose() || result.isAccept()) {
            reportBadge.setShowShadow(false);
            reportBadge.setBadgeText("!");
        } else {
            reportBadge.hide(false);
        }
    }

    private void setTaskState(TaskStateEntity result) {
        if (taskBadge == null) {
            taskBadge = new QBadgeView(this).bindTarget(task).setBadgeText("!");
        }
        if (result.isClose() || result.isAccept()) {
            taskBadge.setShowShadow(false);
            taskBadge.setBadgeText("!");
        } else {
            taskBadge.hide(false);
        }
    }
    private void setJubaoState(JubaoStateEntity result) {
        if (xunchaBadge == null) {
            xunchaBadge = new QBadgeView(this).bindTarget(xuncha).setBadgeText("!");
        }
        if (result.getPending() || result.getAssign()) {
            xunchaBadge.setShowShadow(false);
            xunchaBadge.setBadgeText("!");
        } else {
            xunchaBadge.hide(false);
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


    private  void setNoProcess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
