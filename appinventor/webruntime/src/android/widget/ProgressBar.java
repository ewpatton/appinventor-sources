package android.widget;

import android.view.View;
import com.google.gwt.user.client.DOM;

public class ProgressBar extends View {
  private DivElement outer;
  private DivElement inner;
  
  public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
    // this(context, attrs, defStyleAttr, 0);
    super(DOM.createDiv());
    outer = getElement();
    inner = DOM.createDiv();
    outer.appendChild(inner);
  }
  
  
}