package com.YomiOluwadara.conferencedemo.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.YomiOluwadara.conferencedemo.services.HomeService;

@Service
public class HomeServiceImpl implements HomeService {
    
    @Value("${yomi.app.version}")
    private String appVersion;

    @Override
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("version", appVersion);
        return status;
    }

    @Override
    public String getAppVersion() {
        return appVersion;
    }

    @Override
    public String welcomeMessage() {
        return "Welcome to the Conference Demo Application!";
    }

    @Override
    public String getUserType() {
        return "Guest";
    }

    @Override
    public String appVersion() {
        return appVersion;
    }
} 