package com.yhdyy.ybserver.bos;

import lombok.Data;

import java.util.Date;

@Data
// 详见烟台市医保数据资源中心数据接入标准（业务内容部分）V1.24
public class RegBo extends BaseBo {
    /**
     * 挂/退号日期
     */
    public String GHRQ;
    /**
     * 门诊就诊流水号
     */
    public String GHBM;
    /**
     * 退号标志
     */
    public String GTHBZ;
    /**
     * 医疗机构代码
     */
    public String YLJGDM;
    /**
     * 收/退费编码
     */
    public String STFBH;
    /**
     * 挂/退号时间
     */
    // datetime
    public String GTHSJ;
    /**
     * 挂号类别
     */
    public String GHLB;
    /**
     * 保险类别
     */
    public String BXLB;
    /**
     * 科室编码
     */
    public String KSBM;
    /**
     * 特需标志 0-非特 1-特需
     */
    public String TXBZ;
    /**
     * 外地标志
     */
    public String WDBZ;
    /**
     * 自费诊疗费
     */
    public String ZFZLF;
    /**
     * 诊疗费（含挂号费）
     */
    //数字
    public String ZLF;
    /**
     * 其他费
     */
    //数字
    public String QTF;
    /**
     * 卡号
     */
    public String KH;
    /**
     * 卡类型
     */
    public String KLX;
    /**
     * 是否预约
     */
    //数字
    public String SFYY;
    /**
     * 挂号人次标识 1-计入 2-不计入
     */
    public String GHRCBS;
    /**
     * 一站式收费 1-是 2 否
     */
    public String YZSSF;
    /**
     * 修改标志 0-正常 1-撤销
     */
    //数字
    public String XGBZ;
    /**
     * 预留1
     */
    public String YLYL1;
    /**
     * 预留2
     */
    public String YLYL2;


}
