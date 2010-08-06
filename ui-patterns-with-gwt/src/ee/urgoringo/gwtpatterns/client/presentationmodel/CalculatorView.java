package ee.urgoringo.gwtpatterns.client.presentationmodel;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CalculatorView extends Composite implements CalculatorModelChangeListener {
  private TextBox timeTextBox;
  private TextBox userStoriesTextBox;
  private TextBox bugCountTextBox;
  private VerticalPanel panel;
  private Button calculate;

  public TextBox getBugCountTextBox() {
    return bugCountTextBox;
  }

  public TextBox getTimeTextBox() {
    return timeTextBox;
  }
  
  public TextBox getUserStoriesTextBox() {
    return userStoriesTextBox;
  }

  public CalculatorView(CalculatorPresentationModel model) {
    panel = new VerticalPanel();
    initWidget(panel);
    buildUi();

    model.addChangeListener(this);
    CalculatorController controller = new CalculatorController(
            model, this);
    controller.registerCalculateEventSource(calculate);
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
  
  public void onModelChange(CalculatorPresentationModel model) {
    update(model);
  }

  private void update(CalculatorPresentationModel model) {
    getTimeTextBox().setText(model.getTime().toString());
    getUserStoriesTextBox().setText(model.getUserStories().toString());
    getBugCountTextBox().setText(model.getBugCount().toString());
    DOM.setStyleAttribute(getBugCountTextBox().getElement(), "color", model
            .getQualityColor());
  }

}
