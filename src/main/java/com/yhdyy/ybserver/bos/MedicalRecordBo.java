package com.yhdyy.ybserver.bos;

import lombok.Data;

import java.util.Date;

@Data
// 详见烟台市医保数据资源中心数据接入标准（业务内容部分）V1.24
public class MedicalRecordBo extends BaseBo{
    /**
     * 医疗机构代码
     */
    public String YLJGDM;
    /**
     * 就诊流水号
     */
    public String JZLSH;
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
     * 患者姓名
     */
    public String HZXM;
    /**
     * 病人年龄
     */
    public String BRNL;
    /**
     * 就诊类型
     */
    public String JZLX;
    /**
     * 保险类别
     */
    public String BXLB;
    /**
     * 特需标志
     */
    public String TXBZ;
    /**
     * 就医地点
     */
    public String JYDDFL;
    /**
     * 就诊科室编码
     */
    public String JZKSBM;
    /**
     * 就诊科室名称
     */
    public String JZKSMC;
    /**
     * 门诊就诊日期
     */
    public String JZKSRQ;
    /**
     * 过敏史标志
     */
    public String GMSBZ;
    /**
     * 过敏史
     */
    public String GMS;
    /**
     * 初诊标志代码
     */
    public String CZBZ;
    /**
     * 现病史
     */
    public String XBS;
    /**
     * 既往史
     */
    public String JWS;
    /**
     * 体格检查
     */
    public String TGJC;
    /**
     * 中医四诊观察结果
     */
    public String ZYSZGCJG;
    /**
     * 辅助检查项目
     */
    public String FZJCXM;
    /**
     * 辅助检查结果
     */
    public String FZJCJG;
    /**
     * 主诊医生工号
     */
    public String ZZYSGH;
    /**
     * 主诊医生姓名
     */
    public String ZZYSXM;
    /**
     * 门诊诊断编码
     */
    public String JZZDBM;
    /**
     * 编码类型
     */
    public String BMLX;
    /**
     * 门诊呢诊断说明
     */
    public String JZZDSM;
    /**
     * 主诉
     */
    public String ZS;
    /**
     * 症状描述
     */
    public String ZZMS;
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
