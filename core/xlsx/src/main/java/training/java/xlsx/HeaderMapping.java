package training.java.xlsx;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.jetty.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HeaderMapping {
  @Getter
  private final Map<String, Integer> headerMap = new LinkedHashMap<>();

  public HeaderMapping(Row row) {
    for(int i = 0; i < row.getLastCellNum(); i++) {
      String cell = XLSXRow.getCellAsText(row, i);
      if(StringUtil.isEmpty(cell)) continue;
      headerMap.put(cell, i);
    }
  }

  public String[] getHeaders() {
    List<String> headers = new ArrayList<>(this.headerMap.keySet());
    return headers.toArray(new String[0]);
  }


  public void add(String header, int colIdx) {
    if(headerMap.containsKey(header)) {
      throw new RuntimeException("Column " + header + " is already existed");
    }
    headerMap.put(header, colIdx);
  }

  public int getColumnIndex(String header) {
    Integer val = headerMap.get(header);
    if(val == null) return -1;
    return val;
  }
}