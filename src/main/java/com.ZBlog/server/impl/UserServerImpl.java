package com.ZBlog.server.impl;

import com.ZBlog.bean.*;
import com.ZBlog.commom.Result;
import com.ZBlog.commom.ResultStatus;
import com.ZBlog.dao.*;
import com.ZBlog.server.UserServer;
import com.ZBlog.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("UserServer")
public class UserServerImpl implements UserServer {
    @Autowired
    UserDao userDao;
    @Autowired
    BlogDao blogDao;
    @Autowired
    TagsDao tagsDao;
    @Autowired
    CommentLikeDao commentLikeDao;
    @Autowired
    BlogTagsDao blogTagsDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    FileDao fileDao;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    RelationShipDao relationShipDao;

    //用户登录
    @Override
    public Result userLogin(String userName, String password) {
        Result result=new Result();
        TokenUtil tokenUtil=new TokenUtil();

        TUser tUser=userDao.userLogin(userName,password);

        //查无此人
        if(tUser==null){
            result.setResult(ResultStatus.LOGINFAILED);
            return result;
        //封禁账户
        }else if(tUser.getIsBan().equals(1)){
            result.setResult(ResultStatus.ACCOUNTBAN);
            return result;
        //签发token及返回用户数据
        }else{
            String token = tokenUtil.getToken(tUser);
            //销毁数据
            tUser.setUserId(-1);
            if (token!=null){
                Map<String,Object> map =new HashMap<String, Object>();
                map.put("token",token);
                map.put("UserData",tUser);
                result.setData(map);
                return result;
            }else {
                System.out.println("Token创建失败");
                result.setResult(ResultStatus.SERVERERR);
                return result;
            }

        }
    }

    @Override
    public Result userRegister(Map<String, String> map) {
        Result result = new Result();
        //验证验证码正确且未过期
        if (tokenUtil.getMailCode(map.get("token")).equals(map.get("code")) && tokenUtil.goodToken(map.get("token"))){
            //验证是否用户名已被注册
            if (userDao.checkUserName(map.get("userName"))== null){
                if (userDao.userRegister(map.get("userName"),map.get("passwd"),map.get("email")) == 1){
                    result.setMessage("注册成功！");
                    return result;
                }else {
                    result.setResult(ResultStatus.SERVERERR);
                    return result;
                }
            }else {
                result.setCode(301);
                result.setMessage("该用户名已被注册！");
                return result;
            }
        }else {
            result.setCode(302);
            result.setMessage("验证码错误或者已经过期！");
            return result;
        }
    }

    @Override
    public Result getUserData(Integer userId) {
        Result result = new Result();
        try {
            result.setData(userDao.getUserData(userId));
        }catch (Exception e){
            result.setResult(ResultStatus.SERVERERR);
        }
        return result;
    }

    @Override
    public Result updateUserData(TUser tUser,String userName) {
        Result result = new Result();
        //不为空说明更改了头像
        if(tUser.getImgName()!=null){
            TFile tFile = new TFile();
            tFile.setFileMd5(tUser.getImgName());
            tFile.setUserId(tUser.getUserId());
            tFile.setDate(new Date());
            tFile.setIsAvatar(1);
            fileDao.insertFileName(tFile);
        }
        //判断未改变userName
        if(tUser.getUserName().equals(userName)){
            userDao.updateUserData(tUser);
            result.setCode(200);
        }else{
            if(userDao.checkUserName(tUser.getUserName())==null){
                userDao.updateUserData(tUser);
                result.setCode(201);
            }else {
                result.setCode(304);
                result.setMessage("该用户名已被注册");
                return result;
            }
        }
        result.setMessage("Success");
        return result;
    }

    @Override
    public Result updatePassWd(Integer userId, Map<String,String> map) {
        Result result = new Result();
        //验证原密码
        if(userDao.userLogin(map.get("userName"),map.get("rowpasswd")) != null){
            //更新密码
            if(userDao.updatePassWd(userId,map.get("passwd")) != 0){
                result.setMessage("resetSuccess");
                return result;
            }
            result.setResult(ResultStatus.SERVERERR);
            return result;
        }
        result.setResult(ResultStatus.LOGINFAILED);
        return result;
    }

