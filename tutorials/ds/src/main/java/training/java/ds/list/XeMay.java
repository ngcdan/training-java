package training.java.ds.list;

public abstract class XeMay implements Xe {

  protected  String bienSo;

  public abstract void ganBienSo();

  @Override
  public String chay() {
    return "chay bang xang";
  }

  @Override
  public String bopPhanh() {
    return "phanh dia";
  }

  }