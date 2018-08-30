package com.laiyy.boot.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author laiyy
 * @date 2018/8/30 15:26
 * @description
 *
 * 使用 @ServerEndpoint，指定一个地址，表示定义一个 WebSocket Server 端
 */
@Component
@ServerEndpoint(value = "/my-chat/{nick}")
@Slf4j
public class WebSocketController {
    @OnOpen
    public void onOpen(@PathParam(value = "nick") String userNick, Session session) {
        String message = "有新用户：【" + userNick + "】 加入聊天室";
        log.info(message);
        WebSocketUtil.addSession(userNick, session);
        // 向所有在线用户通知
        WebSocketUtil.sendMessageAll(message);
    }
    @OnClose
    public void onClose(@PathParam("nick") String userNick, Session session) {
        String message = "用户：【" + userNick + "】 退出聊天室";
        log.info(message);
        WebSocketUtil.removeSession(userNick);
        WebSocketUtil.sendMessageAll(message);
    }
    @OnMessage
    public void onMessage(@PathParam("nick")String userNick, String message) {
        String info = "用户：【" + userNick + "】：" + message;
        log.info(info);
        WebSocketUtil.sendMessageAll(info);
    }
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("出现异常：" + throwable.getLocalizedMessage());
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }
}
