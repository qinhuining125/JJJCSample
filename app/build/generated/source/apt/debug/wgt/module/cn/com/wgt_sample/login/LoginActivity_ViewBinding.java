// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  private View view2131296430;

  @UiThread
  public LoginActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.loginUsername = Utils.findRequiredViewAsType(source, R.id.username, "field 'loginUsername'", EditText.class);
    target.loginPassword = Utils.findRequiredViewAsType(source, R.id.password, "field 'loginPassword'", EditText.class);
    target.loginSign = Utils.findRequiredViewAsType(source, R.id.loginsign, "field 'loginSign'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.login, "method 'onViewClicked'");
    view2131296430 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loginUsername = null;
    target.loginPassword = null;
    target.loginSign = null;

    view2131296430.setOnClickListener(null);
    view2131296430 = null;

    this.target = null;
  }
}
