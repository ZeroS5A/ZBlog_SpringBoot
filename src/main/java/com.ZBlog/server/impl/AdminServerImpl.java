package com.ZBlog.server.impl;

import com.ZBlog.bean.TUser;
import com.ZBlog.commom.Result;
import com.ZBlog.commom.ResultStatus;
import com.ZBlog.dao.UserDao;
import com.ZBlog.server.AdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminServer")
public class AdminServerImpl implements AdminServer {
    @Autowired
    UserDao userDao;

    @Override
    public Result getUserList() {
        Result result = new Result();
        List<TUser> tUsers = userDao.getUserList();
        if(tUsers.isEmpty()){
            result.setResult(ResultStatus.NOTDATA);
            return result;
        }else {
            result.setData(tUsers);
            return result;
        }
    }

    @Override
    public Result banUserByUserId(String _userId) {
        Result result = new Result();
        try {
            Integer userId = Integer.valueOf(_userId);
            if (userDao.banUserByUserId(userId) == 1) {
                result.setMessage("changeSuccess");
                return result;
            }else {
                result.setResult(ResultStatus.SERVERERR);
                return result;
            }
        }catch (Exception e){
            result.setResult(ResultStatus.SERVERERR);
            return result;
        }
    }

    @Override
    public Result changeRoleByUserId(String _userId, String role) {
        Result result = new Result();
        try {
            Integer userId = Integer.valueOf(_userId);
            if (userDao.changeRoleByUserId(userId, role) == 1) {
                result.setMessage("changeSuccess");
                return result;
            }else {
                result.setResult(ResultStatus.SERVERERR);
                return result;
            }
        }catch (Exception e){
            result.setResult(ResultStatus.SERVERERR);
            return result;
        }
    }
}
