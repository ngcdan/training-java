package training.java.xlsx;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.jetty.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class XLSXRow {

  @Getter
  private final HeaderMapping headerMapping;
  @Getter
  private final Row           row;
  private final int           index;
  @Getter
  private final List<String> errors;

  public XLSXRow(HeaderMapping headerMapping, Row row, int idx) {
    this.headerMapping = headerMapping;
    this.row = row;
    this.index = idx;
    this.errors = new ArrayList<>();
  }

  public int getRowIndex() { return index; }

  public boolean hasError() {
    return errors.size() > 0;
  }

  public void addError(String column, String message) {
    this.errors.add("[" + column + "] " + message);
  }

  public void throwErrorMessages() {
    if (errors.size() > 0) {
      StringBuilder msgBuilder = new StringBuilder();
      for (String msg : errors) {
        msgBuilder.append(msg).append("\n");
      }
      throw new RuntimeException(msgBuilder.toString());
    }
  }

  public void validateNumericCells(String ... colName) {
    for(String sel : colName) {
      int colIdx = headerMapping.getColumnIndex(sel);
      String ret = getCellAsText(row, colIdx);
      if (StringUtil.isBlank(ret)) return;

      try {
        Double.valueOf(ret);
      } catch (NumberFormatException e) {
        addError(sel, "' - value '" + ret + "' must be a numeric");
      }
    }
  }

  public void validateNotEmptyCells(String ... colName) {
    for(String sel : colName) {
      int colIdx = headerMapping.getColumnIndex(sel);
      String ret = getCellAsText(row, colIdx);
      if(StringUtil.isEmpty(ret)) {
        addError(sel, "' - value '" + ret + "' must not be empty!");
      }
    }
  }

  public String[] getCellValuesAsString() {
    String[] cells = new String[row.getPhysicalNumberOfCells()];
    for(int i = 0; i < cells.length; i++) {
      cells[i] = getCellAsText(row, i);
    }
    return cells;
  }

  public Object getCellValue(String colName) {
    int colIdx = headerMapping.getColumnIndex(colName);
    if(colIdx < 0) throw new IllegalArgumentException("No column " + colName);
    Cell currCell = row.getCell(colIdx);
    if(currCell == null) return null ;
    if(currCell.getCellType() == CellType.NUMERIC) {
      return currCell.getNumericCellValue();
    } else if(currCell.getCellType() == CellType.BOOLEAN) {
      return currCell.getBooleanCellValue();
    } else {
      return currCell.getStringCellValue();
    }
  }

  public boolean getCellAsBoolean(String colName) {
    final String ret = getCellAsText(this, headerMapping, colName);
    if(StringUtil.isEmpty(ret)) return false;
    return Boolean.parseBoolean(ret.trim());
  }

  public String getCellAsString(String colName, String defaultValue) {
    final int colIdx = headerMapping.getColumnIndex(colName);
    if(colIdx < 0 ) return defaultValue;
    final String ret = getCellAsText(row, colIdx);
    if(ret == null)  return defaultValue;
    return ret;
  }

  public String getCellAsString(String colName) {
    final String ret = getCellAsText(this, headerMapping, colName);
    return ret.trim();
  }

  static public String getCellAsText(XLSXRow row, HeaderMapping hMapping, String colName) {
    int colIdx = hMapping.getColumnIndex(colName);
    if(colIdx < 0) throw new IllegalArgumentException("No column " + colName);
    return getCellAsText(row.row, colIdx);
  }

  static public String getCellAsText(Row row, int col) {
    Cell currCell = row.getCell(col);
    if(currCell == null) return null;
    if(currCell.getCellType() == CellType.NUMERIC) {
      if (currCell.getCellStyle().getDataFormatString().contains("%")) {
        return currCell.getNumericCellValue() * 100 + "%";
      }
      return Double.toString(currCell.getNumericCellValue());
    } else if(currCell.getCellType() == CellType.BOOLEAN) {
      boolean val =  currCell.getBooleanCellValue();
      return Boolean.toString(val);
    } else {
      return currCell.getRichStringCellValue().getString();
    }
  }

  public double getCellAsDouble(String colName, double defaultVal) {
    int colIdx = headerMapping.getColumnIndex(colName);
    if(colIdx < 0) return defaultVal;
    return getCellAsDouble(row, colIdx, defaultVal);
  }

  static public double getCellAsDouble(Row row, int colIdx, double defaultVal) {
    Cell currCell = row.getCell(colIdx);
    if(currCell == null) return defaultVal;
    if(currCell.getCellType() == CellType.NUMERIC) {
      return currCell.getNumericCellValue();
    } else {
      String val = currCell.getRichStringCellValue().getString();
      if(val == null || StringUtil.isEmpty(val)) return defaultVal;
      return Double.parseDouble(val);
    }
  }

  static public double getCellAsDouble(XLSXRow row, HeaderMapping hMapping, String colName, double defaultVal) {
    int colIdx = hMapping.getColumnIndex(colName);
    return getCellAsDouble(row.row, colIdx, defaultVal);
  }
}