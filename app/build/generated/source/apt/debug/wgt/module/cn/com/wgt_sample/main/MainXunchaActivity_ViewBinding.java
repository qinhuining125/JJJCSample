// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class MainXunchaActivity_ViewBinding<T extends MainXunchaActivity> implements Unbinder {
  protected T target;

  private View view2131296441;

  private View view2131296433;

  private View view2131296434;

  private View view2131296787;

  @UiThread
  public MainXunchaActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.main_xuncha, "field 'layoutXuncha' and method 'onViewClicked'");
    target.layoutXuncha = Utils.castView(view, R.id.main_xuncha, "field 'layoutXuncha'", LinearLayout.class);
    view2131296441 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.main_jubao, "field 'layoutJubao' and method 'onViewClicked'");
    target.layoutJubao = Utils.castView(view, R.id.main_jubao, "field 'layoutJubao'", LinearLayout.class);
    view2131296433 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.viewOnePlaceHolder = Utils.findRequiredView(source, R.id.view_one, "field 'viewOnePlaceHolder'");
    target.viewTwoPlaceHolder = Utils.findRequiredView(source, R.id.view_two, "field 'viewTwoPlaceHolder'");
    view = Utils.findRequiredView(source, R.id.main_jubao_baixing, "field 'layoutJubaoBaixing' and method 'onViewClicked'");
    target.layoutJubaoBaixing = Utils.castView(view, R.id.main_jubao_baixing, "field 'layoutJubaoBaixing'", LinearLayout.class);
    view2131296434 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.xuncha_back, "field 'xunchaBack' and method 'onViewClicked'");
    target.xunchaBack = Utils.castView(view, R.id.xuncha_back, "field 'xunchaBack'", ImageView.class);
    view2131296787 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.xuncha = Utils.findRequiredViewAsType(source, R.id.xuncha, "field 'xuncha'", ImageView.class);
    target.jubao = Utils.findRequiredViewAsType(source, R.id.jubao, "field 'jubao'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.layoutXuncha = null;
    target.layoutJubao = null;
    target.viewOnePlaceHolder = null;
    target.viewTwoPlaceHolder = null;
    target.layoutJubaoBaixing = null;
    target.xunchaBack = null;
    target.xuncha = null;
    target.jubao = null;

    view2131296441.setOnClickListener(null);
    view2131296441 = null;
    view2131296433.setOnClickListener(null);
    view2131296433 = null;
    view2131296434.setOnClickListener(null);
    view2131296434 = null;
    view2131296787.setOnClickListener(null);
    view2131296787 = null;

    this.target = null;
  }
}
