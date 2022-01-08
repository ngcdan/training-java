package training.java.xlsx;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XLSXWorkbook {

  @Getter
  private Workbook workbook;

  public List<String> getAllSheetNames() {
    List<String> names = new ArrayList<>();
    for(int i = 0; i < workbook.getNumberOfSheets(); i++) {
      names.add(workbook.getSheetAt(i).getSheetName());
    }
    return names;
  }

  public void open(String file) throws Exception {
    open(new FileInputStream(file));
  }

  public void open(InputStream is) throws Exception {
    workbook = new XSSFWorkbook(is);
  }

  public void close() throws IOException {
    if(workbook != null) {
      workbook.close();
      workbook = null;
    }
  }
}