package com.demo.tio.study;

import com.demo.tio.study.processor.LayimMsgProcessorManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.tio.websocket.starter.EnableTioWebSocketServer;

@SpringBootApplication
@EnableTioWebSocketServer
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        LayimMsgProcessorManager.init();
        SpringApplication.run(DemoApplication.class, args);
    }



}
