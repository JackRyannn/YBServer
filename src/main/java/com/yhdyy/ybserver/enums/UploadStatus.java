package com.yhdyy.ybserver.enums;

public enum UploadStatus {
    PENDING("待处理", "0"),
    UPLOAD_SUCCESS("成功", "1"),
    UPLOAD_FAILURE("失败", "2"),
    SAVE_SUCCESS("入库成功", "3"),
    SAVE_FAILURE("入库失败", "4"),
    CANCEL("作废", "5");

    private final String name;
    private final String value;

    UploadStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
