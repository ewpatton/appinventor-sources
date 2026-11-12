package android.app;

import android.Res;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;

import java.util.HashMap;
import java.util.Map;

public class AlertDialog extends Dialog implements DialogInterface {
  private static final int[] BUTTON_ORDER = {BUTTON_NEGATIVE, BUTTON_NEUTRAL, BUTTON_POSITIVE};

  private Context ctx;
  private LinearLayout titleLabelContainer;
  private LinearLayout contentPanel;
  private TextView titleLabel;
  private TextView messageLabel;
  private Element customView;
  private final DialogInterface.OnClickListener itemsListener;
  private final LinearLayout buttonsLayout;
  private final Map<Integer, Button> buttons = new HashMap<>();

  public static class Builder {
    Context ctx;
    View view;

    boolean cancelable = true;
    String title;
    String message;
    CharSequence items[];
    String positiveLabel, negativeLabel, neutralLabel;
    DialogInterface.OnClickListener itemsListener, positiveListener, negativeListener, neutralListener;

    public Builder(Context ctx) {
      this.ctx = ctx;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public void setTitle(int title) {
      setTitle(ctx.getString(title));
    }

    public void setMessage(int message) {
      setMessage(ctx.getString(message));
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public void setCancelable(boolean cancelable) {
      this.cancelable = cancelable;
    }

    public void setPositiveButton(int label, DialogInterface.OnClickListener listener) {
      setPositiveButton(ctx.getString(label), listener);
    }

    public void setPositiveButton(String label, DialogInterface.OnClickListener listener) {
      this.positiveLabel = label;
      this.positiveListener = listener;
    }

    public void setNegativeButton(int label, DialogInterface.OnClickListener listener) {
      setNegativeButton(ctx.getString(label), listener);
    }

    public void setNegativeButton(String label, DialogInterface.OnClickListener listener) {
      this.negativeLabel = label;
      this.negativeListener = listener;
    }

    public void setNeutralButton(int label, DialogInterface.OnClickListener listener) {
      setNeutralButton(ctx.getString(label), listener);
    }

    public void setNeutralButton(String label, DialogInterface.OnClickListener listener) {
      this.neutralLabel = label;
      this.neutralListener = listener;
    }

    public void setItems(int items, DialogInterface.OnClickListener listener) {
      this.items = ctx.getResources().getStringArray(items);
      this.itemsListener = listener;
    }

    public void setItems(CharSequence items[], DialogInterface.OnClickListener listener) {
      this.items = items;
      this.itemsListener = listener;
    }

    public void setView(View view) {
      this.view = view;
    }

    public AlertDialog create() {
      return new AlertDialog(this);
    }
  }

  public AlertDialog(Builder builder) {
    super(builder.cancelable);

    this.ctx = builder.ctx;
    this.itemsListener = builder.itemsListener;

    LinearLayout dialogLayout = new LinearLayout(ctx);
    dialogLayout.setOrientation(LinearLayout.VERTICAL);
    dialogLayout.getElement().getStyle().setProperty("alignItems", "stretch");

    titleLabelContainer = new LinearLayout(ctx);
    dialogLayout.addView(titleLabelContainer);

    contentPanel = new LinearLayout(ctx);
    contentPanel.setOrientation(LinearLayout.VERTICAL);
    contentPanel.getElement().getStyle().setProperty("alignItems", "stretch");
    contentPanel.getElement().addClassName(Res.R.style().dialogContent());
    dialogLayout.addView(contentPanel);

    setTitle(builder.title);
    setMessage(builder.message);

    if (builder.items != null) {
      int count = 0;
      for (CharSequence item : builder.items) {
        final int countFinal = count;
        count++;

        Button button = new Button(ctx);
        button.setText(item.toString());
        button.getElement().addClassName(Res.R.style().dialogItem());
        button.getElement().addClassName(Res.R.style().controlHighlight());
        button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            AlertDialog.this.dismiss();
            if (itemsListener != null) {
              itemsListener.onClick(AlertDialog.this, countFinal);
            }
          }
        });
        contentPanel.addView(button);
      }
    }

