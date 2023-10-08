package me.kzv.excelsample.dto;

import me.kzv.excelsample.excel.downloader.style.DefaultHeaderStyle;
import me.kzv.excelsample.excel.downloader.style.ExcelColumn;
import me.kzv.excelsample.excel.downloader.style.ExcelColumnStyle;
import me.kzv.excelsample.style.BlackHeaderStyle;
import me.kzv.excelsample.style.BlueHeaderStyle;

@DefaultHeaderStyle(style = @ExcelColumnStyle(excelCellStyleClass = BlueHeaderStyle.class))
public class ExcelDto {

    @ExcelColumn(headerName = "name")
    private String name;

    private String hideColumn;

    @ExcelColumn(headerName = "age",
            headerStyle = @ExcelColumnStyle(excelCellStyleClass = BlackHeaderStyle.class))
    private int age;

}