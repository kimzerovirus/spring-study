package me.kzv.excelsample.excel.downloader.style.cell;

import me.kzv.excelsample.excel.downloader.style.align.ExcelAlign;
import me.kzv.excelsample.excel.downloader.style.align.NoExcelAlign;
import me.kzv.excelsample.excel.downloader.style.border.ExcelBorders;
import me.kzv.excelsample.excel.downloader.style.border.NoExcelBorders;
import me.kzv.excelsample.excel.downloader.style.color.DefaultExcelColor;
import me.kzv.excelsample.excel.downloader.style.color.ExcelColor;
import me.kzv.excelsample.excel.downloader.style.color.NoExcelColor;
import org.apache.poi.ss.usermodel.CellStyle;

public class ExcelCellStyleConfigurer {

    private ExcelAlign excelAlign = new NoExcelAlign();
    private ExcelColor foregroundColor = new NoExcelColor();
    private ExcelBorders excelBorders = new NoExcelBorders();

    public ExcelCellStyleConfigurer() {

    }

    public ExcelCellStyleConfigurer excelAlign(ExcelAlign excelAlign) {
        this.excelAlign = excelAlign;
        return this;
    }

    public ExcelCellStyleConfigurer foregroundColor(int red, int blue, int green) {
        this.foregroundColor = DefaultExcelColor.rgb(red, blue, green);
        return this;
    }

    public ExcelCellStyleConfigurer excelBorders(ExcelBorders excelBorders) {
        this.excelBorders = excelBorders;
        return this;
    }

    public void configure(CellStyle cellStyle) {
        excelAlign.apply(cellStyle);
        foregroundColor.applyForeground(cellStyle);
        excelBorders.apply(cellStyle);
    }

}
