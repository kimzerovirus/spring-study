package me.kzv.excelsample.exception;

public class NoExcelColumnAnnotationsException extends ExcelException {

    public NoExcelColumnAnnotationsException(String message) {
        super(message, null);
    }

}
