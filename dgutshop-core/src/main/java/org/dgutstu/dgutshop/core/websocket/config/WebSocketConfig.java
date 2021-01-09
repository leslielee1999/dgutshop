package org.dgutstu.dgutshop.core.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: leesk
 * @Description: websocket配置类
 *
 *              开启WebSocket支持
 *
 * @Date: Create in 0:12 2021/1/3
 */
//@Configuration
public class WebSocketConfig {
    /**
     * 这个bean会自动注册使用@ServerEndPoint注解声明的websocket endpoint
     * @return
     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter(){
//        return new ServerEndpointExporter();
//    }
}
