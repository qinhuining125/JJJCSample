// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.xuncha;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class FullZPXuncha_ViewBinding<T extends FullZPXuncha> implements Unbinder {
  protected T target;

  private View view2131296792;

  private View view2131296793;

  private View view2131296794;

  @UiThread
  public FullZPXuncha_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.zptaskZpperson = Utils.findRequiredViewAsType(source, R.id.zptask_zpperson, "field 'zptaskZpperson'", TextView.class);
    target.zptaskText = Utils.findRequiredViewAsType(source, R.id.zptask_text, "field 'zptaskText'", TextView.class);
    view = Utils.findRequiredView(source, R.id.zptask_back, "method 'onViewClicked'");
    view2131296792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.zptask_choose, "method 'onViewClicked'");
    view2131296793 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.zptask_ok, "method 'onViewClicked'");
    view2131296794 = view;
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

    target.zptaskZpperson = null;
    target.zptaskText = null;

    view2131296792.setOnClickListener(null);
    view2131296792 = null;
    view2131296793.setOnClickListener(null);
    view2131296793 = null;
    view2131296794.setOnClickListener(null);
    view2131296794 = null;

    this.target = null;
  }
}
