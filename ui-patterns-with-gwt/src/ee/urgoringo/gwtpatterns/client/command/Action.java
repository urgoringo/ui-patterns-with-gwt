package ee.urgoringo.gwtpatterns.client.command;

public interface Action<T> {

  void execute(ActionEvent<T> event);
}
