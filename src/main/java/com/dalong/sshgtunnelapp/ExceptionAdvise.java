package com.dalong.sshgtunnelapp;

import com.jcraft.jsch.JSchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvise {

    @ExceptionHandler(JSchException.class)
    public Object message(JSchException jSchException) {
        Map msg = new HashMap();
        msg.put("result", "error");
        msg.put("msg", jSchException.getMessage());
        return msg;
    }
}
