package com.ZBlog.controller;

import com.ZBlog.bean.SocketMessage;
import com.ZBlog.commom.Result;
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

    //响应前端发送请求地址"/hello"
    @MessageMapping("/hello")
    //sendTo是订阅地址
    @SendTo("/topic/hello")
    public Result helloEcho(SocketMessage message) throws Exception {
        System.out.println(message.toString());
        Result result = new Result();
        result.setData(message);
        return result;
    }
    @MessageMapping("/msg")
    @SendToUser("/alone/hi")
    public Result helloToUser(SocketMessage message, Principal fromUser) throws Exception {
        System.out.println(message.toString());
        message.setFromUser(fromUser.getName());
        //发送到指定用户
        simpMessageSendingOperations.convertAndSendToUser(message.getToUser(), "/alone/hi", message.getMessage());
        return null;
    }
    @RequestMapping(path = "/socketTest/send", method = RequestMethod.GET)
    public Result sendTest(@RequestParam String user, @RequestParam String mess) {
        simpMessageSendingOperations.convertAndSendToUser(user, "/alone/hi", mess);
        return null;
    }
}
