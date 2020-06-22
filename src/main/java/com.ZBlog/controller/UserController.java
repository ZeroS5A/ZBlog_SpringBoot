package com.ZBlog.controller;

import com.ZBlog.bean.*;
import com.ZBlog.commom.Result;
import com.ZBlog.server.BlogServer;
import com.ZBlog.server.UserServer;
import com.util.TokenUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends ExceptionController{

    @Autowired
    UserServer userServer;
    @Autowired
    BlogServer blogServer;
    @Autowired
    TokenUtil tokenUtil;

    /**
     * 用户登录
     * @param tUser
     * @return
     */
    @RequestMapping("/userLogin")
    public Result userLogin(@RequestBody TUser tUser){
        return userServer.userLogin(tUser.getUserName(),tUser.getPassword());
    }

    @RequestMapping("/userRegister")
    public Result userRegister(@RequestBody Map<String, String> map){
        return userServer.userRegister(map);
    }

    /**
     * 获取用户信息
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/getUserData")
    public Result getUserData(@RequestHeader("Authorization") String token){
        return userServer.getUserData(Integer.parseInt(tokenUtil.getTokenData(token).get("userId")));
    }

    /**
     * 更新用户信息
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/updateUserData")
    public Result updateUserData(@RequestHeader("Authorization") String token,@RequestBody TUser tUser){
        tUser.setUserId(Integer.valueOf(tokenUtil.getTokenData(token).get("userId")));
        String userName=tokenUtil.getTokenData(token).get("userName");
        return userServer.updateUserData(tUser,userName);
    }

    /**
     * 更新密码
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/updatePassWd")
    public Result updatePassWd(@RequestHeader("Authorization") String token,@RequestBody Map<String,String> map){
        Integer userId = Integer.valueOf(tokenUtil.getTokenData(token).get("userId"));
        map.put("userName",tokenUtil.getTokenData(token).get("userName"));
        return userServer.updatePassWd(userId,map);
    }

    /**
     * 更新邮箱
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/updateEmail")
    public Result updateEmail(@RequestHeader("Authorization") String token,@RequestBody Map<String,String> map){
        Integer userId = Integer.valueOf(tokenUtil.getTokenData(token).get("userId"));
        return userServer.updateEmail(userId,map);
    }

    /**
     * 插入和更新博客
     **/
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/insertBlog")
    public Result insertBlog(@RequestHeader("Authorization") String token, @RequestBody TBlog tBlog){
        tBlog.setUserId(Integer.parseInt(tokenUtil.getTokenData(token).get("userId")));
        //当博客Id不空，且用户名正确时
        if(tBlog.getBlogId()!=null &&  tokenUtil.getTokenData(token).get("userName").equals(tBlog.getUserName())){
            return blogServer.updateBlog(tBlog);
        }
        tBlog.setBlogDate(new Date());
        return blogServer.insertBlog(tBlog);
    }

    /**
     * 插入标签（暂时禁用）
     * @param token
     * @param tTags
     * @return
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/insertTags")
    public Result insertTags(@RequestHeader("Authorization") String token, @RequestBody TTags tTags){
        tTags.setCreateUser(tokenUtil.getTokenData(token).get("userId"));
        return userServer.insertTags(tTags);
    }


    /**
     * 获取标签列表
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/getTagsList")
    public Result getTagsList(@RequestHeader("Authorization") String token, @RequestBody TClassification tClassification){
        return userServer.getTagsList(tokenUtil.getTokenData(token).get("userId"),tClassification.getClassId());
    }


    /**
     * 获取分类
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/getClassificationList")
    public Result getClassification(){
        return userServer.getClassificationList();
    }


    /**
     * 根据用户Id获取博客
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/getBlogListByUserId")
    public Result getBlogListByUserId(@RequestHeader("Authorization") String token,@RequestBody Map<String,String> map){
        return blogServer.getBlogListByUserId(Integer.valueOf(tokenUtil.getTokenData(token).get("userId")),map);
    }

    /**
     * 删除博客
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/deleteBlog")
    public Result deleteBlog(@RequestHeader("Authorization") String token,@RequestBody Map<String,Integer> map){
        return blogServer.deleteBlog(map.get("blogId"),Integer.valueOf(tokenUtil.getTokenData(token).get("userId")));
    }

    /**
     * 点赞
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/addLike")
    public Result addLike(@RequestHeader("Authorization") String token, @RequestBody TLike tLike){
        tLike.setUserId(Integer.parseInt(tokenUtil.getTokenData(token).get("userId")));
        return blogServer.insertLike(tLike);
    }

    /**
     * 取消点赞
     * @return
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/deleteLike")
    public Result deleteLike(@RequestHeader("Authorization") String token,@RequestBody TLike tLike){
        tLike.setUserId(Integer.parseInt(tokenUtil.getTokenData(token).get("userId")));
        return blogServer.deleteLike(tLike);
    }

    /**
     * 添加评论
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/addComment")
    public Result addComment(@RequestHeader("Authorization") String token,@RequestBody TComment tComment){
        tComment.setUserId(Integer.parseInt(tokenUtil.getTokenData(token).get("userId")));
        return userServer.addComment(tComment);
    }

    /**
     * 删除评论
     */
    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/deleteComment")
    public Result deleteComment(@RequestHeader("Authorization") String token,@RequestBody TComment tComment){
        tComment.setUserId(Integer.parseInt(tokenUtil.getTokenData(token).get("userId")));
        return userServer.deleteComment(tComment);
    }
}
