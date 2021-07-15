// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.suggest;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class FullNewSuggest_ViewBinding<T extends FullNewSuggest> implements Unbinder {
  protected T target;

  private View view2131296460;

  private View view2131296461;

  @UiThread
  public FullNewSuggest_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.newsuggestText = Utils.findRequiredViewAsType(source, R.id.newsuggest_text, "field 'newsuggestText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.newsuggest_back, "method 'onViewClicked'");
    view2131296460 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.newsuggest_ok, "method 'onViewClicked'");
    view2131296461 = view;
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

    target.newsuggestText = null;

    view2131296460.setOnClickListener(null);
    view2131296460 = null;
    view2131296461.setOnClickListener(null);
    view2131296461 = null;

    this.target = null;
  }
}
