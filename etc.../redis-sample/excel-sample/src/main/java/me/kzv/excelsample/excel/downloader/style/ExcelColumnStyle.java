package me.kzv.excelsample.excel.downloader.style;

import me.kzv.excelsample.excel.downloader.style.cell.ExcelCellStyle;
import me.kzv.excelsample.excel.downloader.style.cell.CustomExcelCellStyle;
import me.kzv.excelsample.excel.downloader.style.cell.DefaultExcelCellStyle;

public @interface ExcelColumnStyle {

    /**
     * Enum implements {@link ExcelCellStyle}
     * Also, can use just class.
     * If not use Enum, enumName will be ignored
     * @see DefaultExcelCellStyle
     * @see CustomExcelCellStyle
     */
    Class<? extends ExcelCellStyle> excelCellStyleClass();

    /**
     * name of Enum implements {@link ExcelCellStyle}
     * if not use Enum, enumName will be ignored
     */
    String enumName() default "";

}
