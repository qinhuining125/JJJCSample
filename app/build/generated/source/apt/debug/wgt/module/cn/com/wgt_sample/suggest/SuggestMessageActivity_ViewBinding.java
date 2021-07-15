// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.suggest;

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

public class SuggestMessageActivity_ViewBinding<T extends SuggestMessageActivity> implements Unbinder {
  protected T target;

  private View view2131296652;

  @UiThread
  public SuggestMessageActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.suggestmessagePerson = Utils.findRequiredViewAsType(source, R.id.suggestmessage_person, "field 'suggestmessagePerson'", TextView.class);
    target.suggestmessageTime = Utils.findRequiredViewAsType(source, R.id.suggestmessage_time, "field 'suggestmessageTime'", TextView.class);
    target.suggestmessageContent = Utils.findRequiredViewAsType(source, R.id.suggestmessage_content, "field 'suggestmessageContent'", TextView.class);
    view = Utils.findRequiredView(source, R.id.suggestmessage_back, "method 'onViewClicked'");
    view2131296652 = view;
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

    target.suggestmessagePerson = null;
    target.suggestmessageTime = null;
    target.suggestmessageContent = null;

    view2131296652.setOnClickListener(null);
    view2131296652 = null;

    this.target = null;
  }
}
