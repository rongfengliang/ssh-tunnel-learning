package com.dalong.sshgtunnelapp;

import lombok.Data;

@Data
public class RequestBody {
    private String host;
    private String user;
    private String password;
    private int port;
    private int tunnelLocalPort;
    private String tunnelRemoteHost;
    private int tunnelRemotePort;
}
