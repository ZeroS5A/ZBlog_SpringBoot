package com.server;

import com.bean.TBlog;
import com.bean.TComment;
import com.bean.TTags;
import com.bean.TUser;
import com.commom.Result;

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
