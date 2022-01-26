package com.dalong.sshgtunnelapp;

import com.jcraft.jsch.Session;
import lombok.Data;

@Data
public class SessionWrapper {
    private Session session;
    private Boolean states;
}
