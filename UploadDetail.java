package com;

public class UploadDetail {
    private String fileName;
    private long fileSize;

    public UploadDetail(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() { return fileName; }
    public long getFileSize() { return fileSize; }
}
