package me.kzv.fileupload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String originalFilename, String storeFileName) {
        this.storeFileName = originalFilename;
        this.storeFileName = storeFileName;
    }
}
