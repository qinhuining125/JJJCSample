// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.utils;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmuiteam.qmui.widget.QMUIProgressBar;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class FullUpdateDialog_ViewBinding<T extends FullUpdateDialog> implements Unbinder {
  protected T target;

  private View view2131296763;

  @UiThread
  public FullUpdateDialog_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.uploadTitle = Utils.findRequiredViewAsType(source, R.id.upload_title, "field 'uploadTitle'", TextView.class);
    target.uploadCircleProgressBar = Utils.findRequiredViewAsType(source, R.id.upload_circleProgressBar, "field 'uploadCircleProgressBar'", QMUIProgressBar.class);
    view = Utils.findRequiredView(source, R.id.upload_button, "field 'uploadButton' and method 'onViewClicked'");
    target.uploadButton = Utils.castView(view, R.id.upload_button, "field 'uploadButton'", Button.class);
    view2131296763 = view;
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

    target.uploadTitle = null;
    target.uploadCircleProgressBar = null;
    target.uploadButton = null;

    view2131296763.setOnClickListener(null);
    view2131296763 = null;

    this.target = null;
  }
}
