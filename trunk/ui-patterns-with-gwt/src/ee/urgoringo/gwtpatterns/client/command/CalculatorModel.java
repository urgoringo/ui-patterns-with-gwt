package ee.urgoringo.gwtpatterns.client.command;

public class CalculatorModel {
  private Integer time = Integer.valueOf(0);
  private Integer userStories = Integer.valueOf(0);
  private Integer bugCount = Integer.valueOf(0);
  private CalculatorModelChangeListener changeListener;

  public void setTime(Integer time) {
    this.time = time;
  }

  public void setUserStories(Integer functions) {
    this.userStories = functions;
  }

  public void addChangeListener(CalculatorModelChangeListener listener) {
    changeListener = listener;
  }

  public void calculate() {
    int result = (userStories - time) / 2;
    
    if (result < 0) {
      bugCount = 0;
    } else {
      bugCount = result;
    }
    
    changeListener.onModelChange(this);
  }

  public CmmiLevel getQualityLevel() {
    if (bugCount < 1) {
      return CmmiLevel.FOUR;
    }
    if (bugCount < 3) {
      return CmmiLevel.THREE;
    }
    return CmmiLevel.TWO;
  }

  public Integer getBugCount() {
    return bugCount;
  }

  public Integer getTime() {
    return time;
  }

  public Integer getUserStories() {
    return userStories;
  }
  
  public static enum CmmiLevel {
    FOUR, THREE, TWO
  }
}
