package com.yhdyy.ybserver.bos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CheckResponse {
    private String code;
    private String msg;

}
