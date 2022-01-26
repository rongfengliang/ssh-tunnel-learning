package com.dalong.sshgtunnelapp;

import com.jcraft.jsch.JSchException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ServiceManager {
    private  final SessionManage sessionManage;
    public  ServiceManager(SessionManage sessionManage){
        this.sessionManage=sessionManage;
    }
    @PostMapping(value = "/conf")
    public Object conf(@RequestBody com.dalong.sshgtunnelapp.RequestBody requestBody) throws JSchException {
        sessionManage.getSession(requestBody);
        return  "ok";
    }

    @GetMapping(value = "/conflist")
    public Object conf() {
        return  sessionManage.tunnelLists();
    }
}
