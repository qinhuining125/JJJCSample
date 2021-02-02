package wgt.module.cn.com.wgt_sample.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;

/**
 * Created by skc on 2020/6/20.
 */
public class AppUtils {

    /**
     * 获取User共享参数
     *
     * @param context
     * @return
     */
    public static SharedPreferences getUserSharedPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return preferences;
    }

    public static Retrofit provideRetrofit(String mBaseUrl) {
        Retrofit retrofit;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //访问http的OkHttpClient方法
        OkHttpClient OkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder();
                        Request build = builder.addHeader("token", BaseApplication.token).build();
                        return chain.proceed(build);
                    }
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(OkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create())//添加Gson转换器
                .baseUrl(mBaseUrl)
                .build();
        return retrofit;
    }

    public static  void getPermission(Activity activity){
        /**
         * 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
         */
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    activity.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }
}
