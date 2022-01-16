package training.java.xlsx;

import org.eclipse.jetty.util.StringUtil;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

  public static class TextField<T> extends XSLXCell<T> {

    private final String field;

    public TextField(String header, String field, boolean required) {
      super(header);
      this.field = field;
    }

    @Override
    public void map(SectionContext ctx, XLSXRow row, T entity) {
      String cell = row.getCellAsString(header, null);
      map(ctx, row, entity, cell);
    }

    @Override
    public void map(SectionContext sectionCtx, XLSXRow row, T entity, String cellVal) {
      try {
        final Class<?> aClass = entity.getClass();
        final Field field = aClass.getDeclaredField(this.field);
        field.setAccessible(true);
        field.set(entity, cellVal);
      } catch(Exception e) {
        throw new RuntimeException(cellVal);
      }
    }
  }

  static public class DoubleField<T> extends XSLXCell<T> {
    String field;
    double defaultValue;

    public DoubleField(String header, String field, double defaultVal, boolean required) {
      super(header, required);
      this.field = field;
      this.defaultValue = defaultVal;
    }

    @Override
    public void map(SectionContext ctx, XLSXRow row, T entity) {
      Double val = row.getCellAsDouble(header, defaultValue);
      try {
        final Class<?> aClass = entity.getClass();
        final Field field = aClass.getDeclaredField(this.field);
        field.setAccessible(true);
        field.set(entity, val);

      } catch(Exception e) {
        throw new RuntimeException();
      }
    }

    @Override
    public void map(SectionContext ctx, XLSXRow row, T entity, String cellVal) {
      try {
        final Class<?> aClass = entity.getClass();
        final Field field = aClass.getDeclaredField(this.field);
        field.setAccessible(true);
        if(StringUtil.isEmpty(cellVal)) {
          field.set(entity, defaultValue);
        } else {
          field.set(entity, Double.parseDouble(cellVal));
        }
      } catch(NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }

  static public class CompactDate<T> extends XSLXCell<T> {
    String field;

    public CompactDate(String header, String field, boolean required) {
      super(header, required);
      this.field = field;
    }

    @Override
    public void map(SectionContext ctx, XLSXRow row, T entity) {
      String val = row.getCellAsString(header, null);
      map(ctx, row, entity, val);
    }

    @Override
    public void map(SectionContext ctx, XLSXRow row, T entity, String val) {
      if(StringUtil.isEmpty(val)) return;
      try {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(val);
        final Class<?> aClass = entity.getClass();
        final Field field = aClass.getDeclaredField(this.field);
        field.setAccessible(true);
        field.set(entity, date);
      } catch (ParseException | NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException(val);
      }
    }
  }


}