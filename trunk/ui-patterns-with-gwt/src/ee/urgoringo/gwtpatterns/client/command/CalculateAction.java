package ee.urgoringo.gwtpatterns.client.command;

public class CalculateAction implements Action<CalculatorView> {

  public void execute(ActionEvent<CalculatorView> event) {
    CalculatorView view = event.getSource();

    updateModel(view);
    view.getModel().calculate();
  }

  private void updateModel(CalculatorView view) {
    CalculatorModel model = view.getModel();
    model.setTime(Integer.valueOf(view.getTimeTextBox().getText()));
    model.setUserStories(Integer
            .valueOf(view.getUserStoriesTextBox().getText()));
  }
}
