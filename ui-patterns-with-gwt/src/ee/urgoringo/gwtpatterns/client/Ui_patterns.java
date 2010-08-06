package ee.urgoringo.gwtpatterns.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import ee.urgoringo.gwtpatterns.client.mvc.CalculatorModel;
import ee.urgoringo.gwtpatterns.client.mvc.CalculatorView;

public class Ui_patterns implements EntryPoint {

  public void onModuleLoad() {
    RootPanel.get().add(new CalculatorView(new CalculatorModel()));
//  RootPanel.get().add(new CalculatorView(new CalculatorPresentationModel()));    
  }
}
