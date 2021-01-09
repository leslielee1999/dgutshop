package org.dgutstu.dgutshop.core.websocket.service;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 11:39 2021/1/3
 */
public interface WebSocketService {
    /**
     * 群发
     * @param message
     */
    void groupSending(String message);

    /**
     * 指定发送
     * @param name
     * @param message
     */
    void appointSending(String name,String message);

}
