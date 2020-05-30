package com.controller;

import com.commom.Result;
import com.server.BlogServer;
import com.util.TokenUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/blog")
public class BlogController extends ExceptionController{

    @Autowired
    BlogServer blogServer;

    @Autowired
    TokenUtil tokenUtil;

    @RequestMapping("/getBlogList")
    public Result getBlogList(@RequestBody Map<String,String> map){
        map.put("userId","%");
        return blogServer.getBlogList(map);
    }

    @RequestMapping("/getBlog")
    public Result getBlog(@RequestBody Map<String,String> map){
        String userId;
        if(map.get("token") ==null)
            userId=null;
        else
            userId=tokenUtil.getTokenData(map.get("token")).get("userId");
        return blogServer.getBlog(map,userId);
    }

    @RequestMapping("/getHomeClass")
    public Result getHomeClass(){
        return blogServer.getHomeClass();
    }

    @RequestMapping("/getComment")
    public Result getComment(@RequestBody Map<String,String> map){
        String userId;
        if(map.get("token") ==null)
            userId=null;
        else
            userId=tokenUtil.getTokenData(map.get("token")).get("userId");
        return blogServer.getComment(map,userId);
    }

    @RequestMapping("/getCommentChild")
    public Result getCommentChild(@RequestBody Map<String,String> map){
        String userId;
        if(map.get("token") ==null)
            userId=null;
        else
            userId=tokenUtil.getTokenData(map.get("token")).get("userId");
        return blogServer.getCommentChild(Integer.valueOf(map.get("dialogId")),userId);
    }
}
