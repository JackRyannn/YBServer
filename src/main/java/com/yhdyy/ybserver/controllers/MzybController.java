package com.yhdyy.ybserver.controllers;

import com.yhdyy.ybserver.enums.UploadStatus;
import com.yhdyy.ybserver.services.MzybService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class MzybController {
    @Resource
    private MzybService mzybService;

    @RequestMapping(value = "/uploadReg", method = RequestMethod.GET)
    public void doUploadReg(Integer count, String status) {
        System.out.println("doUpload------Reg,count=" + count + " status=" + status);
        mzybService.doUploadReg(count, status);
    }

    @RequestMapping(value = "/uploadMedicalRecord", method = RequestMethod.GET)
    public void doUploadMedicalRecord(Integer count, String status) {
        System.out.println("doUpload------MedicalRecord,count=" + count + " status=" + status);
        mzybService.doUploadMedicalRecord(count, status);
    }

    @RequestMapping(value = "/uploadPrescriptionDetail", method = RequestMethod.GET)
    public void doUploadPrescriptionDetail(Integer count, String status) {
        System.out.println("doUpload------PrescriptionDetail,count=" + count + " status=" + status);
        mzybService.doUploadPrescriptionDetail(count, status);
    }

    @RequestMapping(value = "/doCheck", method = RequestMethod.GET)
    public void doCheck() {
        System.out.println("doCheck");
        mzybService.checkSchedule();
    }

    @RequestMapping(value = "/doRetry", method = RequestMethod.GET)
    public void doRetry() {
        System.out.println("doRetry");
        mzybService.doRetry();
    }
}
