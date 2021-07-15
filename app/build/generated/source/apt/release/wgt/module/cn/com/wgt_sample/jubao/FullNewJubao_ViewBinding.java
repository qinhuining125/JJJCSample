// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.jubao;

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
import wgt.module.cn.com.wgt_sample.utils.MyListView;

public class FullNewJubao_ViewBinding<T extends FullNewJubao> implements Unbinder {
  protected T target;

  private View view2131296679;

  private View view2131296463;

  private View view2131296464;

  private View view2131296403;

  @UiThread
  public FullNewJubao_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.newtaskZpperson = Utils.findRequiredViewAsType(source, R.id.newtask_zpperson, "field 'newtaskZpperson'", MyListView.class);
    target.newtaskText = Utils.findRequiredViewAsType(source, R.id.newtask_text, "field 'newtaskText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.task_back, "method 'onViewClicked'");
    view2131296679 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.newtask_choose, "method 'onViewClicked'");
    view2131296463 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.newtask_ok, "method 'onViewClicked'");
    view2131296464 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.image_upload, "method 'onViewClicked'");
    view2131296403 = view;
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

    target.newtaskZpperson = null;
    target.newtaskText = null;

    view2131296679.setOnClickListener(null);
    view2131296679 = null;
    view2131296463.setOnClickListener(null);
    view2131296463 = null;
    view2131296464.setOnClickListener(null);
    view2131296464 = null;
    view2131296403.setOnClickListener(null);
    view2131296403 = null;

    this.target = null;
  }
}
