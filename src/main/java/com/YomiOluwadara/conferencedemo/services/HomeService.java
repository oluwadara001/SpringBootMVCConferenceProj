package com.YomiOluwadara.conferencedemo.services;

import java.util.Map;

public interface HomeService {
    Map<String, Object> getStatus();
    String getAppVersion();
    String welcomeMessage();
    String getUserType();
    String appVersion();
} 