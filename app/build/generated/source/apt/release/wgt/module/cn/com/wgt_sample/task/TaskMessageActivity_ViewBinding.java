// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.task;

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

public class TaskMessageActivity_ViewBinding<T extends TaskMessageActivity> implements Unbinder {
  protected T target;

  private View view2131296729;

  private View view2131296724;

  private View view2131296721;

  private View view2131296720;

  @UiThread
  public TaskMessageActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.taskmessagePerson = Utils.findRequiredViewAsType(source, R.id.taskmessage_person, "field 'taskmessagePerson'", TextView.class);
    target.taskmessageText = Utils.findRequiredViewAsType(source, R.id.taskmessage_text, "field 'taskmessageText'", TextView.class);
    target.taskmessageTime = Utils.findRequiredViewAsType(source, R.id.taskmessage_time, "field 'taskmessageTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.taskmessage_zp, "field 'taskmessageZp' and method 'onViewClicked'");
    target.taskmessageZp = Utils.castView(view, R.id.taskmessage_zp, "field 'taskmessageZp'", TextView.class);
    view2131296729 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.taskmessage_sl, "field 'taskmessageSl' and method 'onViewClicked'");
    target.taskmessageSl = Utils.castView(view, R.id.taskmessage_sl, "field 'taskmessageSl'", TextView.class);
    view2131296724 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.taskmessage_bj, "field 'taskmessageBj' and method 'onViewClicked'");
    target.taskmessageBj = Utils.castView(view, R.id.taskmessage_bj, "field 'taskmessageBj'", TextView.class);
    view2131296721 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.taskmessageSrc = Utils.findRequiredViewAsType(source, R.id.taskmessage_src, "field 'taskmessageSrc'", ImageView.class);
    target.taskMessageZp1time = Utils.findRequiredViewAsType(source, R.id.task_message_zp1time, "field 'taskMessageZp1time'", TextView.class);
    target.taskMessageZp1line = Utils.findRequiredView(source, R.id.task_message_zp1line, "field 'taskMessageZp1line'");
    target.taskMessageZp1person = Utils.findRequiredViewAsType(source, R.id.task_message_zp1person, "field 'taskMessageZp1person'", TextView.class);
    target.taskMessageZp2time = Utils.findRequiredViewAsType(source, R.id.task_message_zp2time, "field 'taskMessageZp2time'", TextView.class);
    target.taskMessageZp2line = Utils.findRequiredView(source, R.id.task_message_zp2line, "field 'taskMessageZp2line'");
    target.taskMessageZp2person = Utils.findRequiredViewAsType(source, R.id.task_message_zp2person, "field 'taskMessageZp2person'", TextView.class);
    target.taskMessageZp2layout = Utils.findRequiredViewAsType(source, R.id.task_message_zp2layout, "field 'taskMessageZp2layout'", LinearLayout.class);
    target.taskMessageSltime = Utils.findRequiredViewAsType(source, R.id.task_message_sltime, "field 'taskMessageSltime'", TextView.class);
    target.taskMessageSlline = Utils.findRequiredView(source, R.id.task_message_slline, "field 'taskMessageSlline'");
    target.taskMessageSlperson = Utils.findRequiredViewAsType(source, R.id.task_message_slperson, "field 'taskMessageSlperson'", TextView.class);
    target.taskMessageSllayout = Utils.findRequiredViewAsType(source, R.id.task_message_sllayout, "field 'taskMessageSllayout'", LinearLayout.class);
    target.taskMessageBjtime = Utils.findRequiredViewAsType(source, R.id.task_message_bjtime, "field 'taskMessageBjtime'", TextView.class);
    target.taskMessageBjperson = Utils.findRequiredViewAsType(source, R.id.task_message_bjperson, "field 'taskMessageBjperson'", TextView.class);
    target.taskMessageBjtext = Utils.findRequiredViewAsType(source, R.id.task_message_bjtext, "field 'taskMessageBjtext'", TextView.class);
    target.taskMessageBjlayout = Utils.findRequiredViewAsType(source, R.id.task_message_bjlayout, "field 'taskMessageBjlayout'", LinearLayout.class);
    target.imageRecylerView = Utils.findRequiredViewAsType(source, R.id.taskmessage_image, "field 'imageRecylerView'", RecyclerView.class);
    target.layoutImages = Utils.findRequiredViewAsType(source, R.id.layout_images, "field 'layoutImages'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.taskmessage_back, "method 'onViewClicked'");
    view2131296720 = view;
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

    target.taskmessagePerson = null;
    target.taskmessageText = null;
    target.taskmessageTime = null;
    target.taskmessageZp = null;
    target.taskmessageSl = null;
    target.taskmessageBj = null;
    target.taskmessageSrc = null;
    target.taskMessageZp1time = null;
    target.taskMessageZp1line = null;
    target.taskMessageZp1person = null;
    target.taskMessageZp2time = null;
    target.taskMessageZp2line = null;
    target.taskMessageZp2person = null;
    target.taskMessageZp2layout = null;
    target.taskMessageSltime = null;
    target.taskMessageSlline = null;
    target.taskMessageSlperson = null;
    target.taskMessageSllayout = null;
    target.taskMessageBjtime = null;
    target.taskMessageBjperson = null;
    target.taskMessageBjtext = null;
    target.taskMessageBjlayout = null;
    target.imageRecylerView = null;
    target.layoutImages = null;

    view2131296729.setOnClickListener(null);
    view2131296729 = null;
    view2131296724.setOnClickListener(null);
    view2131296724 = null;
    view2131296721.setOnClickListener(null);
    view2131296721 = null;
    view2131296720.setOnClickListener(null);
    view2131296720 = null;

    this.target = null;
  }
}
