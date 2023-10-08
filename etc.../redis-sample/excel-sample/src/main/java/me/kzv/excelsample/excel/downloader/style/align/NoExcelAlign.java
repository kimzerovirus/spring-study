package me.kzv.excelsample.excel.downloader.style.align;

import org.apache.poi.ss.usermodel.CellStyle;

public class NoExcelAlign implements ExcelAlign {

    @Override
    public void apply(CellStyle cellStyle) {
        // Do Nothing.. No Style

    }
}
