package com.yhdyy.ybserver.bos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UploadRequest implements Serializable {
    public UploadRequest() {
    }
    /**
     * 请求流水号
     */
    private String reqSnum;
    /**
     * 请求时间
     */
    private String reqTime;
    /**
     * 医疗机构代码
     */
    private String insCode;
    /**
     * 业务类型
     */
    private String serviceType;
    /**
     * 签名数据
     */
    private String sign;
    /**
     * 业务内容
     */
    private Object data;

}
