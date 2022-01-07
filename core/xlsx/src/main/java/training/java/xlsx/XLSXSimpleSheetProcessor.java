package training.java.xlsx;

import org.apache.poi.ss.usermodel.Row;
import training.java.io.DataContext;

import java.util.Iterator;

public class XLSXSimpleSheetProcessor implements IXLSXSheetProcessor {
  private SheetMetadata metadata;
  private Iterator<Row> rowIterator;
  private Row           currentRow;

  private XLSXSectionProcessor<?> sectionProcessor;

  @Override
  public <T extends XLSXSectionProcessor<?>> T getSectionProcessor(String name) {
    return null;
  }

  public void process(DataContext dataContext, XLSXWorkbook workbook, String sheetName) {}
}