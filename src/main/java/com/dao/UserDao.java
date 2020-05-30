package com.dao;

import com.bean.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {

    @Select(
            "Select\n" +
                    "t_user.userId,\n" +
                    "t_user.userName,\n" +
                    "t_user.avatar,\n" +
                    "t_user.summary,\n" +
                    "t_user.attribute,\n" +
                    "t_user.role \n"+
                    "From \n" +
                    "t_user\n" +
                    "Where \n" +
                    "userName=#{0}\n" +
                    "AND \n" +
                    "password=#{1}"
    )
    public TUser userLogin(String userName, String password);

    @Select(
            "SELECT\n" +
                    "t_user.userName,\n" +
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
    public TUser getUserData(Integer userId);

    @Select(
            "SELECT\n" +
                    "count(t_user.userName)\n" +
                    "FROM\n" +
                    "t_user\n" +
                    "WHERE\n" +
                    "t_user.userName=#{0}"
    )
    public int checkUserName(String userName);

    @Update(
            "UPDATE\n" +
                    "	t_user u\n" +
                    "	SET\n" +
                    "	u.userName=#{userName},\n" +
                    "	u.email=#{email},\n" +
                    "	u.avatar=#{avatar},\n" +
                    "	u.occupation=#{occupation},\n" +
                    "	u.gender=#{gender},\n" +
                    "	u.summary=#{summary}\n" +
                    "	WHERE\n" +
                    "	u.userId=#{userId}"
    )
    public int updateUserData(TUser tUser);
}
