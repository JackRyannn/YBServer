package com.yhdyy.ybserver.bos;

import lombok.Data;

import java.util.Date;

@Data
public class BaseBo {
    /**
     * 医保结算时间
     */
    public Date yb_date;
    /**
     * 插入时间
     */
    public Date insert_date;
    /**
     * 上传状态 0-待处理 1-已发送 2-发送失败 3-入库成功 4-入库失败 5-作废
     */
    public String upload_status;
    /**
     * 上传日期
     */
    public Date upload_date;
    /**
     * 返回结果
     */
    public String result_code;
    /**
     * 返回额外信息
     */
    public String message;
    /**
     * 操作人
     */
    public String upload_opera;
    /**
     * 请求流水号，用于重传
     */
    public String upload_serial;
}
