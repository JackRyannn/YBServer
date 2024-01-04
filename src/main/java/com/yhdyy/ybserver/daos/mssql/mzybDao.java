package com.yhdyy.ybserver.daos.mssql;

import com.yhdyy.ybserver.bos.BaseBo;
import com.yhdyy.ybserver.bos.MedicalRecordBo;
import com.yhdyy.ybserver.bos.PrescriptionDetailBo;
import com.yhdyy.ybserver.bos.RegBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface mzybDao {
    List<BaseBo> getBaseBo(@Param("table") String table, @Param("count") Integer count, @Param("upload_status") List<String> upload_status);

    List<RegBo> getRegBo(@Param("count") Integer count, @Param("upload_status") List<String> upload_status);

    List<RegBo> getRegBoBySerial(@Param("start") String start, @Param("end") String end);

    int updateReg(@Param("keys") List<String> keys, @Param("baseBo") BaseBo baseBo);

    int updateBase(@Param("table") String table, @Param("baseBo") BaseBo baseBo);


    List<MedicalRecordBo> getMedicalRecordBo(@Param("count") Integer count, @Param("upload_status") List<String> upload_status);

    List<MedicalRecordBo> getMedicalRecordBoBySerial(@Param("start") String start, @Param("end") String end);

    int updateMedicalRecord(@Param("keys") List<String> keys, @Param("baseBo") BaseBo baseBo);

    List<PrescriptionDetailBo> getPrescriptionDetailBo(@Param("count") Integer count, @Param("upload_status") List<String> upload_status);

    List<PrescriptionDetailBo> getPrescriptionDetailBoBySerial(@Param("start") String start, @Param("end") String end);

    int updatePrescriptionDetail(@Param("keys") List<String> keys, @Param("baseBo") BaseBo baseBo);

}