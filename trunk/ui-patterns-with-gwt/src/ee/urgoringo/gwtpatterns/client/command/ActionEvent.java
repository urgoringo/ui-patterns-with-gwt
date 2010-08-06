package ee.urgoringo.gwtpatterns.client.command;

public class ActionEvent<T> {

  public ActionEvent(T source) {
    this.source = source;    
  }
  
  public T getSource() {
    return source;
  }

  private T source;
}
