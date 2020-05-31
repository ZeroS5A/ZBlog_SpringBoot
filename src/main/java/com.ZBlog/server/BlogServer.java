package com.ZBlog.server;

import com.ZBlog.bean.TBlog;
import com.ZBlog.bean.TLike;
import com.ZBlog.commom.Result;

import java.util.Map;

public interface BlogServer {
    public Result getBlogList(Map map);

    public Result getBlog(Map map,String userId);

    public Result getHomeClass();

    public Result insertLike(TLike tLike);

    public Result deleteLike(TLike tLike);

    public Result insertBlog(TBlog tBlog);

    public Result getBlogListByUserId(String userId);

    public Result updateBlog(TBlog tBlog);

    public Result deleteBlog(Integer blogId,Integer userId);

    public Result getComment(Map<String,String> map,String userId);

    public Result getCommentChild(Integer dialogId,String userId);
}
