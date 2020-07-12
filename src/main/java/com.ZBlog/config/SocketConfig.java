package com.ZBlog.config;

import com.ZBlog.bean.SocketUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.security.Principal;
import java.util.Map;
import com.ZBlog.util.TokenUtil;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.ZBlog.bean.SocketUser;

@Configuration

@EnableWebSocketMessageBroker
public class SocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Autowired
    private TokenUtil tokenUtil;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        //分别是 连接地址 跨域头 以sockJs方式连接
        //registry.addEndpoint("/socketConnect").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/socketConnect").addInterceptors(new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                           WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {

                ServletServerHttpRequest req = (ServletServerHttpRequest) serverHttpRequest;
                //获取token认证
                String token = req.getServletRequest().getParameter("token");
                System.out.println(token);
                Principal user = authenticate(token);
                //解析token获取用户信息
                if(user == null){   //如果token认证失败user为null，返回false拒绝握手
                    return false;
                }
                //保存认证用户
                attributes.put("user", user);
                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

            }
        }).setHandshakeHandler(new DefaultHandshakeHandler(){
            @Override
            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                //设置认证用户
                return (Principal)attributes.get("user");
            }
        })
        .setAllowedOrigins("*") //允许跨域
        .withSockJS();  //指定使用SockJS协议
    }

    @Override
    //配置消息代理(Message Broker)
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //点对点应配置一个/user消息代理，广播式应配置一个/topic消息代理,群发（mass），单独聊天（alone）
        registry.enableSimpleBroker("/topic","/user","/mass","/alone");
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user");
    }

    private Principal authenticate(String token){
        SocketUser socketUser = new SocketUser();
        //TODO 解析token并获取认证用户信息
        if (tokenUtil.goodToken(token)) {
            socketUser.setName(tokenUtil.getTokenData(token).get("userName"));
            return socketUser;
        }
        else {
            return null;
        }
    }
}
