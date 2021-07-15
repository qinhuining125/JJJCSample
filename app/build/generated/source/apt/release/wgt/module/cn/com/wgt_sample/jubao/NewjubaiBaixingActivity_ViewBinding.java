// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.jubao;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class NewjubaiBaixingActivity_ViewBinding<T extends NewjubaiBaixingActivity> implements Unbinder {
  protected T target;

  private View view2131296679;

  private View view2131296402;

  private View view2131296456;

  private View view2131296403;

  @UiThread
  public NewjubaiBaixingActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.jubaoText = Utils.findRequiredViewAsType(source, R.id.jubao_text, "field 'jubaoText'", EditText.class);
    target.jubaoObject = Utils.findRequiredViewAsType(source, R.id.jubao_object, "field 'jubaoObject'", EditText.class);
    target.editPhone = Utils.findRequiredViewAsType(source, R.id.edit_phone, "field 'editPhone'", EditText.class);
    target.xunchaName = Utils.findRequiredViewAsType(source, R.id.xuncha_name, "field 'xunchaName'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerview_image_upload, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.task_back, "method 'onViewClicked'");
    view2131296679 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.image_choose, "method 'onViewClicked'");
    view2131296402 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.newreport_ok, "method 'onViewClicked'");
    view2131296456 = view;
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

    target.jubaoText = null;
    target.jubaoObject = null;
    target.editPhone = null;
    target.xunchaName = null;
    target.recyclerView = null;

    view2131296679.setOnClickListener(null);
    view2131296679 = null;
    view2131296402.setOnClickListener(null);
    view2131296402 = null;
    view2131296456.setOnClickListener(null);
    view2131296456 = null;
    view2131296403.setOnClickListener(null);
    view2131296403 = null;

    this.target = null;
  }
}
