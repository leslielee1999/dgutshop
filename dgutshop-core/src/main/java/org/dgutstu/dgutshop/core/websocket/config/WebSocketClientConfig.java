package org.dgutstu.dgutshop.core.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @Author: leesk
 * @Description: 配置websocket后台客户端
 * @Date: Create in 11:16 2021/1/3
 */
//@Slf4j
//@Component
public class WebSocketClientConfig {
//    @Bean
//    public WebSocketClient webSocketClient() {
//        try {
//            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://localhost:8083/admin/order/list"),new Draft_6455()) {
//                @Override
//                public void onOpen(ServerHandshake handshakedata) {
//                    log.info("[websocket] 连接成功");
//                }
//
//                @Override
//                public void onMessage(String message) {
//                    log.info("[websocket] 收到消息={}",message);
//
//                }
//
//                @Override
//                public void onClose(int code, String reason, boolean remote) {
//                    log.info("[websocket] 退出连接");
//                }
//
//                @Override
//                public void onError(Exception ex) {
//                    log.info("[websocket] 连接错误={}",ex.getMessage());
//                }
//            };
//            webSocketClient.connect();
//            return webSocketClient;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}