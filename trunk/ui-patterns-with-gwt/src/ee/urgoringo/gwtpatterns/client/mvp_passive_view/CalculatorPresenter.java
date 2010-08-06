package ee.urgoringo.gwtpatterns.client.mvp_passive_view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.DOM;

public class CalculatorPresenter {

  private final CalculatorModel model;
  private final CalculatorView view;

  public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
    this.view = view;
    this.model = model;
    this.model.addChangeListener(new CalculatorModelChangeListener() {

      public void onModelChange(CalculatorModel model) {
        CalculatorPresenter.this.updateView(model);
      }});
  }

  public void registerCalculateEventSource(HasClickHandlers save) {
    save.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        calculate();
      }});
  }

  private void calculate() {
    model.setTime(Integer.valueOf(view.getTimeTextBox().getText()));
    model.setUserStories(Integer.valueOf(view.getUserStoriesTextBox().getText()));
    model.calculate();
  }
  
  private void updateView(CalculatorModel model) {
    view.getTimeTextBox().setText(model.getTime().toString());
    view.getUserStoriesTextBox().setText(model.getUserStories().toString());
    view.getBugCountTextBox().setText(model.getBugCount().toString());  

    switch (model.getQualityLevel()) {
      case FOUR :
        DOM.setStyleAttribute(view.getBugCountTextBox().getElement(), "color", "red");
        break;
      case THREE :
        DOM.setStyleAttribute(view.getBugCountTextBox().getElement(), "color", "blue");
        break;
      default :
        DOM.setStyleAttribute(view.getBugCountTextBox().getElement(), "color", "black");
        break;
    }
  }
}
