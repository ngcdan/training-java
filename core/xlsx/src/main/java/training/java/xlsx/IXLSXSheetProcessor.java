package training.java.xlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.jetty.util.StringUtil;

import java.util.Iterator;

public interface IXLSXSheetProcessor {
  static public enum Instruction { META, SECTION, END_SECTION, COMMENT, EMPTY, DATA }

  public <T extends XLSXSectionProcessor<?>> T getSectionProcessor(String name);

  default boolean isEmptyRow(Row row) {
    Iterator<Cell> i = row.cellIterator();
    int count = 0;
    while(i.hasNext()) {
      Cell cell = i.next();
      if(!StringUtil.isEmpty(cell.toString()))  return false;
      count++;
      if(count > 20) return true;
    }
    return true;
  }
}