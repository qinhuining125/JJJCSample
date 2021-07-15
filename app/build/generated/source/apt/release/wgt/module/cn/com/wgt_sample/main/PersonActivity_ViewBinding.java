// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.main;

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

public class PersonActivity_ViewBinding<T extends PersonActivity> implements Unbinder {
  protected T target;

  private View view2131296486;

  private View view2131296322;

  private View view2131296431;

  @UiThread
  public PersonActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.username = Utils.findRequiredViewAsType(source, R.id.username, "field 'username'", TextView.class);
    view = Utils.findRequiredView(source, R.id.person_back, "method 'onViewClicked'");
    view2131296486 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.changepassword, "method 'onViewClicked'");
    view2131296322 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.loginout, "method 'onViewClicked'");
    view2131296431 = view;
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

    target.username = null;

    view2131296486.setOnClickListener(null);
    view2131296486 = null;
    view2131296322.setOnClickListener(null);
    view2131296322 = null;
    view2131296431.setOnClickListener(null);
    view2131296431 = null;

    this.target = null;
  }
}
