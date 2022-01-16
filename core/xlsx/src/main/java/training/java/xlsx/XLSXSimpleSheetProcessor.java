package training.java.xlsx;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import training.java.io.DataContext;

import java.util.Iterator;

public class XLSXSimpleSheetProcessor implements IXLSXSheetProcessor {
  private SheetMetadata metadata;
  private Iterator<Row> rowIterator;
  private Row           currentRow;

  protected XLSXSectionProcessor<?> sectionProcessor;

  @Override
  public <T extends XLSXSectionProcessor<?>> T getSectionProcessor(String name) {
    return (T) sectionProcessor;
  }

  public XLSXSimpleSheetProcessor(XLSXSectionProcessor<?> sectionProcessor) {
    this.sectionProcessor = sectionProcessor;
  }

  public void process(DataContext ctx, XLSXWorkbook workbook, String sheetName) throws Exception {
    process(ctx, workbook.getWorkbook(), sheetName, XLSXProcessMode.CUSTOM);
  }

  public void process(DataContext ctx, Workbook workbook, String sheetName, XLSXProcessMode mode) throws Exception {
    Sheet sheet = workbook.getSheet(sheetName);
    if(sheet == null) throw new IllegalArgumentException("Sheet " + sheetName + " is not available");

    sectionProcessor.onStartSheet(ctx, this);
    rowIterator = sheet.iterator();
    metadata = new SheetMetadata();

    Row row;
    SectionContext sectionContext = null;

    while((row = nextRow()) != null) {
      Instruction ins = getRowInstruction(row);
      if(Instruction.META == ins) {
        String name = XLSXRow.getCellAsText(row, 1);
        String value = XLSXRow.getCellAsText(row, 2);
        metadata.put(name, value);
        continue;
      } else if(Instruction.COMMENT == ins) {
        continue;
      }
      HeaderMapping headerMapping = new HeaderMapping(row);
      sectionContext = new SectionContext("data", ctx, metadata, headerMapping);
      break;
    }

    ctx.addAttribute(metadata);
    onStartSection(sectionContext);
    doRead(sectionContext,  sectionContext.getHeaderMapping(), mode);
    onEndSection(sectionContext);
    sectionProcessor.onEndSheet(ctx, this);
  }

  protected void doRead(SectionContext ctx, HeaderMapping headerMapping, XLSXProcessMode mode) throws Exception {
    int rowIdx = 0;
    Row row;
    while((row = nextRow()) != null) {
      XLSXRow currentRow = new XLSXRow(headerMapping, row, rowIdx);
      onProcessRow(ctx, currentRow, mode);
      rowIdx++;
    }
  }

  protected void onStartSection(SectionContext ctx) throws Exception {
    sectionProcessor.onStartSection(this, ctx);
    IXLSXSectionProcessorPlugin plugin = sectionProcessor.getPlugin();
    if(plugin != null) plugin.onStartSection(this, ctx);
  }

  protected void onProcessRow(SectionContext ctx, XLSXRow row, XLSXProcessMode mode) throws Exception {
    IXLSXSectionProcessorPlugin plugin = sectionProcessor.getPlugin();
    if(plugin != null) {
      plugin.onProcessRow(this, ctx, row, mode);
    } else {
      sectionProcessor.mapRow(ctx, row);
    }
  }

  protected void onEndSection(SectionContext ctx) throws Exception {
    sectionProcessor.onEndSection(this, ctx);
    IXLSXSectionProcessorPlugin plugin = sectionProcessor.getPlugin();
    if(plugin != null) plugin.onEndSection(this, ctx);
  }

  private Row nextRow() {
    while(rowIterator.hasNext()) {
      Row row = rowIterator.next();
      Instruction ins = getRowInstruction(row);
      if(Instruction.COMMENT == ins) {
        continue;
      }
      if(!isEmptyRow(row)) {
        currentRow = row;
        return currentRow;
      }
    }
    currentRow = null;
    return null;
  }

  private Instruction getRowInstruction(Row row) {
    String cellVal = XLSXRow.getCellAsText(row, 0);
    if("_META_".equals(cellVal)) return Instruction.META;
    if("_COMMENT_".equals(cellVal)) return Instruction.COMMENT;
    return Instruction.DATA;
  }
}