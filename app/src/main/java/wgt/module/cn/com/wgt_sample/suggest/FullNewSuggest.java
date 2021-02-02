package wgt.module.cn.com.wgt_sample.suggest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wgt.module.cn.com.wgt_sample.R;

/**
 * Created by skc on 2020/6/17.
 */
public class FullNewSuggest extends Dialog {

    public View view;
    // buttnife绑定。
    Unbinder unbinder;

    @BindView(R.id.newsuggest_text)
    EditText newsuggestText;

    private setSuggest ss;

    public FullNewSuggest(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = LayoutInflater.from(getContext()).inflate(R.layout.newsuggest_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    @OnClick({R.id.newsuggest_back, R.id.newsuggest_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.newsuggest_back:
                dismiss();
                break;
            case R.id.newsuggest_ok:
                if (!TextUtils.isEmpty(newsuggestText.getText().toString())) {
                    ss.addSuggest(newsuggestText.getText().toString());
                } else {
                    Toast.makeText(getContext(), "廉政建议不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void setSuggest2Activity(setSuggest ss) {
        this.ss = ss;
    }

    public interface setSuggest {
        void addSuggest(String suggest);
    }
}
