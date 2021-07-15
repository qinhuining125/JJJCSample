// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.changepassword;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class ChangePassWordActivity_ViewBinding<T extends ChangePassWordActivity> implements Unbinder {
  protected T target;

  private View view2131296323;

  private View view2131296324;

  @UiThread
  public ChangePassWordActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.changepasswordNewpassword = Utils.findRequiredViewAsType(source, R.id.changepassword_newpassword, "field 'changepasswordNewpassword'", EditText.class);
    target.changepasswordNewpasswordture = Utils.findRequiredViewAsType(source, R.id.changepassword_newpasswordture, "field 'changepasswordNewpasswordture'", EditText.class);
    target.changepasswordLoglayout = Utils.findRequiredViewAsType(source, R.id.changepassword_loglayout, "field 'changepasswordLoglayout'", LinearLayout.class);
    target.changepasswordOldpassword = Utils.findRequiredViewAsType(source, R.id.changepassword_oldpassword, "field 'changepasswordOldpassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.changepassword_back, "field 'changepasswordBack' and method 'onViewClicked'");
    target.changepasswordBack = Utils.castView(view, R.id.changepassword_back, "field 'changepasswordBack'", ImageView.class);
    view2131296323 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.changepassword_button, "method 'onViewClicked'");
    view2131296324 = view;
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

    target.changepasswordNewpassword = null;
    target.changepasswordNewpasswordture = null;
    target.changepasswordLoglayout = null;
    target.changepasswordOldpassword = null;
    target.changepasswordBack = null;

    view2131296323.setOnClickListener(null);
    view2131296323 = null;
    view2131296324.setOnClickListener(null);
    view2131296324 = null;

    this.target = null;
  }
}
