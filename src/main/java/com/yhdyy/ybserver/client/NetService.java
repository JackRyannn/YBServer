package com.yhdyy.ybserver.client;

import com.dtflys.forest.annotation.*;

import java.util.Map;

public interface NetService {
    @Post("http://10.79.1.45:9070/process/v1/invoke")
    String upload(@JSONBody String body);

    @Post("http://10.79.1.45:9070/process/v1/result")
    String check(@JSONBody String body);

}
