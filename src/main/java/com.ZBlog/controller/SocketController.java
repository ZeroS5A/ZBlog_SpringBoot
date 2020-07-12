package com.ZBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/socketTest")
public class SocketController {

    //客户端信息、在线人数、连接session
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private static Integer onlineCount = 0;
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        addOnlineCount();
        System.out.println("有新的客户端连接了,当前在线人数："+ onlineCount);
        //将新用户存入在线的组
       this.session = session ;
        System.out.println(session.toString());
        clients.put(session.getId(), session);
    }

    @OnClose
    public void onClose() {
        subOnlineCount();
        System.out.println("有用户断开了,当前在线人数："+ onlineCount);

        //将掉线的用户移除在线的组里
        clients.remove(session.getId());
    }


    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("服务端收到客户端发来的消息:"+message);
        this.sendAll("receive:"+message);
    }


    //单向发送信息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //群发消息
    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        SocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        SocketController.onlineCount--;
    }


}
