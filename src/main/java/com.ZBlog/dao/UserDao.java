package com.ZBlog.dao;

import com.ZBlog.bean.TUser;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserDao {

    @Select(
            "Select\n" +
                    "t_user.userId,\n" +
                    "t_user.userName,\n" +
                    "t_user.nickName,\n" +
                    "t_user.avatar,\n" +
                    "t_user.email,\n" +
                    "t_user.role, \n"+
                    "t_user.isBan \n"+
                    "From \n" +
                    "t_user\n" +
                    "Where \n" +
                    "userName=#{0}\n" +
                    "AND \n" +
                    "password=#{1}"
    )
    //用户登录
    public TUser userLogin(String userName, String password);

    @Insert(
            "INSERT INTO\n" +
                    "	t_user(userName, nickName, `password`,email)\n" +
                    "VALUES\n" +
                    "	(#{0},#{0},#{1},#{2})"
    )
    //用户注册
    public Integer userRegister(String userName, String password, String email);

    @Select(
            "SELECT\n" +
                    "t_user.userName,\n" +
                    "t_user.nickName,\n" +
                    "t_user.occupation,\n" +
                    "t_user.avatar,\n" +
                    "t_user.summary,\n" +
                    "t_user.email,\n" +
                    "t_user.phone,\n" +
                    "t_user.gender,\n" +
                    "t_user.attribute,\n" +
                    "b.blogNum\n" +
                    "FROM\n" +
                    "t_user\n" +
                    "LEFT JOIN\n" +
                    "	(\n" +
                    "		SELECT\n" +
                    "			count(t_blog.blogId) as blogNum,\n" +
                    "			t_blog.userId\n" +
                    "		FROM\n" +
                    "			t_blog\n" +
                    "		WHERE\n" +
                    "			t_blog.userId=#{0}\n" +
                    "	) b\n" +
                    "ON\n" +
                    "	t_user.userId=b.userId\n" +
                    "WHERE t_user.userId=#{0}"
    )
    //获取用户信息
    public TUser getUserData(Integer userId);

    @Select(
            "SELECT\n" +
                    "t_user.userId\n" +
                    "FROM\n" +
                    "t_user\n" +
                    "WHERE\n" +
                    "t_user.userName=#{0}"
    )
    //检查用户名
    public String checkUserName(String userName);

    @Select(
            "SELECT\n" +
                    "count(t_user.userName)\n" +
                    "FROM\n" +
                    "t_user\n" +
                    "WHERE\n" +
                    "t_user.email=#{0}"
    )
    //检查邮箱
    public int checkEmail(String email);

    @Update(
            "UPDATE\n" +
                    "	t_user u\n" +
                    "	SET\n" +
                    "	u.nickName=#{nickName},\n" +
                    "	u.email=#{email},\n" +
                    "	u.avatar=#{avatar},\n" +
                    "	u.occupation=#{occupation},\n" +
                    "	u.gender=#{gender},\n" +
                    "	u.summary=#{summary}\n" +
                    "	WHERE\n" +
                    "	u.userId=#{userId}"
    )
    //更新用户信息
    public int updateUserData(TUser tUser);

    @Update(
            "UPDATE\n" +
                    "	t_user u\n" +
                    "	SET\n" +
                    "	u.password=#{1}\n" +
                    "	WHERE\n" +
                    "	u.userId=#{0}"
    )
    //更新密码
    public int updatePassWd(Integer userId,String passWd);

    @Update(
            "UPDATE\n" +
                    "	t_user u\n" +
                    "	SET\n" +
                    "	u.email=#{1}\n" +
                    "	WHERE\n" +
                    "	u.userId=#{0}"
    )
    //更新邮箱
    public int updateEmail(Integer userId,String email);

    @Select(
        "SELECT \n" +
                "	userId,userName,email,phone,role\n" +
                "FROM `t_user`"
    )
    //获取所有用户数据
    public List<TUser> getUserList();

    @Update(
            "UPDATE\n" +
                    "	t_user\n" +
                    "SET\n" +
                    "	t_user.isBan = IF(t_user.isBan=1,0,1)\n" +
                    "WHERE\n" +
                    "	t_user.userId = #{0}"
    )
    //更改用户的封禁状态
    public int banUserByUserId(Integer userId);

    @Update(
            "UPDATE\n" +
                    "	t_user\n" +
                    "SET\n" +
                    "	t_user.role = #{1}\n" +
                    "WHERE\n" +
                    "	t_user.userId = #{0}"
    )
    //更改用户的角色
    public int changeRoleByUserId(Integer userId, String role);
}
