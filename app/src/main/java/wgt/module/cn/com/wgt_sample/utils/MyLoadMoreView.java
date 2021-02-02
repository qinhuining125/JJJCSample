package wgt.module.cn.com.wgt_sample.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultLoadMoreView;

import wgt.module.cn.com.wgt_sample.R;


/**
 * Created by skc on 2020/6/24.
 */
public class MyLoadMoreView extends DefaultLoadMoreView {

    private ProgressBar mProgressBar;
    private TextView mTvMessage;

    public MyLoadMoreView(Context context) {
        super(context);
        mProgressBar = findViewById(R.id.progress_bar);
        mTvMessage = findViewById(R.id.tv_load_more_message);
        mTvMessage.setTextSize(13);
        mTvMessage.setTextColor(Color.GRAY);
        mTvMessage.setCompoundDrawablePadding(15);
        setBackgroundColor(Color.parseColor("#00000000"));
        getLayoutParams().height = 120;
    }

    public MyLoadMoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mProgressBar = findViewById(R.id.progress_bar);
        mTvMessage = findViewById(R.id.tv_load_more_message);
    }

    public void setVisible(boolean isHide) {
        if (isHide) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
        }
    }

    @Override
    public void onLoadFinish(boolean dataEmpty, boolean hasMore) {
        if (!hasMore) {
            setVisibility(VISIBLE);

            if (dataEmpty) {
                mTvMessage.setCompoundDrawables(null, null, null, null);
                mProgressBar.setVisibility(GONE);
                mTvMessage.setVisibility(VISIBLE);
                mTvMessage.setText(R.string.support_recycler_data_empty);
            } else {
                mProgressBar.setVisibility(GONE);
                mTvMessage.setVisibility(VISIBLE);
                mTvMessage.setText("无更多数据");

            }
        } else {
            mTvMessage.setCompoundDrawables(null, null, null, null);
            setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onWaitToLoadMore(SwipeRecyclerView.LoadMoreListener loadMoreListener) {
        setVisibility(INVISIBLE);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }
}
