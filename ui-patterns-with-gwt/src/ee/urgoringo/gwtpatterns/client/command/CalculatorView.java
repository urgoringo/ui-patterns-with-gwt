package ee.urgoringo.gwtpatterns.client.command;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CalculatorView extends Composite
        implements
          CalculatorModelChangeListener {
  private TextBox timeTextBox;
  private TextBox userStoriesTextBox;
  private TextBox bugCountTextBox;
  private VerticalPanel panel;
  private Button calculate;
  private final CalculatorModel model;

  public CalculatorView(final CalculatorModel model) {
    this.model = model;
    panel = new VerticalPanel();
    initWidget(panel);
    buildUi();

    calculate.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        new CalculateAction().execute(new ActionEvent<CalculatorView>(
                CalculatorView.this));
      }
    });

    model.addChangeListener(this);
  }

  private void buildUi() {
    timeTextBox = new TextBox();
    addInputWidget(panel, "Time (in man-days)", timeTextBox);
    userStoriesTextBox = new TextBox();
    addInputWidget(panel, "No of user stories", userStoriesTextBox);
    bugCountTextBox = new TextBox();
    bugCountTextBox.setReadOnly(true);
    addInputWidget(panel, "No of bugs", bugCountTextBox);
    calculate = new Button("Calculate");
    addInputWidget(panel, "", calculate);
  }

  public void onModelChange(CalculatorModel model) {
    updateView(model);
  }

  private void updateView(CalculatorModel model) {
    timeTextBox.setText(model.getTime().toString());
    userStoriesTextBox.setText(model.getUserStories().toString());
    bugCountTextBox.setText(model.getBugCount().toString());

    updateQualityColor(model);
  }

  public HasText getTimeTextBox() {
    return timeTextBox;
  }

  public TextBox getUserStoriesTextBox() {
    return userStoriesTextBox;
  }

  public TextBox getBugCountTextBox() {
    return bugCountTextBox;
  }

  public VerticalPanel getPanel() {
    return panel;
  }

  private void updateQualityColor(CalculatorModel model) {
    switch (model.getQualityLevel()) {
      case FOUR :
        DOM.setStyleAttribute(bugCountTextBox.getElement(), "color", "red");
        break;
      case THREE :
        DOM.setStyleAttribute(bugCountTextBox.getElement(), "color", "blue");
        break;
      default :
        DOM.setStyleAttribute(bugCountTextBox.getElement(), "color", "black");
        break;
    }
  }

  private void addInputWidget(VerticalPanel panel, String labelName,
          Widget inputWidget) {
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    Label label = new Label(labelName);
    label.setStyleName("dialogLabel");
    horizontalPanel.add(label);
    inputWidget.setStyleName("dialogLabel");
    horizontalPanel.add(inputWidget);
    panel.add(horizontalPanel);
  }

  public CalculatorModel getModel() {
    return model;
  }
}
