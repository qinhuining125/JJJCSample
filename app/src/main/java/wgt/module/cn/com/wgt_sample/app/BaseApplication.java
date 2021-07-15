package wgt.module.cn.com.wgt_sample.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by skc on 2020/6/20.
 * p
 */
public class BaseApplication extends Application {

    public static String token = "";
    public static int prm;
    public static String username = "";
    public static String userid = "";

//    public static String baseURL = "http://192.168.32.201:9012/";
//    public static String baseURL = "http://192.168.32.240:81/";

//    public static String baseURL = "http://192.168.32.4/";
//    public static String baseURL = "http://183.201.252.83:49012/";
    public static String baseURL = "http://183.201.252.83:49012/";
//    public static String baseWebViewUrl = "http://192.168.32.201:81/reporting/report.html";
    public static String baseWebViewUrl = "http://183.201.252.83:49012/reporting/report.html";

    private static BaseApplication INSTANCE;
    //用来存储所有创建的activity
    private List<Activity> activityList = new ArrayList<>();

    public static synchronized BaseApplication getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BaseApplication();
        }
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }


    /**
     * 为了兼容5.0一下的版本，方法超过65536。
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //添加新创建的activity
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    //关闭所有activity，退出程序
    public void exit(Context context) {
        for (Activity activity : activityList) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    //-1.8版本更新说明：
    //1.将巡察工作单独作为了一个模块；
    //2.给乡镇管理员添加了使用巡察宣传的权限；
    //3.修复指派任务图片为空是崩溃的bug，服务器还没调试好；
    //4.举报详情页标题添加“匿名举报”；
    //5.首页判断是否有待办优化，避免浪费资源；
    //6.问题上报添加已转办和已知晓两种状态。

}
