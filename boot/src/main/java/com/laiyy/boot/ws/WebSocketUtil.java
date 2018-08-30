package com.laiyy.boot.ws;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author laiyy
 * @date 2018/8/30 15:33
 * @description
 */
public class WebSocketUtil {

    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();


    public static void addSession(String userNick, Session session) {
        // putIfAbsent，添加键值对的时候，先判断该键值对是否存在在
        // 不存在：新增，并返回 null
        // 存在：不覆盖，并返回以存在的值
        ONLINE_SESSION.put(userNick, session);
    }

    public static void removeSession(String userNick) {
        ONLINE_SESSION.remove(userNick);
    }

    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
        // getAsyncRemote 异步发送
        // getBasicRemote 同步发送
        RemoteEndpoint.Async async = session.getAsyncRemote();
        async.sendText(message);
    }

    public static void sendMessageAll(String message) {
        ONLINE_SESSION.forEach((sessionId, session) -> {
            sendMessage(session, message);
        });
    }

}
