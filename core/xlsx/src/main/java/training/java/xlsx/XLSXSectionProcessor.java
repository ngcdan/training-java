package training.java.xlsx;

import lombok.Getter;
import lombok.Setter;
import training.java.io.DataContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class XLSXSectionProcessor<T> {
  protected Map<String, XSLXCell<T>> fieldMap = new HashMap<>();

  @Getter @Setter
  private IXLSXSectionProcessorPlugin plugin;

  @Getter
  protected List<T> collector;

  @Getter
  protected T entity;

  public XLSXSectionProcessor<T> newCollector() {
    collector = new ArrayList<>();
    return this;
  }

  public XLSXSectionProcessor<T> collect(T entity) {
    collector.add(entity);
    return this;
  }

  protected void add(XSLXCell<T> field) {
    fieldMap.put(field.header, field);
  }

  // theo chieu doc
  protected void mapRow(SectionContext ctx, XLSXRow row, T entity, String name, String value) {
    XSLXCell<T> field = fieldMap.get(name);
    if(field == null) {
      ctx.addError(name, "Unknown field " + name + " with value " + value);
      return;
    }
    if(field.validate(ctx, row, value)) {
      field.map(ctx, row, entity, value);
    }
  }

  //theo chieu ngang
  final public void mapRow(SectionContext ctx, XLSXRow row, T entity) {
    this.entity = entity;
    for(XSLXCell<T> field : fieldMap.values()) {
      if(field.validate(ctx, row)) {
        field.map(ctx, row, entity);
      }
    }
  }

  public void mapRow(SectionContext ctx, XLSXRow row) throws Exception {
    throw new IllegalStateException("You need to override this method");
  }

  protected void onStartSheet(DataContext ctx, IXLSXSheetProcessor sheetProcessor) throws Exception {}

  protected void onEndSheet(DataContext ctx, IXLSXSheetProcessor sheetProcessor) throws Exception {}

  protected void onStartSection(IXLSXSheetProcessor sheetProcessor, SectionContext ctx) throws Exception {}

  protected void onEndSection(IXLSXSheetProcessor sheetProcessor, SectionContext ctx) throws Exception {
    ctx.dumpError();
  }

  public void validateRow(SectionContext ctx, XLSXRow row) {
    for(XSLXCell<?> cell : fieldMap.values()) {
      cell.validate(ctx, row);
    }
  }
}