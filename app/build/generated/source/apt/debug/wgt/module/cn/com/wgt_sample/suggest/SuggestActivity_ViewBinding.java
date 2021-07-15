// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.suggest;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class SuggestActivity_ViewBinding<T extends SuggestActivity> implements Unbinder {
  protected T target;

  private View view2131296651;

  private View view2131296649;

  @UiThread
  public SuggestActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.suggestList = Utils.findRequiredViewAsType(source, R.id.suggest_list, "field 'suggestList'", SwipeRecyclerView.class);
    view = Utils.findRequiredView(source, R.id.suggest_newwork, "field 'suggestNewwork' and method 'onViewClicked'");
    target.suggestNewwork = Utils.castView(view, R.id.suggest_newwork, "field 'suggestNewwork'", TextView.class);
    view2131296651 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh_layout, "field 'refresh'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.suggest_back, "method 'onViewClicked'");
    view2131296649 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.suggestList = null;
    target.suggestNewwork = null;
    target.refresh = null;

    view2131296651.setOnClickListener(null);
    view2131296651 = null;
    view2131296649.setOnClickListener(null);
    view2131296649 = null;

    this.target = null;
  }
}
