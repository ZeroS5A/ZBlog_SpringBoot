package com.server.impl;

import com.bean.TBlog;
import com.bean.TClassification;
import com.bean.TComment;
import com.bean.TLike;
import com.commom.Result;
import com.commom.ResultStatus;
import com.dao.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.BlogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("BlogServer")
public class BlogServerImpl implements BlogServer {

    @Autowired
    BlogDao blogDao;
    @Autowired
    BlogTagsDao blogTagsDao;
    @Autowired
    TagsDao tagsDao;
    @Autowired
    CommentLikeDao commentLikeDao;
    @Autowired
    BlogLikeDao blogLikeDao;
    @Autowired
    CommentDao commentDao;

    //获取博客列表
    @Override
    public Result getBlogList(Map map) {
        Result result =new Result();
        try{
            PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),15);
        }catch (Exception e){
            System.out.println(e);
            PageHelper.startPage(0,15);
        }

        List<TBlog> tBlogs=blogDao.getBlogList(map);
        if(tBlogs==null){
            result.setResult(ResultStatus.NOTDATA);
            return result;
        }

//        //迭代加上点赞数——纪念逝去的代码，被sql解决了
//        Iterator<TBlog> tBlogIterator=tBlogs.iterator();
//        while (tBlogIterator.hasNext()){
//            TBlog tBlogLins = tBlogIterator.next();
//            tBlogLins.setLikeNum(likeDao.selectLikeByBlogId(tBlogLins.getBlogId()));
//        }

        PageInfo<TBlog> pageInfo =new PageInfo<TBlog>(tBlogs);

        result.setData(pageInfo);
        return result;
    }

    //获取博客详情
    @Override
    public Result getBlog(Map map,String userId) {
        Result result =new Result();

        TBlog tBlog=blogDao.getBlog(map);
        if(tBlog==null){
            result.setResult(ResultStatus.NOTDATA);
            return result;
        }
        //设置标签
        tBlog.setTagsList(blogTagsDao.getTagsListByBlogId(tBlog.getBlogId()));
        //设置点赞数
        tBlog.setLikeNum(blogLikeDao.selectLikeByBlogId(tBlog.getBlogId()));
        //删除敏感数据
        tBlog.setUserId(-1);
        //增加浏览量
        blogDao.addBrowse(tBlog.getBlogId());
        tBlog.setBrowse(tBlog.getBrowse()+1);

        //检查该用户是否点赞
        if(userId!=null)
            if (blogLikeDao.selectLike(tBlog.getBlogId(), Integer.valueOf(userId)) == 0) {
                tBlog.setLike(false);
            } else {
                tBlog.setLike(true);
            }

        result.setData(tBlog);
        return result;
    }

    //获取首页信息
    @Override
    public Result getHomeClass() {
        Result result=new Result();
        try {
            List<TClassification> tClassifications = tagsDao.getClassificationList();
            //迭代往大标题加小标签
            Iterator<TClassification> tClassificationIterator =tClassifications.iterator();
            while (tClassificationIterator.hasNext()){
                //只能next一次
                TClassification tClassification=tClassificationIterator.next();
                tClassification.settTagsList(tagsDao.getTagsList("",tClassification.getClassId()));
            }
            result.setData(tClassifications);
            return result;
        }catch (Exception e) {
            result.setResult(ResultStatus.SERVERERR);
            return result;
        }
    }

    //点赞
    @Override
    public Result insertLike(TLike tLike) {
        Result result =new Result();
        tLike.setDate(new Date());
//        //先删除再点赞，防恶意
//        likeDao.deleteLike(tLike);
        if(tLike.getCommentId()==null){
            blogLikeDao.insertLike(tLike);
            result.setMessage("blogLikeSuccess");
        //否则赞评论
        }else {
            if(commentLikeDao.insertLike(tLike)!=0){
                result.setMessage("commentLikeSuccess");
            }else {
                result.setResult(ResultStatus.SERVERERR);
            }
        }
        return result;
    }

    //删赞
    @Override
    public Result deleteLike(TLike tLike) {
        Result result =new Result();
        //如果是删博客
        if(tLike.getCommentId()==null){
            blogLikeDao.deleteLike(tLike.getBlogId(),tLike.getUserId());
            result.setMessage("blogLikeDeleteSuccess");
        //否则删评论
        }else {
            if(commentLikeDao.deleteLikeByCommentId(tLike.getCommentId(), tLike.getUserId())!=0){
                result.setMessage("likeDeleteSuccess");
            }else {
                result.setResult(ResultStatus.SERVERERR);
            }
        }
        return result;
    }

    //新增博客
    @Override
    public Result insertBlog(TBlog tBlog) {
        Result result=new Result();
        if(blogDao.insertBlog(tBlog)==1){
            //如果有标签
            if(!tBlog.getTagsList().isEmpty()){
                if(blogTagsDao.insertByTagsIdListAndBlogId(tBlog.getTagsList(),tBlog.getBlogId())>=1){
                    result.setMessage("insertBlogAndTagsSuccess");
                    return result;
                }
                else {
                    result.setCode(504);
                    result.setMessage("insertBlogSuccess,butTagsFail");
                    return result;
                }
            }
            else {
                result.setMessage("insertBlogSuccess");
                return result;
            }
        }else {
            result.setCode(505);
            result.setMessage("insertFail");
            return result;
        }
    }

    //按用户查博客列表
    @Override
    public Result getBlogListByUserId(String userId) {
        Result result=new Result();
        List<TBlog> blogList=blogDao.getBlogListByUserId(userId);
        if (blogList.isEmpty()){
            result.setResult(ResultStatus.NOTDATA);
            return result;
        }
        result.setData(blogList);
        return result;
    }

    //更新博客
    @Override
    public Result updateBlog(TBlog tBlog) {
        //先删除标签
        blogTagsDao.deleteByBlogId(tBlog.getBlogId());
        Result result=new Result();
        if(blogDao.updateBlog(tBlog)==1){
            //如果有标签
            if(!tBlog.getTagsList().isEmpty()){
                if(blogTagsDao.insertByTagsIdListAndBlogId(tBlog.getTagsList(),tBlog.getBlogId())>=1){
                    result.setMessage("insertBlogAndTagsSuccess");
                    return result;
                }
                else {
                    result.setCode(504);
                    result.setMessage("insertBlogSuccess,butTagsFail");
                    return result;
                }
            }
            else {
                result.setMessage("insertBlogSuccess");
                return result;
            }
        }else {
            result.setCode(505);
            result.setMessage("insertFail");
            return result;
        }
    }

    //删除博客
    @Override
    public Result deleteBlog(Integer blogId,Integer userId) {
        Result result=new Result();
        //用户核验，是否本人删除
        if(blogDao.checkBlogByBlogId(blogId).equals(userId)){
            try {
//            纪念逝去的代码——被数据库的外键删除关系解决了
//            //先删标签
//            blogTagsDao.deleteByBlogId(blogId);
//            //删赞
//            blogLikeDao.deleteLikeByBlogId(blogId);
//            commentLikeDao.deleteLikeByBlogId(blogId);
//            //删评论
//            commentDao.deleteCommentByBlog(blogId);
            //删博客
            blogDao.deleteBlogById(blogId);
            }catch (Exception e){
                result.setResult(ResultStatus.SERVERERR);
                return result;
            }
            result.setMessage("deleteSuccess");
        }else {
            result.setResult(ResultStatus.ILLEAGL);
        }
        return result;
    }

    //获取评论
    @Override
    public Result getComment(Map map,String userId) {
        Result result=new Result();
        try{
            PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),10);
        }catch (Exception e){
            System.out.println(e);
            PageHelper.startPage(0,10);
        }

        List<TComment> tComments=commentDao.getCommentListByBlogId(Integer.parseInt(map.get("blogId").toString()));
        if(tComments.isEmpty()){
            result.setResult(ResultStatus.NOTDATA);
            return result;
        }else {
            //迭代检查该用户是否点赞
            if(userId!=null){
                Iterator<TComment> tCommentIterator=tComments.iterator();
                while (tCommentIterator.hasNext()){
                    TComment tComment=tCommentIterator.next();
                    if(commentLikeDao.selectLikeByComment(tComment.getCommentId(),Integer.valueOf(userId))==0){
                        tComment.setLike(true);
                    }else {
                        tComment.setLike(false);
                    }
                }
            }
        }
        PageInfo <TComment> tCommentPageInfo=new PageInfo<TComment>(tComments);
        result.setData(tCommentPageInfo);
        return result;
    }

    //获取子评论
    @Override
    public Result getCommentChild(Integer dialogId,String userId) {
        Result result=new Result();
        try {
            List<TComment> tComments=commentDao.getCommentChild(dialogId);
            if(tComments.isEmpty()){
                result.setResult(ResultStatus.NOTDATA);
                return result;
            }else {
                //迭代检查该用户是否点赞
                if(userId!=null){
                    Iterator<TComment> tCommentIterator=tComments.iterator();
                    while (tCommentIterator.hasNext()){
                        TComment tComment=tCommentIterator.next();
                        if(commentLikeDao.selectLikeByComment(tComment.getCommentId(),Integer.valueOf(userId))==0){
                            tComment.setLike(true);
                        }else {
                            tComment.setLike(false);
                        }
                    }
                }
            }
            result.setData(tComments);
            return result;
        }catch (Exception e){
            result.setResult(ResultStatus.SERVERERR);
            return result;
        }
    }
}
