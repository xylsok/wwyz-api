package com.xyls.wwyz.responseModel.goods;

/**
 * Created by Lee on 2017/6/12.
 */
public class FileUploadVM {

    public FileUploadVM(String realPath, String relativePath) {
        this.realPath = realPath;
        this.relativePath = relativePath;
    }

    private String realPath;
    private String relativePath;

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
