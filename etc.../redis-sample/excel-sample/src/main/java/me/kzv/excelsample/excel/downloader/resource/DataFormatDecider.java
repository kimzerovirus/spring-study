package me.kzv.excelsample.excel.downloader.resource;

import org.apache.poi.ss.usermodel.DataFormat;

public interface DataFormatDecider {

    short getDataFormat(DataFormat dataFormat, Class<?> type);

}
