package com.ZBlog.server;

import com.ZBlog.bean.TBlog;
import com.ZBlog.bean.TComment;
import com.ZBlog.bean.TTags;
import com.ZBlog.bean.TUser;
import com.ZBlog.commom.Result;
import org.apache.ibatis.annotations.Insert;

import java.util.Map;

public interface UserServer {
    public Result userLogin(String userName, String password);

    public Result userRegister(Map<String, String> map);

    public Result getUserData(Integer userId);

    public Result updateUserData(TUser tUser,String userName);

    public Result updatePassWd(Integer userId, Map<String,String> map);

    public Result updateEmail(Integer userId, Map<String,String> map);

    public Result insertTags(TTags tTags);

    public Result getTagsList(String userName,Integer classId);

    public Result getClassificationList();

    public Result addComment(TComment tComment);

    public Result deleteComment(TComment tComment);

    public Result changeAttention(Integer userId, Map map);

    public Result getUserRelationShip(Integer userId);
}
