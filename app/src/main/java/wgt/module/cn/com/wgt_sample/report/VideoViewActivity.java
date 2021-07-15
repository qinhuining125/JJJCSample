package wgt.module.cn.com.wgt_sample.report;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;


import com.pili.pldroid.player.AVOptions;


import wgt.module.cn.com.wgt_sample.R;

public class VideoViewActivity extends AppCompatActivity {

    private static final String KEY_URL = "url";
    private VideoView mPlVideoView;


    public static void start(Context context,String url) {
        Intent starter = new Intent(context, VideoViewActivity.class);
        starter.putExtra(KEY_URL,url);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        mPlVideoView = findViewById(R.id.videoView);
        Intent intent = getIntent();
        String url = intent.getStringExtra(KEY_URL);
        mPlVideoView.setVideoPath(url);

        MediaController mc = new MediaController(this);
        mPlVideoView.setMediaController(mc);

        mPlVideoView.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPlVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlVideoView.stopPlayback();
    }

    private AVOptions getOptions() {
        boolean mIsLiveStreaming = false;
        AVOptions options = new AVOptions();
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_MEDIACODEC, 0);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);
        options.setInteger(AVOptions.KEY_LOG_LEVEL, 3);
        options.setInteger(AVOptions.KEY_AUDIO_DATA_CALLBACK, 1);
        return options;
    }
}
