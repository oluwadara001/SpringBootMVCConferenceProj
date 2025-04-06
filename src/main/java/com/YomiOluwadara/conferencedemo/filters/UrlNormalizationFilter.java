package com.YomiOluwadara.conferencedemo.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UrlNormalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestURI = httpRequest.getRequestURI();
        
        // Check if the URI contains double slashes
        if (requestURI.contains("//")) {
            // Normalize the URI by replacing multiple slashes with a single slash
            String normalizedURI = requestURI.replaceAll("/+", "/");
            
            // If the normalized URI is different from the original, redirect
            if (!normalizedURI.equals(requestURI)) {
                String queryString = httpRequest.getQueryString();
                String redirectURI = normalizedURI + (queryString != null ? "?" + queryString : "");
                
                httpResponse.sendRedirect(redirectURI);
                return;
            }
        }
        
        chain.doFilter(request, response);
    }
} 