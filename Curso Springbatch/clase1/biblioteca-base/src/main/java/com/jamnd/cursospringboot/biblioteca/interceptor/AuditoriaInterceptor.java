package com.jamnd.cursospringboot.biblioteca.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class AuditoriaInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuditoriaInterceptor.class);
    private static final String ATRIBUTO_INICIO = "auditoria.inicioMs";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(ATRIBUTO_INICIO, System.currentTimeMillis());
        String usuario = request.getHeader("X-Usuario");
        if (usuario == null || usuario.isBlank()) {
            usuario = "anonimo";
        }

        log.info("[AUDITORIA][INICIO] metodo={} uri={} ip={} usuario={} timestamp={}",
                request.getMethod(),
                request.getRequestURI(),
                request.getRemoteAddr(),
                usuario,
                LocalDateTime.now());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        Object inicio = request.getAttribute(ATRIBUTO_INICIO);
        long duracionMs = 0;
        if (inicio instanceof Long inicioMs) {
            duracionMs = System.currentTimeMillis() - inicioMs;
        }

        log.info("[AUDITORIA][FIN] metodo={} uri={} status={} duracionMs={} error={}",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                duracionMs,
                ex != null ? ex.getClass().getSimpleName() : "ninguno");
    }
}
