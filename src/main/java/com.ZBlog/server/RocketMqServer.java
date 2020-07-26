package com.ZBlog.server;

import com.ZBlog.commom.Result;

public interface RocketMqServer {

    public Result sendMessage(String Message) throws Exception;
}
