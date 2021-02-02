package wgt.module.cn.com.wgt_sample.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUIProgressBar;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.login.LoginActivity;
import wgt.module.cn.com.wgt_sample.main.MainActivity;

public class FullUpdateDialog extends Dialog {

    Unbinder unbinder;
    @BindView(R.id.upload_title)
    TextView uploadTitle;
    @BindView(R.id.upload_circleProgressBar)
    QMUIProgressBar uploadCircleProgressBar;
    @BindView(R.id.upload_button)
    Button uploadButton;

    private View view;
    private Context context;
    private MainActivity main;
    private String newVersion, oldVersion, fileName, appURL;
    private String saveDir = "APK";
    private int type = 0;

    public FullUpdateDialog(Context context, MainActivity main, String newVersion, String oldVersion, String fileName) {
        super(context);
        this.context = context;
        this.main = main;
        this.newVersion = newVersion;
        this.oldVersion = oldVersion;
        this.fileName = fileName;
        this.appURL = "download/JJJC.apk";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = LayoutInflater.from(getContext()).inflate(R.layout.upload_alert_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        initView();
    }

    private void initView() {
        uploadCircleProgressBar.setMaxValue(100);
        uploadTitle.setText("检测到新版本" + newVersion + "  当前版本" + oldVersion);
        uploadCircleProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "%";
            }
        });
    }

    @OnClick(R.id.upload_button)
    public void onViewClicked() {
        switch (type) {
            case 0:
                uploadButton.setText("正在下载 ...");
                uploadCircleProgressBar.setVisibility(View.VISIBLE);
                type = 4;
                downLoad();
                break;
            case 1:
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/纪检监察/" + saveDir + "/" + fileName);
                if (file.exists()) {
                    FileProviderUtils.installApk(getContext(), file);
                }
                break;
            case 2:
                uploadButton.setText("正在下载 ...");
                downLoad();
                break;
        }
    }

    private void downLoad() {
        uploadCircleProgressBar.setProgress(0);
        DownLoadUtil.get().download(getContext(), BaseApplication.baseURL + appURL, saveDir, fileName, new DownLoadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess() {
                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uploadButton.setText("安装");
                        type = 1;
                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/纪检监察/" + saveDir + "/" + fileName);
                        FileProviderUtils.installApk(getContext(), file);
                    }
                });
            }

            @Override
            public void onDownloading(final int progress) {
                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uploadCircleProgressBar.setProgress(progress);
                    }
                });
            }

            @Override
            public void onDownloadFailed() {
                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uploadButton.setText("下载失败，请重试");
                        type = 2;
                    }
                });

            }
        });
    }
}
