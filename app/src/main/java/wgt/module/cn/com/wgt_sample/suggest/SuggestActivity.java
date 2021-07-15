package wgt.module.cn.com.wgt_sample.suggest;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.adapter.SuggestAdapter;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.SuggestEntity;
import wgt.module.cn.com.wgt_sample.entity.SuggestListRequestEntity;
import wgt.module.cn.com.wgt_sample.entity.SuggestSubmitRequestEntity;
import wgt.module.cn.com.wgt_sample.utils.MyLoadMoreView;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class SuggestActivity extends AppCompatActivity implements SuggestContract.View {

    @BindView(R.id.suggest_list)
    SwipeRecyclerView suggestList;
    @BindView(R.id.suggest_newwork)
    TextView suggestNewwork;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh;

    private SuggestAdapter adapter;
    private List<SuggestEntity.SuggestSonEntity> dataList;
    private int currentPage = 1;

    private FullNewSuggest fullNewSuggest;
    private QMUITipDialog dataDialog, addDialog;
    private SuggestContract.Presenter mPresenter;

    /**
     * wait刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            onRefrush();
        }
    };
    /**
     * wait加载更多。
     */
    private SwipeRecyclerView.LoadMoreListener loadMoreListener = new SwipeRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            refresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentPage++;
                    // 加载数据。
                    dataDialog.show();
                    mPresenter.getSuggestList(new Gson().toJson(new SuggestListRequestEntity(currentPage)));
                }
            }, 1000);
        }
    };

    private void onRefrush() {
        dataList.clear();
        adapter.notifyDataSetChanged();
        currentPage = 1;

        suggestList.loadMoreFinish(false, true);
        // 加载数据。
        dataDialog.show();
        mPresenter.getSuggestList(new Gson().toJson(new SuggestListRequestEntity(currentPage)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_suggest);
        ButterKnife.bind(this);

        onCreateNewDialog();
        initView();
        onClickListen();

        // 检查是否正在重新创建一个以前销毁的实例
        if (savedInstanceState != null) {
//            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.baseURL = "http://183.201.252.83:49012/";
            BaseApplication.prm = savedInstanceState.getInt("prm");
            BaseApplication.token = savedInstanceState.getString("token");
            BaseApplication.userid = savedInstanceState.getString("userid");
            BaseApplication.username = savedInstanceState.getString("username");
            currentPage = savedInstanceState.getInt("currentPage");
            dataList.add((SuggestEntity.SuggestSonEntity) new Gson().fromJson(savedInstanceState.getString("dataList"), new TypeToken<List<SuggestEntity.SuggestSonEntity>>() {
            }.getType()));
            adapter.notifyDataSetChanged();
        } else {
            initData();
        }
    }

    private void initData() {
        dataDialog.show();
        mPresenter.getSuggestList(new Gson().toJson(new SuggestListRequestEntity(currentPage)));
    }

    private void onClickListen() {
        suggestList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(SuggestActivity.this, SuggestMessageActivity.class);
                intent.putExtra("data", new Gson().toJson(dataList.get(position)));
                startActivity(intent);
            }
        });

        // 在点击事件监听钱setadapter。
        suggestList.setAdapter(adapter);
    }

    private void initView() {
        mPresenter = new SuggestPresenter(this, this);

        // 刷新监听。
        refresh.setOnRefreshListener(refreshListener);
        // 加载更多的监听。
        suggestList.setLoadMoreListener(loadMoreListener);
        suggestList.setLayoutManager(new LinearLayoutManager(this));
        MyLoadMoreView loadmoreView = new MyLoadMoreView(this);
        suggestList.addFooterView(loadmoreView);
        suggestList.setLoadMoreView(loadmoreView);

        dataList = new ArrayList<>();
        adapter = new SuggestAdapter(dataList, this);

        // 上报权限。
        if (BaseApplication.prm == 1001 || BaseApplication.prm == 1002 || BaseApplication.prm == 1003) {
            suggestNewwork.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.suggest_back, R.id.suggest_newwork})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.suggest_back:
                finish();
                break;
            case R.id.suggest_newwork:
                fullNewSuggest = new FullNewSuggest(this);
                fullNewSuggest.show();
                fullNewSuggest.setSuggest2Activity(new FullNewSuggest.setSuggest() {
                    @Override
                    public void addSuggest(String suggest) {
                        addDialog.show();
                        mPresenter.addSuggest(new Gson().toJson(new SuggestSubmitRequestEntity(suggest)));
                    }
                });
                break;
        }
    }

    @Override
    public void setSuggest(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
            fullNewSuggest.dismiss();
            onRefrush();
        }
    }

    @Override
    public void setSuggestList(SuggestEntity data) {
        dataList.addAll(data.getRows());
        refresh.setRefreshing(false);
        adapter.notifyDataSetChanged();

        if (dataList.size() == data.getTotal()) {
            suggestList.loadMoreFinish(false, false);
        } else {
            suggestList.loadMoreFinish(false, true);
        }
    }

    @Override
    public void ERRORLIST(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
        refresh.setRefreshing(false);
        suggestList.loadMoreError(0, e);
        dataDialogDissmis();
    }

    @Override
    public void addDialogDissmis() {
        addDialog.dismiss();
    }

    @Override
    public void setPresenter(SuggestContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void dataDialogDissmis() {
        dataDialog.dismiss();
    }

    @Override
    public void ERROR(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
        addDialogDissmis();
    }

    /**
     * 等待框。
     */
    private void onCreateNewDialog() {
        dataDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载...")
                .create();
        dataDialog.setCancelable(false);

        addDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在提交...")
                .create();
        addDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("prm", BaseApplication.prm);
        outState.putString("token", BaseApplication.token);
        outState.putString("userid", BaseApplication.userid);
        outState.putString("username", BaseApplication.username);
        outState.putInt("currentPage", currentPage);
        outState.putString("dataList", new Gson().toJson(dataList));
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