    @Override
    public Result updateEmail(Integer userId, Map<String, String> map) {
        Result result = new Result();
        if(tokenUtil.goodToken(map.get("token"))){
            if (userDao.checkEmail(map.get("email"))==0){
                if (map.get("code").equals(tokenUtil.getMailCode(map.get("token")))){
                    userDao.updateEmail(userId,map.get("email"));
                    result.setMessage("updateSuccess");
                    return result;
                }
                result.setCode(302);
                result.setMessage("fail");
                return result;
            }
            result.setCode(301);
            result.setMessage("had");
            return result;
        }
        result.setCode(303);
        result.setMessage("bad");
        return result;
    }

    @Override
    public Result insertTags(TTags tTags) {
        Result result=new Result();
        if (tagsDao.insert(tTags)==0){
            result.setCode(505);
            result.setMessage("insertFail");
            return result;
        }
        result.setData(tTags);
        return result;
    }

    @Override
    public Result getTagsList(String userName, Integer classId) {
        Result result=new Result();
        List<TTags> tTags =tagsDao.getTagsList(userName,classId);
        if (tTags==null){
            result.setResult(ResultStatus.NOTDATA);
            return result;
        }
        result.setData(tTags);
        return result;
    }

    @Override
    public Result getClassificationList() {
        Result result=new Result();
        List<TClassification> tClassifications =tagsDao.getClassificationList();
        if (tClassifications==null){
            result.setResult(ResultStatus.NOTDATA);
            return result;
        }
        result.setData(tClassifications);
        return result;
    }

    @Override
    public Result addComment(TComment tComment) {
        Result result=new Result();
        tComment.setDate(new Date());
        //获取对谁评论的评论id
        if(tComment.getLastCommentId()!=null)
            tComment.setRootId(tComment.getLastCommentId());
        //插入数据
        if (commentDao.insertComment(tComment)==0){
            result.setResult(ResultStatus.NOTDATA);
            return result;
        }
        result.setData(tComment);
        return result;
    }

    @Override
    public Result deleteComment(TComment tComment) {
        Result result=new Result();

        //统计删除数
        int res=0;
        //判断是不是本用户操作
        if(tComment.getUserId().equals(commentDao.getCommentByCommentId(tComment.getCommentId()).getUserId())){

            if(commentDao.deleteCommentByCommentId(tComment.getCommentId()) != 0){
                result.setMessage("deleteSuccess");
            }
            //逝去的代码——给数据库CASCADE解决了
////            System.out.println("用户通过");
//            //删除赞
//            //如果是子评论，则只删除一个
//            if(tComment.getDialogId()!=null){
////                System.out.println("是子评论");
//                //删赞
//                commentLikeDao.deleteLikeByCommentId(tComment.getCommentId(),tComment.getUserId());
//                //删评论
//                res+=commentDao.deleteCommentByCommentId(tComment.getCommentId());
//            //如果不存在对话ID，那么就是主评论
//            }else{
////                System.out.println("是主评论");
//                //删对话的赞
//                commentLikeDao.deleteLikeByDialogId(tComment.getCommentId());
//                //删自己的赞
//                commentLikeDao.deleteLikeByCommentId(tComment.getCommentId(),tComment.getUserId());
//                //删对话评论
//                res+=commentDao.deleteCommentByDialogId(tComment.getCommentId());
//                //删自己
//                res+=commentDao.deleteCommentByCommentId(tComment.getCommentId());
//            }
//            if (res==0){
//                result.setMessage("canNoDeleteAny");
//            }else {
//                result.setMessage("delete"+res+"comment");
//            }
        }else {
            result.setResult(ResultStatus.ILLEAGL);
        }

        return result;
    }

    @Override
    public Result changeAttention(Integer userId, Map map) {
        Result result = new Result();
        String user2 = userDao.checkUserName(map.get("userName").toString());
        if (relationShipDao.checkRelationShip(userId, Integer.valueOf(user2), "attention") == 0) {
            relationShipDao.insertRelationShip(userId, Integer.valueOf(user2), "attention");
            result.setCode(201);
            result.setMessage("关注成功");
        }
        else {
            relationShipDao.deleteRelationShip(userId, Integer.valueOf(user2), "attention");
            result.setCode(202);
            result.setMessage("取关成功");
        }
        return result;
    }

    @Override
    public Result getUserRelationShip(Integer userId) {
        Result result = new Result();
        result.setData(relationShipDao.getUserRelationShip(userId));
        return result;
    }


}
