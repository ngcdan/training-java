package training.java.xlsx;

import org.eclipse.jetty.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class XSLXCell<T> {
  public interface CellValidate {
    boolean validate(SectionContext ctx, String header, String val);
  }

  public interface CellMapper<T> {
    void map(SectionContext ctx, XLSXRow row, T entity, String cellValue);
  }

  final public String header;
  protected boolean required;
  protected List<Validator> validators;
  private CellMapper<T>   mapper;

  protected XSLXCell(String name) {
    this.header = name;
  }

  public XSLXCell(String name, boolean required) {
    this.header = name;
    this.required = required;
    if(required) add("Cell cannot be empty", (ctx, header, cellVal) -> !StringUtil.isEmpty(cellVal));
  }

  public XSLXCell<T> add(String mesg, CellValidate validator) {
    if (validators == null) validators = new ArrayList<>();
    validators.add(new Validator(validator, mesg));
    return this;
  }

  public boolean validate(SectionContext ctx, XLSXRow row) {
    if(validators == null) return true;
    String cellVal = row.getCellAsString(header, null);
    boolean validated = true;
    for(Validator validator : validators) {
      if(!validator.callable.validate(ctx, header, cellVal)) {
        String message = validator.message;
        String prefix = "[header=" + header + ", cell=" + cellVal + "]" ;
        row.addError(prefix, message);
        validated = false;
      }
    }
    return validated;
  }

  public boolean validate(SectionContext ctx, XLSXRow row, String cellVal) {
    if(validators == null) return true;
    boolean validated = true;
    for(Validator validator : validators) {
      if(!validator.callable.validate(ctx, header, cellVal)) {
        String message = validator.message;
        ctx.addError(header, message);
        validated = false;
      }
    }
    return validated;
  }

  public XSLXCell<T> mapper(CellMapper<T> mapper) {
    this.mapper = mapper;
    return this;
  }

  public void map(SectionContext ctx, XLSXRow row, T entity) {
    String cellVal = row.getCellAsString(header, null);
    mapper.map(ctx, row, entity, cellVal);
  }

  public void map(SectionContext sectionCtx, XLSXRow row, T entity, String cellVal) {
    mapper.map(sectionCtx, row, entity, cellVal);
  }

  static public class Validator {
    String message ;
    CellValidate callable;

    Validator(CellValidate validator, String message) {
      this.callable = validator;
      this.message   = message;
    }
  }
}