    setView(builder.view);

    buttonsLayout = new LinearLayout(ctx);
    buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);
    buttonsLayout.setHorizontalGravity(Gravity.RIGHT);
    buttonsLayout.getElement().addClassName(Res.R.style().dialogButtons());
    buttonsLayout.setVisibility(View.GONE);
    dialogLayout.addView(buttonsLayout);
    setButton(BUTTON_NEGATIVE, builder.negativeLabel, builder.negativeListener);
    setButton(BUTTON_NEUTRAL, builder.neutralLabel, builder.neutralListener);
    setButton(BUTTON_POSITIVE, builder.positiveLabel, builder.positiveListener);

    popupPanel.getElement().appendChild(dialogLayout.getElement());
  }

  public void setTitle(int title) {
    setTitle(Context.resources.getString(title));
  }

  public void setTitle(String title) {
    if (titleLabel == null && title != null && !title.isEmpty()) {
      titleLabel = new TextView(ctx);
      titleLabel.getElement().addClassName(Res.R.style().dialogTitle());
      titleLabel.getElement().getStyle().setProperty("margin", "0");
      titleLabelContainer.addView(titleLabel);
    }
    if (titleLabel != null) {
      titleLabel.setText(title);
    }
  }

  public void setMessage(int message) {
    setMessage(Context.resources.getString(message));
  }

  public void setMessage(CharSequence message) {
    if (messageLabel == null && message != null && !"".equals(message)) {
      messageLabel = new TextView(ctx);
      messageLabel.getElement().addClassName(Res.R.style().dialogMessage());
      contentPanel.addView(messageLabel);
    }
    if (messageLabel != null) {
      messageLabel.setText(message.toString().replace("\n", "<br/>"));
    }
  }

  public void setView(View view) {
    Element content = contentPanel.getElement();
    if (customView != null && customView.getParentElement() == content) {
      customView.removeFromParent();
    }
    customView = view == null ? null : view.getElement();
    if (customView != null) {
      if (view instanceof EditText) {
        customView.getStyle().setProperty("margin", "0 1rem 0.5rem");
      }
      content.appendChild(customView);
    }
  }

  public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
    if (whichButton != BUTTON_NEGATIVE && whichButton != BUTTON_NEUTRAL
        && whichButton != BUTTON_POSITIVE) {
      throw new IllegalArgumentException("Invalid dialog button: " + whichButton);
    }
    boolean layoutChanged;
    if (text == null || text.length() == 0) {
      layoutChanged = buttons.remove(whichButton) != null;
    } else {
      Button button = buttons.get(whichButton);
      layoutChanged = button == null;
      if (layoutChanged) {
        button = new Button(ctx);
        button.getElement().addClassName(Res.R.style().dialogButton());
        button.getElement().addClassName(Res.R.style().controlHighlight());
        buttons.put(whichButton, button);
      }
      button.getElement().setInnerText(text.toString());
      button.setOnClickListener(view -> {
        dismiss();
        if (listener != null) {
          listener.onClick(this, whichButton);
        }
      });
    }
    if (layoutChanged) {
      refreshButtons();
    }
  }

  private void refreshButtons() {
    Element row = buttonsLayout.getElement();
    row.removeAllChildren();
    for (int whichButton : BUTTON_ORDER) {
      Button button = buttons.get(whichButton);
      if (button != null) {
        row.appendChild(button.getElement());
      }
    }
    buttonsLayout.setVisibility(buttons.isEmpty() ? View.GONE : View.VISIBLE);
  }

  @Override
  public void show() {
    contentPanel.getElement().getStyle().setProperty("maxHeight", "75vh");
    popupPanel.show();
    Style style = popupPanel.getElement().getStyle();
    style.setPosition(Style.Position.FIXED);
    style.setTop(50, Style.Unit.PCT);
    style.setLeft(50, Style.Unit.PCT);
    style.setProperty("transform", "translate(-50%, -50%)");
    style.setProperty("minWidth", "min(20rem, 95vw)");
  }
}
