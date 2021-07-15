// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.report;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class ReportMessageActivity_ViewBinding<T extends ReportMessageActivity> implements Unbinder {
  protected T target;

  private View view2131296573;

  private View view2131296591;

  private View view2131296592;

  private View view2131296571;

  private View view2131296566;

  @UiThread
  public ReportMessageActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.reportmessagePerson = Utils.findRequiredViewAsType(source, R.id.reportmessage_person, "field 'reportmessagePerson'", TextView.class);
    target.reportmessageType = Utils.findRequiredViewAsType(source, R.id.reportmessage_type, "field 'reportmessageType'", TextView.class);
    target.reportmessageText = Utils.findRequiredViewAsType(source, R.id.reportmessage_text, "field 'reportmessageText'", TextView.class);
    target.reportmessageTime = Utils.findRequiredViewAsType(source, R.id.reportmessage_time, "field 'reportmessageTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.reportmessage_js, "field 'reportmessageJs' and method 'onViewClicked'");
    target.reportmessageJs = Utils.castView(view, R.id.reportmessage_js, "field 'reportmessageJs'", TextView.class);
    view2131296573 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.reportmessage_zhixiao, "field 'reportmessageZX' and method 'onViewClicked'");
    target.reportmessageZX = Utils.castView(view, R.id.reportmessage_zhixiao, "field 'reportmessageZX'", TextView.class);
    view2131296591 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.reportmessage_zhuanban, "field 'reportmessageZB' and method 'onViewClicked'");
    target.reportmessageZB = Utils.castView(view, R.id.reportmessage_zhuanban, "field 'reportmessageZB'", TextView.class);
    view2131296592 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.reportmessage_finish, "field 'reportmessageFinish' and method 'onViewClicked'");
    target.reportmessageFinish = Utils.castView(view, R.id.reportmessage_finish, "field 'reportmessageFinish'", TextView.class);
    view2131296571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.reportmessageSbtime = Utils.findRequiredViewAsType(source, R.id.reportmessage_sbtime, "field 'reportmessageSbtime'", TextView.class);
    target.reportmessageSbline = Utils.findRequiredView(source, R.id.reportmessage_sbline, "field 'reportmessageSbline'");
    target.reportmessageSbperson = Utils.findRequiredViewAsType(source, R.id.reportmessage_sbperson, "field 'reportmessageSbperson'", TextView.class);
    target.reportmessageJstime = Utils.findRequiredViewAsType(source, R.id.reportmessage_jstime, "field 'reportmessageJstime'", TextView.class);
    target.reportmessageJsline = Utils.findRequiredView(source, R.id.reportmessage_jsline, "field 'reportmessageJsline'");
    target.reportmessageJsperson = Utils.findRequiredViewAsType(source, R.id.reportmessage_jsperson, "field 'reportmessageJsperson'", TextView.class);
    target.reportmessageJslayout = Utils.findRequiredViewAsType(source, R.id.reportmessage_jslayout, "field 'reportmessageJslayout'", LinearLayout.class);
    target.reportmessageBjtime = Utils.findRequiredViewAsType(source, R.id.reportmessage_bjtime, "field 'reportmessageBjtime'", TextView.class);
    target.reportmessageBjperson = Utils.findRequiredViewAsType(source, R.id.reportmessage_bjperson, "field 'reportmessageBjperson'", TextView.class);
    target.reportmessageBjtext = Utils.findRequiredViewAsType(source, R.id.reportmessage_bjtext, "field 'reportmessageBjtext'", TextView.class);
    target.reportmessageBjlayout = Utils.findRequiredViewAsType(source, R.id.reportmessage_bjlayout, "field 'reportmessageBjlayout'", LinearLayout.class);
    target.reportmessageZxtime = Utils.findRequiredViewAsType(source, R.id.reportmessage_zxtime, "field 'reportmessageZxtime'", TextView.class);
    target.reportmessageZxperson = Utils.findRequiredViewAsType(source, R.id.reportmessage_zxperson, "field 'reportmessageZxperson'", TextView.class);
    target.reportmessageZxlayout = Utils.findRequiredViewAsType(source, R.id.reportmessage_zxlayout, "field 'reportmessageZxlayout'", LinearLayout.class);
    target.reportmessageZbtime = Utils.findRequiredViewAsType(source, R.id.reportmessage_zbtime, "field 'reportmessageZbtime'", TextView.class);
    target.reportmessageZbperson = Utils.findRequiredViewAsType(source, R.id.reportmessage_zbperson, "field 'reportmessageZbperson'", TextView.class);
    target.reportmessageZblayout = Utils.findRequiredViewAsType(source, R.id.reportmessage_zblayout, "field 'reportmessageZblayout'", LinearLayout.class);
    target.reportmessageSrc = Utils.findRequiredViewAsType(source, R.id.reportmessage_src, "field 'reportmessageSrc'", ImageView.class);
    target.imageRecylerView = Utils.findRequiredViewAsType(source, R.id.reportmessage_image, "field 'imageRecylerView'", RecyclerView.class);
    target.layoutImages = Utils.findRequiredViewAsType(source, R.id.layout_images1, "field 'layoutImages'", LinearLayout.class);
    target.audioRecylerView = Utils.findRequiredViewAsType(source, R.id.reportmessage_audio, "field 'audioRecylerView'", RecyclerView.class);
    target.layoutAudios = Utils.findRequiredViewAsType(source, R.id.layout_audio, "field 'layoutAudios'", LinearLayout.class);
    target.videoRecylerView = Utils.findRequiredViewAsType(source, R.id.reportmessage_video, "field 'videoRecylerView'", RecyclerView.class);
    target.layoutVideos = Utils.findRequiredViewAsType(source, R.id.layout_video, "field 'layoutVideos'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.reportmessage_back, "method 'onViewClicked'");
    view2131296566 = view;
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

    target.reportmessagePerson = null;
    target.reportmessageType = null;
    target.reportmessageText = null;
    target.reportmessageTime = null;
    target.reportmessageJs = null;
    target.reportmessageZX = null;
    target.reportmessageZB = null;
    target.reportmessageFinish = null;
    target.reportmessageSbtime = null;
    target.reportmessageSbline = null;
    target.reportmessageSbperson = null;
    target.reportmessageJstime = null;
    target.reportmessageJsline = null;
    target.reportmessageJsperson = null;
    target.reportmessageJslayout = null;
    target.reportmessageBjtime = null;
    target.reportmessageBjperson = null;
    target.reportmessageBjtext = null;
    target.reportmessageBjlayout = null;
    target.reportmessageZxtime = null;
    target.reportmessageZxperson = null;
    target.reportmessageZxlayout = null;
    target.reportmessageZbtime = null;
    target.reportmessageZbperson = null;
    target.reportmessageZblayout = null;
    target.reportmessageSrc = null;
    target.imageRecylerView = null;
    target.layoutImages = null;
    target.audioRecylerView = null;
    target.layoutAudios = null;
    target.videoRecylerView = null;
    target.layoutVideos = null;

    view2131296573.setOnClickListener(null);
    view2131296573 = null;
    view2131296591.setOnClickListener(null);
    view2131296591 = null;
    view2131296592.setOnClickListener(null);
    view2131296592 = null;
    view2131296571.setOnClickListener(null);
    view2131296571 = null;
    view2131296566.setOnClickListener(null);
    view2131296566 = null;

    this.target = null;
  }
}
