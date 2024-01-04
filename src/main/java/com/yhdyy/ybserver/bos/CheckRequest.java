package com.yhdyy.ybserver.bos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CheckRequest implements Serializable {
    public CheckRequest() {
    }
    /**
     * 请求流水号
     */
    private String reqSnum;
    /**
     * 医疗机构代码
     */
    private String insCode;
    /**
     * 业务类型
     */
    private String serviceType;
}
