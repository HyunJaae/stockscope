package me.hyeonjae.stockscope.common.interceptor;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        
        MDC.put("requestId", UUID.randomUUID().toString());
        
        log.info("""
                REQUEST:
                - Method: {}
                - URI: {}
                - Client IP: {}
                - User Agent: {}
                - Handler: {}
                """,
                method, requestURI, remoteAddr, userAgent, handler.getClass().getSimpleName()
        );
        
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();
        
        long startTime = (long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;
        
        log.info("""
                RESPONSE:
                - Method: {}
                - URI: {}
                - Status: {}
                - Duration: {}ms
                - Request ID: {}
                """,
                method, requestURI, status, duration, MDC.get("requestId")
        );
        
        if (ex != null) {
            log.error("Exception occurred while processing request", ex);
        }
        
        MDC.clear();
    }
}
