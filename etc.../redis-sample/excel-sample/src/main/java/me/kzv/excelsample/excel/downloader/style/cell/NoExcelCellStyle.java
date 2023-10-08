package me.kzv.excelsample.excel.downloader.style.cell;

import org.apache.poi.ss.usermodel.CellStyle;

public class NoExcelCellStyle implements ExcelCellStyle{

    @Override
    public void apply(CellStyle cellStyle) {
        // Do Nothing.. no style
    }
}
