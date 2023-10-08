package me.kzv.excelsample.excel.downloader.style.color;

import org.apache.poi.ss.usermodel.CellStyle;

public class NoExcelColor implements ExcelColor {

    @Override
    public void applyForeground(CellStyle cellStyle) {
        // Do Nothing.. No Style
    }

}
