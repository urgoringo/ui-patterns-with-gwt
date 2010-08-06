package ee.urgoringo.gwtpatterns.client.presentationmodel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;

public class CalculatorController {

  private final CalculatorPresentationModel model;
  private final CalculatorView view;

  public CalculatorController(CalculatorPresentationModel model,
          CalculatorView view) {
    this.model = model;
    this.view = view;
  }

  public void registerCalculateEventSource(HasClickHandlers save) {
    save.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        calculate();
      }});
  }

  private void calculate() {
    model.setTime(Integer.valueOf(view.getTimeTextBox().getText()));
    model.setUserStories(Integer
            .valueOf(view.getUserStoriesTextBox().getText()));
    model.calculate();
  }

}
