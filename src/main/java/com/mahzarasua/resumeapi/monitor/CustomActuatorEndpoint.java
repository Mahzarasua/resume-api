package com.mahzarasua.resumeapi.monitor;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Endpoint(id = "custom")
@Component
public class CustomActuatorEndpoint {

    @ReadOperation
    public Object customEndpoint(){
        HashMap map = new HashMap();
        map.put("Status", "Up");
        return map;
    }
}
