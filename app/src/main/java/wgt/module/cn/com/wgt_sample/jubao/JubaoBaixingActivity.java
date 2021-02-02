package wgt.module.cn.com.wgt_sample.jubao;


import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.api.ApiInterface;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;


public class JubaoBaixingActivity extends AppCompatActivity {
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.jubao_baixing_back)
    ImageView btnBack;


    public JsInterface jsInterfacev;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_jubao_webview);
        ButterKnife.bind(this);
        // 获取websettings对象（一。设置编码格 式 二。支持JavaScript）
        WebSettings settings = webView.getSettings();
        // 设置编码格式
        settings.setDefaultTextEncodingName("utf-8");
        // 支持js
        settings.setJavaScriptEnabled(true);//必须得设置不然js代码不能实现
        //在js中调用本地java方法
        jsInterfacev=new JsInterface(this);
        webView.addJavascriptInterface(jsInterfacev, "AndroidWebView");
        //添加客户端支持
        webView.setWebViewClient(new MyWebViewClient(jsInterfacev));
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(BaseApplication.baseWebViewUrl+"?id="+getIntent().getStringExtra("id"));
    }

        @OnClick({R.id.jubao_baixing_back})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.jubao_baixing_back:
                    finish();
                    break;
            }
        }


     class JsInterface {
        private Context mContext;
        public JsInterface(Context context) {
            this.mContext = context;
        }
        @JavascriptInterface
        public String showInfoFromJS(){

            return BaseApplication.token;
        }
        public void sendInfoToJs(String token){

            webView.loadUrl("javascript:showInfoFromJava('"+token+"')");
        }
    }

}
// webview设置的代理类
 class MyWebViewClient extends WebViewClient {
    JubaoBaixingActivity.JsInterface jsInterface;
    public MyWebViewClient(JubaoBaixingActivity.JsInterface jsInterface) {
        this.jsInterface = jsInterface;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        jsInterface.sendInfoToJs(BaseApplication.token);

    }
}
