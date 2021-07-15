// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.report;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class NewReportActivity_ViewBinding<T extends NewReportActivity> implements Unbinder {
  protected T target;

  private View view2131296403;

  private View view2131296293;

  private View view2131296772;

  private View view2131296679;

  private View view2131296454;

  private View view2131296456;

  private View view2131296402;

  private View view2131296292;

  private View view2131296771;

  @UiThread
  public NewReportActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.newreportType = Utils.findRequiredViewAsType(source, R.id.newreport_type, "field 'newreportType'", TextView.class);
    target.newreportText = Utils.findRequiredViewAsType(source, R.id.newreport_text, "field 'newreportText'", EditText.class);
    target.newreportToVillageMgr = Utils.findRequiredViewAsType(source, R.id.newreport_tovillagemgr, "field 'newreportToVillageMgr'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.image_upload, "field 'imageUpload' and method 'onViewClicked'");
    target.imageUpload = Utils.castView(view, R.id.image_upload, "field 'imageUpload'", TextView.class);
    view2131296403 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.imageRecylerView = Utils.findRequiredViewAsType(source, R.id.recyclerview_image_upload1, "field 'imageRecylerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.audio_upload, "field 'audioUpload' and method 'onViewClicked'");
    target.audioUpload = Utils.castView(view, R.id.audio_upload, "field 'audioUpload'", TextView.class);
    view2131296293 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.audioRecylerView = Utils.findRequiredViewAsType(source, R.id.recyclerview_audio_upload1, "field 'audioRecylerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.video_upload, "field 'videoUpload' and method 'onViewClicked'");
    target.videoUpload = Utils.castView(view, R.id.video_upload, "field 'videoUpload'", TextView.class);
    view2131296772 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.videoRecylerView = Utils.findRequiredViewAsType(source, R.id.recyclerview_video_upload1, "field 'videoRecylerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.task_back, "method 'onViewClicked'");
    view2131296679 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.newreport_choose, "method 'onViewClicked'");
    view2131296454 = view;
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
    view = Utils.findRequiredView(source, R.id.image_choose, "method 'onViewClicked'");
    view2131296402 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.audio_choose, "method 'onViewClicked'");
    view2131296292 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.video_choose, "method 'onViewClicked'");
    view2131296771 = view;
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

    target.newreportType = null;
    target.newreportText = null;
    target.newreportToVillageMgr = null;
    target.imageUpload = null;
    target.imageRecylerView = null;
    target.audioUpload = null;
    target.audioRecylerView = null;
    target.videoUpload = null;
    target.videoRecylerView = null;

    view2131296403.setOnClickListener(null);
    view2131296403 = null;
    view2131296293.setOnClickListener(null);
    view2131296293 = null;
    view2131296772.setOnClickListener(null);
    view2131296772 = null;
    view2131296679.setOnClickListener(null);
    view2131296679 = null;
    view2131296454.setOnClickListener(null);
    view2131296454 = null;
    view2131296456.setOnClickListener(null);
    view2131296456 = null;
    view2131296402.setOnClickListener(null);
    view2131296402 = null;
    view2131296292.setOnClickListener(null);
    view2131296292 = null;
    view2131296771.setOnClickListener(null);
    view2131296771 = null;

    this.target = null;
  }
}
