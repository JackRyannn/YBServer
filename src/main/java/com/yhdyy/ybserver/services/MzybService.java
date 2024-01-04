package com.yhdyy.ybserver.services;

import com.google.gson.Gson;
import com.yhdyy.ybserver.bos.*;
import com.yhdyy.ybserver.client.NetService;
import com.yhdyy.ybserver.daos.mssql.mzybDao;
import com.yhdyy.ybserver.enums.UploadStatus;
import com.yhdyy.ybserver.utils.SerialGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MzybService {
//    上传状态 0-待处理 1-已发送 2-发送失败 3-入库成功 4-入库失败 5-作废

    // 循环上传次数上限
    public static final Integer LOOP_COUNT = 1000;
    // 单次上传条数
    public static final Integer UPLOAD_COUNT = 100;
    // 单次检查条数
    public static final Integer CHECK_COUNT = 1;
    public static final String INS_CODE = "060001";
    public static final String UPLOAD_OPERA = "院方";
    public static final String REG_SERVICE_TYPE = "TB_HIS_MZ_Reg";
    public static final String MEDICAL_RECORD_SERVICE_TYPE = "TB_YL_MZ_Medical_Record";
    public static final String PRESCRIPTION_DETAIL_SERVICE_TYPE = "TB_CIS_Prescription_Detail";
    public static final String YB = "_yb";
    public static final String CODE_SUCCESS = "100000";
    @Resource
    private mzybDao mzybDao;
    @Resource
    private NetService netService;

    // 创建 SimpleDateFormat 对象并指定日期格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    Gson gson = new Gson();

    @Scheduled(cron = "0 0 22 * * ?")
    private void regSchedule() {
        doUploadReg(UPLOAD_COUNT, UploadStatus.PENDING.getValue());
    }

    @Scheduled(cron = "0 10 22 * * ?")
    private void medicalSchedule() {
        doUploadMedicalRecord(UPLOAD_COUNT, UploadStatus.PENDING.getValue());
    }

    @Scheduled(cron = "0 20 22 * * ?")
    private void prescriptionSchedule() {
        doUploadPrescriptionDetail(UPLOAD_COUNT, UploadStatus.PENDING.getValue());
    }

    public void doUploadReg(Integer count, String status) {
        Date date = new Date();
        log.info("doUpload------Reg");
        try {
            for (int i = 0; i < LOOP_COUNT; i++) {
                List<RegBo> regBos = mzybDao.getRegBo(count, Arrays.asList(status));
                if (regBos.size() <= 0) {
                    log.debug("Reg------今日数据已上传完毕");
                    return;
                }
                doReg(regBos, date);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void doUploadMedicalRecord(Integer count, String status) {
        Date date = new Date();
        log.info("doUpload------MedicalRecord");
        try {
            for (int i = 0; i < LOOP_COUNT; i++) {
                List<MedicalRecordBo> medicalRecordBos = mzybDao.getMedicalRecordBo(count, Arrays.asList(status));
                if (medicalRecordBos.size() <= 0) {
                    log.debug("MedicalRecord------今日数据已上传完毕");
                    return;
                }
                doMedicalRecord(medicalRecordBos, date);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void doUploadPrescriptionDetail(Integer count, String status) {
        Date date = new Date();
        log.info("doUpload------PrescriptionDetail");
        try {
            for (int i = 0; i < LOOP_COUNT; i++) {
                List<PrescriptionDetailBo> prescriptionDetailBos = mzybDao.getPrescriptionDetailBo(count, Arrays.asList(status));
                if (prescriptionDetailBos.size() <= 0) {
                    log.debug("PrescriptionDetail------今日数据已上传完毕");
                    return;
                }
                doPrescriptionDetail(prescriptionDetailBos, date);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Scheduled(cron = "0 30 22 * * ?")
    public void checkSchedule() {
        log.debug("checkSchedule");
        doCheck(REG_SERVICE_TYPE);
        doCheck(MEDICAL_RECORD_SERVICE_TYPE);
        doCheck(PRESCRIPTION_DETAIL_SERVICE_TYPE);
    }

    public void doCheck(String service_type) {
        log.debug("doCheck------" + service_type);
        for (int i = 0; i < LOOP_COUNT; i++) {
            ArrayList<BaseBo> baseBos = new ArrayList<>();
            baseBos.addAll(mzybDao.getBaseBo(service_type + YB, CHECK_COUNT, Collections.singletonList(UploadStatus.UPLOAD_SUCCESS.getValue())));

            HashMap<String, BaseBo> baseMaps = new HashMap();
            for (BaseBo baseBo : baseBos) {
                baseMaps.put(baseBo.getUpload_serial(), baseBo);
            }
            log.debug("本次需要check的流水号数量为:" + baseMaps.size());

            if (baseBos.size() <= 0) {
                log.debug("已无需要check的数据");
                return;
            }

            for (BaseBo baseBo : baseMaps.values()) {
                CheckRequest request = new CheckRequest();
                request.setReqSnum(baseBo.getUpload_serial());
                request.setInsCode(INS_CODE);
                request.setServiceType(service_type);

                String jsonBody = gson.toJson(request);
                log.debug("doCheck------jsonBody:" + jsonBody);
                //5.post请求
                String ret = netService.check(jsonBody);
                log.debug("doCheck------ret:" + ret);
                CheckResponse response = gson.fromJson(ret, CheckResponse.class);
                if (CODE_SUCCESS.equals(response.getCode())) {
                    baseBo.setUpload_status(UploadStatus.SAVE_SUCCESS.getValue());
                } else {
                    log.debug("doCheck------SAVE_FAILURE------upload_serial:" + baseBo.getUpload_serial());
                    baseBo.setUpload_status(UploadStatus.SAVE_FAILURE.getValue());
                }
                if (mzybDao.updateBase(service_type + YB, baseBo) > 0) {
                    log.debug("doCheck------请求后更新数据库成功");
                }
            }
        }

    }

    //    @Scheduled(cron = "0 0/1 * * * ?")
    public void doRetry() {
        log.debug("doRetry");
    }

    private void doReg(List<RegBo> regBos, Date date) {
        log.debug("doReg");
        String time = sdf.format(date);
        BaseBo baseBo = new BaseBo();
        baseBo.setUpload_date(date);
        String serialNum = time + SerialGenerator.getSerialNum();
        baseBo.setUpload_opera(UPLOAD_OPERA);
        baseBo.setUpload_serial(serialNum);
        List<String> keys = regBos.stream().map(RegBo::getGHBM).collect(Collectors.toList());
        if (mzybDao.updateReg(keys, baseBo) > 0) {
            log.debug("doReg------请求前更新数据库成功");
        }
        UploadRequest requestBody = new UploadRequest();
        requestBody.setReqSnum(serialNum);
        requestBody.setReqTime(time);
        requestBody.setInsCode(INS_CODE);
        requestBody.setServiceType(REG_SERVICE_TYPE);
        requestBody.setSign("");
        requestBody.setData(regBos);
        String jsonBody = gson.toJson(requestBody);
//        log.debug("doReg------jsonBody:" + jsonBody);
        //5.post请求
        String ret = netService.upload(jsonBody);
        log.debug("doReg------ret:" + ret);

        UploadResponse response = gson.fromJson(ret, UploadResponse.class);
        baseBo.setResult_code(response.getCode());
        baseBo.setMessage(response.getMsg());
        if (CODE_SUCCESS.equals(response.getCode())) {
            baseBo.setUpload_status(UploadStatus.UPLOAD_SUCCESS.getValue());
        } else {
            log.debug("doReg------UPLOAD_FAILURE------jsonBody:" + jsonBody);
            baseBo.setUpload_status(UploadStatus.UPLOAD_FAILURE.getValue());
        }
        if (mzybDao.updateReg(keys, baseBo) > 0) {
            log.debug("doReg------请求后更新数据库成功");
        }
    }

    private void doMedicalRecord(List<MedicalRecordBo> medicalRecordBos, Date date) {
        log.debug("doMedicalRecord");
        String time = sdf.format(date);
        BaseBo baseBo = new BaseBo();
        baseBo.setUpload_date(date);
        String serialNum = time + SerialGenerator.getSerialNum();
        baseBo.setUpload_opera(UPLOAD_OPERA);
        baseBo.setUpload_serial(serialNum);
        List<String> keys = medicalRecordBos.stream().map(MedicalRecordBo::getJZLSH).collect(Collectors.toList());
        if (mzybDao.updateMedicalRecord(keys, baseBo) > 0) {
            log.debug("doMedicalRecord------请求前更新数据库成功");
        }
        UploadRequest requestBody = new UploadRequest();
        requestBody.setReqSnum(serialNum);
        requestBody.setReqTime(time);
        requestBody.setInsCode(INS_CODE);
        requestBody.setServiceType(MEDICAL_RECORD_SERVICE_TYPE);
        requestBody.setSign("");
        requestBody.setData(medicalRecordBos);
        String jsonBody = gson.toJson(requestBody);
//        log.debug("doMedicalRecord------jsonBody:" + jsonBody);
        //5.post请求
        String ret = netService.upload(jsonBody);
        log.debug("doMedicalRecord------ret:" + ret);

        UploadResponse response = gson.fromJson(ret, UploadResponse.class);
        baseBo.setResult_code(response.getCode());
        baseBo.setMessage(response.getMsg());
        if (CODE_SUCCESS.equals(response.getCode())) {
            baseBo.setUpload_status(UploadStatus.UPLOAD_SUCCESS.getValue());
        } else {
            log.debug("doMedicalRecord------UPLOAD_FAILURE------jsonBody:" + jsonBody);
            baseBo.setUpload_status(UploadStatus.UPLOAD_FAILURE.getValue());
        }
        if (mzybDao.updateMedicalRecord(keys, baseBo) > 0) {
            log.debug("doMedicalRecord------请求后更新数据库成功");
        }
    }

    private void doPrescriptionDetail(List<PrescriptionDetailBo> prescriptionDetailBos, Date date) {
        log.debug("doPrescriptionDetail");
        String time = sdf.format(date);
        BaseBo baseBo = new BaseBo();
        baseBo.setUpload_date(date);
        String serialNum = time + SerialGenerator.getSerialNum();
        baseBo.setUpload_opera(UPLOAD_OPERA);
        baseBo.setUpload_serial(serialNum);
        List<String> keys = prescriptionDetailBos.stream().map((PrescriptionDetailBo bo) -> bo.getCYH() + bo.getCFMXH()).collect(Collectors.toList());
        if (mzybDao.updatePrescriptionDetail(keys, baseBo) > 0) {
            log.debug("doPrescriptionDetail------请求前更新数据库成功");
        }
        UploadRequest requestBody = new UploadRequest();
        requestBody.setReqSnum(serialNum);
        requestBody.setReqTime(time);
        requestBody.setInsCode(INS_CODE);
        requestBody.setServiceType(PRESCRIPTION_DETAIL_SERVICE_TYPE);
        requestBody.setSign("");
        requestBody.setData(prescriptionDetailBos);
        String jsonBody = gson.toJson(requestBody);
//        log.debug("doPrescriptionDetail------jsonBody:" + jsonBody);
        //5.post请求
        String ret = netService.upload(jsonBody);
        log.debug("doPrescriptionDetail------ret:" + ret);

        UploadResponse response = gson.fromJson(ret, UploadResponse.class);
        baseBo.setResult_code(response.getCode());
        baseBo.setMessage(response.getMsg());
        if (CODE_SUCCESS.equals(response.getCode())) {
            baseBo.setUpload_status(UploadStatus.UPLOAD_SUCCESS.getValue());
        } else {
            log.debug("doPrescriptionDetail------UPLOAD_FAILURE------jsonBody:" + jsonBody);
            baseBo.setUpload_status(UploadStatus.UPLOAD_FAILURE.getValue());
        }
        if (mzybDao.updatePrescriptionDetail(keys, baseBo) > 0) {
            log.debug("doPrescriptionDetail------请求后更新数据库成功");
        }
    }


}
