package com.umax.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    @Autowired
    Tracer tracer;
    private static final Logger log = LoggerFactory.getLogger(FooService.class);

    public void printLog() {
        Span newSpan = tracer.nextSpan().name("someOperation").start();
        try (org.springframework.cloud.sleuth.Tracer.SpanInScope spanInScope = tracer.withSpan(newSpan)) {
            // Lógica de negocio dentro del span
           log.info("testing ");
        } catch (Exception e) {
            // Registrar error en el span
            newSpan.error(e);
        } finally {
            // Finalizar el span
            newSpan.end();
        }

    }
}
