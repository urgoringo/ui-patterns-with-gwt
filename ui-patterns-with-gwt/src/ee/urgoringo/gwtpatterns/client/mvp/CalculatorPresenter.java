package ee.urgoringo.gwtpatterns.client.mvp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;

public class CalculatorPresenter {

  private final CalculatorModel model;
  private final CalculatorView view;

  public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
    this.model = model;
    this.model.addChangeListener(new CalculatorModelChangeListener() {

      public void onModelChange(CalculatorModel model) {
        CalculatorPresenter.this.view.update(model);
        updateQualityColor(model);
      }});
    this.view = view;
  }

  public void registerCalculateEventSource(HasClickHandlers save) {
    save.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        calculate();
      }});
  }

  private void calculate() {
    updateModel();
    model.calculate();
  }

  private void updateModel() {
    model.setTime(Integer.valueOf(view.getTimeTextBox().getText()));
    model.setUserStories(Integer.valueOf(view.getUserStoriesTextBox().getText()));
  }
  
  private void updateQualityColor(CalculatorModel model) {
    switch (model.getQualityLevel()) {
      case FOUR :
        view.setBugCountTextColor("red");
        break;
      case THREE :
        view.setBugCountTextColor("blue");
        break;
      default :
        view.setBugCountTextColor("black");
        break;
    }
  }
 
}
