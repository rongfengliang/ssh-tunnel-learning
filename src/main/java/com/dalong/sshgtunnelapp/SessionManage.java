package com.dalong.sshgtunnelapp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class SessionManage implements AutoCloseable {

    private Logger logger = LoggerFactory.getLogger(SessionManage.class);
    private final static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    public Session getSession(RequestBody requestBody) throws JSchException {
        String key = String.format("%s-%d<=>%s-%d", requestBody.getHost(), requestBody.getPort(), requestBody.getTunnelRemoteHost(), requestBody.getTunnelRemotePort());
        if (Objects.isNull(sessionMap.get(key))) {
            JSch jsch = new JSch();
            Session session = jsch.getSession(requestBody.getUser(), requestBody.getHost(), requestBody.getPort());
            session.setPassword(requestBody.getPassword());
            LocalUserInfo lui = new LocalUserInfo();
            session.setUserInfo(lui);
            session.connect();
            session.setPortForwardingL(requestBody.getTunnelLocalPort(), requestBody.getTunnelRemoteHost(), requestBody.getTunnelRemotePort());
            logger.info("session info: {}", key);
            sessionMap.put(key, session);
            return session;
        }
        return sessionMap.get(key);
    }

    public List<String> tunnelLists() {
        return sessionMap.keySet().stream().collect(Collectors.toList());
    }

    public Session getSession(String host, String user, String password, int port, String tunnelRemoteHost, int tunnelLocalPort, int tunnelRemotePort) throws JSchException {
        String key = String.format("%s-%d<=>%s-%d", host, port, tunnelRemoteHost, tunnelRemotePort);
        if (Objects.isNull(sessionMap.get(key))) {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            LocalUserInfo lui = new LocalUserInfo();
            session.setUserInfo(lui);
            session.connect();
            session.setPortForwardingL(tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);
            sessionMap.put(key, session);
            return session;
        }
        return sessionMap.get(key);
    }

    @Override
    public void close() throws Exception {
        for (Map.Entry<String, Session> sessionEntry : sessionMap.entrySet()) {
            sessionEntry.getValue().disconnect();
        }
    }
}
