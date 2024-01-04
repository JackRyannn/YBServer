package com.yhdyy.ybserver.bos;

import lombok.Data;

import java.util.Date;

@Data
// 详见烟台市医保数据资源中心数据接入标准（业务内容部分）V1.24
public class PrescriptionDetailBo extends BaseBo {
    /**
     * 处方号
     */
    public String CYH;
    /**
     * 处方项目明细号码
     */
    public String CFMXH;
    /**
     * 医疗机构代码
     */
    public String YLJGDM;
    /**
     * 门诊就诊流水号
     */
    public String JZLSH;
    /**
     * 处方类型
     */
    public String CFLX;
    /**
     * 中药处方类别
     */
    public String ZYCFLB;
    /**
     * 卡号
     */
    public String KH;
    /**
     * 卡类型
     */
    public String KLX;
    /**
     * 证件类型
     */
    public String ZJLX;
    /**
     * 证件号码
     */
    public String ZJHM;
    /**
     * 就诊科室代码
     */
    public String JZKSDM;
    /**
     * 就诊科室名称
     */
    public String JZKSMC;
    /**
     * 疾病诊断编码
     */
    public String JZZDBM;
    /**
     * 编码类型
     */
    public String BMLX;
    /**
     * 开方医生工号
     */
    public String KFYS;
    /**
     * 开方医生姓名
     */
    public String KFYSXM;
    /**
     * 开方时间
     */
    public String KFRQ;
    /**
     * 项目编码（院内）
     */
    public String XMBM;
    /**
     * 项目名称（院内）
     */
    public String XMMC;
    /**
     * 项目编码（医保）
     */
    public String XMBMYB;
    /**
     * 生产批号
     */
    public String SCPH;
    /**
     * 有效期至
     */
    public String YXQZ;
    /**
     * 是否药品
     */
    public String SFYP;
    /**
     * 剂型代码
     */
    public String JXDM;
    /**
     * 药品规格
     */
    public String YPGG;
    /**
     * 用药途径代码
     */
    public String YF;
    /**
     * 药品用法
     */
    public String YPYF;
    /**
     * 发药数量
     */
    public String YPSL;
    /**
     * 发药数量单位
     */
    public String YPDW;
    /**
     * 处方贴数
     */
    public String CFTS;
    /**
     * 医嘱组号
     */
    public String YZZH;
    /**
     * 用药频次
     */
    public String SYPC;
    /**
     * 每次使用剂量
     */
    public String JL;
    /**
     * 每次使用剂量单位
     */
    public String DW;
    /**
     * 使用总剂量
     */
    public String ZJL;
    /**
     * 每次使用数量
     */
    public String MCSL;
    /**
     * 每次使用数量单位
     */
    public String MCDW;
    /**
     * 用药天数
     */
    public String YYTS;
    /**
     * 调配药师工号
     */
    public String DPYSGH;
    /**
     * 调配药师姓名
     */
    public String DPYSXM;
    /**
     * 核对药师工号
     */
    public String HDYSGH;
    /**
     * 核对药师姓名
     */
    public String HDYSXM;
    /**
     * 发药药师工号
     */
    public String FYYSGH;
    /**
     * 发药药师姓名
     */
    public String FYYSXM;
    /**
     * 药品作用目的
     */
    public String YPZYMD;
    /**
     * 中药饮片处方
     */
    public String ZYYPCF;
    /**
     * 中药饮片剂数
     */
    public String ZYYPJS;
    /**
     * 中药煎煮法代码
     */
    public String JYDM;
    /**
     * 中药用药方法
     */
    public String ZYYYFF;

    /**
     * 皮试判别
     */
    public String SFPS;
    /**
     * 检查部位
     */
    public String JCBW;
    /**
     * 备注信息
     */
    public String BZ;
    /**
     * 处方药品金额
     */
    public String YPJE;
    /**
     * 修改标志
     */
    public String XGBZ;
    /**
     * 密级
     */
    public String MJ;
    /**
     * 预留1
     */
    public String YLYL1;
    /**
     * 预留2
     */
    public String YLYL2;

}
