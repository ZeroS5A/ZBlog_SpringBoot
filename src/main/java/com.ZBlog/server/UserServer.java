package com.ZBlog.server;

import com.ZBlog.bean.TBlog;
import com.ZBlog.bean.TComment;
import com.ZBlog.bean.TTags;
import com.ZBlog.bean.TUser;
import com.ZBlog.commom.Result;

public interface UserServer {
    public Result userLogin(String userName, String password);

    public Result getUserData(Integer userId);

    public Result updateUserData(TUser tUser,String userName);

    public Result insertTags(TTags tTags);

    public Result getTagsList(String userName,Integer classId);

    public Result getClassificationList();

    public Result addComment(TComment tComment);

    public Result deleteComment(TComment tComment);
}
