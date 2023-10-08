package me.kzv.excelsample.excel.downloader.style.border;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.BorderStyle;

@Getter
@RequiredArgsConstructor
public enum ExcelBorderStyle {
    // Custom Enum 으로 변환했지만 굳이 안하고 바로 POI 의 BorderStyle 을 사용해도 될듯.
    NONE(BorderStyle.NONE),
    THIN(BorderStyle.THIN),
    MEDIUM(BorderStyle.MEDIUM),
    DASHED(BorderStyle.DASHED),
    DOTTED(BorderStyle.DOTTED),
    THICK(BorderStyle.THICK),
    DOUBLE(BorderStyle.DOUBLE),
    HAIR(BorderStyle.HAIR),
    MEDIUM_DASHED(BorderStyle.MEDIUM_DASHED),
    DASH_DOT(BorderStyle.DASH_DOT),
    MEDIUM_DASH_DOT(BorderStyle.MEDIUM_DASH_DOT),
    DASH_DOT_DOT(BorderStyle.DASH_DOT_DOT),
    MEDIUM_DASH_DOT_DOT(BorderStyle.MEDIUM_DASH_DOT_DOT),
    SLANTED_DASH_DOT(BorderStyle.SLANTED_DASH_DOT);

    private final BorderStyle style;
}
