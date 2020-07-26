package com.ZBlog.controller;

import com.ZBlog.bean.SocketMessage;
import com.ZBlog.commom.Result;
import com.ZBlog.server.RocketMqServer;
import com.ZBlog.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SockJsController {
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;
//    @Autowired
//    private RocketMqServer rocketMqServer;
    @Autowired
    private UserServer userServer;

    //响应前端发送请求地址"/sendPub"
    @MessageMapping("/sendPub")
    //sendTo是订阅地址
    @SendTo("/topic/public")
    public Result helloEcho(SocketMessage message, Principal fromUser) throws Exception {
        Result result = new Result();
        //设置发送者名
        message.setFromUserName(fromUser.getName());
        result.setData(message);
//        rocketMqServer.sendMessage(JSONObject.toJSON(message).toString());
        return result;
    }
    @MessageMapping("/sendUsr")
    @SendToUser("/alone/hi")
    public Result helloToUser(SocketMessage message, Principal fromUser) throws Exception {
        System.out.println(message.toString());
        Result result = new Result();
        message.setFromUserName(fromUser.getName());
        result.setData(message);
        //发送到指定用户
        simpMessageSendingOperations.convertAndSendToUser(message.getToUserName(), "/alone/hi", result);
        return result;
    }
    @RequestMapping(path = "/socketTest/send", method = RequestMethod.GET)
    public Result sendTest(@RequestParam String user, @RequestParam String mess) {
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setMessage(mess);
        simpMessageSendingOperations.convertAndSendToUser(user, "/alone/hi", socketMessage);
        return null;
    }
}
