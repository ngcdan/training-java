package training.java.xlsx;

import lombok.Getter;
import training.java.io.DataContext;
import training.java.io.utils.IOUtil;

import java.io.InputStream;

public class XLSXDataSheetProcessor {
  static public interface OnPostProcess {
    void call(String sheetName, IXLSXSheetProcessor sheetProcessor) ;
  }

  @Getter
  XLSXWorkbook workbook ;
  DataContext dataContext;

  public XLSXDataSheetProcessor open(String xlsxRes) throws Exception {
    InputStream is = IOUtil.loadResource(xlsxRes);
    return open(is);
  }

  public XLSXDataSheetProcessor open(InputStream is) throws Exception {
    dataContext = new DataContext();
    workbook = new XLSXWorkbook();
    workbook.open(is);
    return this;
  }

  public XLSXDataSheetProcessor addAttribute(Object value) {
    dataContext.addAttribute(value);
    return this;
  }

  public XLSXDataSheetProcessor addAttribute(String name, Object value) {
    dataContext.addAttribute(name, value);
    return this;
  }

  public void close() throws Exception {
    if(workbook == null) return;
    workbook.close();
  }

  public void process(String sheetName, XLSXSimpleSheetProcessor sheetProcessor, OnPostProcess onPostProcess) throws Exception {
    if(sheetName.startsWith("__")) return;
    sheetProcessor.process(dataContext, workbook, sheetName);
    onPostProcess.call(sheetName, sheetProcessor);
  }

  public void process(String sheetName, XLSXSimpleSheetProcessor sheetProcessor) throws Exception {
    if(sheetName.startsWith("__")) return;
    sheetProcessor.process(dataContext, workbook, sheetName);
  }
}