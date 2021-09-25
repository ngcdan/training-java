package training.java.design.pattern.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class LunchOrderBean {
  @Getter
  private final String bread;
  @Getter
  private final String condiments;
  @Getter
  private final String dressing;
  @Getter
  private final String meat;
  
  public LunchOrderBean(Builder builder) {
    bread = builder.bread;
    condiments = builder.condiments;
    dressing = builder.dressing;
    meat = builder.meat;
  }
  
  @NoArgsConstructor
  public static class  Builder {
    private String bread;
    private String condiments;
    private String dressing;
    private String meat;
    
    public LunchOrderBean build() {
      return new LunchOrderBean(this);
    }
    
    public Builder bread(String bread) {
      this.bread = bread;
      return this;
    }
  
    public Builder condiments(String condiments) {
      this.condiments = condiments;
      return this;
    }
  
    public Builder dressing(String dressing) {
      this.dressing = dressing;
      return this;
    }
  
    public Builder meat(String meat) {
      this.meat = meat;
      return this;
    }
  }
  
  
}
