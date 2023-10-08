package me.kzv.excelsample.style;

import me.kzv.excelsample.excel.downloader.style.align.DefaultExcelAlign;
import me.kzv.excelsample.excel.downloader.style.border.DefaultExcelBorders;
import me.kzv.excelsample.excel.downloader.style.border.ExcelBorderStyle;
import me.kzv.excelsample.excel.downloader.style.cell.CustomExcelCellStyle;
import me.kzv.excelsample.excel.downloader.style.cell.ExcelCellStyleConfigurer;

public class BlueHeaderStyle extends CustomExcelCellStyle {

    @Override
    public void configure(ExcelCellStyleConfigurer configurer) {
        configurer.foregroundColor(223, 235, 246)
                .excelBorders(DefaultExcelBorders.newInstance(ExcelBorderStyle.THIN))
                .excelAlign(DefaultExcelAlign.CENTER_CENTER);
    }

}
