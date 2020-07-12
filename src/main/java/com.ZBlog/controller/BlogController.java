package com.ZBlog.controller;

import com.ZBlog.commom.Result;
import com.ZBlog.server.BlogServer;
import com.ZBlog.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/blog")
public class BlogController extends ExceptionController {

    @Autowired
    BlogServer blogServer;
    @Autowired
    TokenUtil tokenUtil;

    @RequestMapping("/getBlogList")
    public Result getBlogList(@RequestBody Map<String,String> map){
        return blogServer.getBlogList(map);
    }

    @RequestMapping("/getBlog")
    public Result getBlog(@RequestBody Map<String,String> map){
        Integer userId;
        if(map.get("token") ==null)
            userId=null;
        else
            userId = Integer.valueOf(tokenUtil.getTokenData(map.get("token")).get("userId"));
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

    /**
     * 获取邮箱验证码
     */
    @RequestMapping("/getMailCode")
    public Result getMailCode(@RequestBody Map<String,String> map){
        return blogServer.getMailCode(map.get("email"));
    }
}
