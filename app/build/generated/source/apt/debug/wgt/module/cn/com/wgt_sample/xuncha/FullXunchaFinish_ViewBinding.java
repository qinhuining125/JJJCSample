// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.xuncha;

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

public class FullXunchaFinish_ViewBinding<T extends FullXunchaFinish> implements Unbinder {
  protected T target;

  private View view2131296717;

  private View view2131296718;

  @UiThread
  public FullXunchaFinish_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.finishtaskText = Utils.findRequiredViewAsType(source, R.id.finishtask_text, "field 'finishtaskText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.taskfinih_dialog_canel, "method 'onViewClicked'");
    view2131296717 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.taskfinih_dialog_ok, "method 'onViewClicked'");
    view2131296718 = view;
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

    target.finishtaskText = null;

    view2131296717.setOnClickListener(null);
    view2131296717 = null;
    view2131296718.setOnClickListener(null);
    view2131296718 = null;

    this.target = null;
  }
}
