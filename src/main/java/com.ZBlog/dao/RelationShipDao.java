package com.ZBlog.dao;

import com.ZBlog.bean.TRelationship;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RelationShipDao {

    @Insert(
            "INSERT\n" +
                    "	t_relationship\n" +
                    "	SET\n" +
                    "	t_relationship.user1=#{0},\n" +
                    "	t_relationship.user2=#{1},\n" +
                    "	t_relationship.type=#{2}"
    )
    int insertRelationShip(Integer userId1, Integer userId2, String type);

    @Delete(
            "DELETE FROM\n" +
                    "	t_relationship\n" +
                    "WHERE\n" +
                    "	t_relationship.user1=#{0}\n" +
                    "   AND\n"+
                    "	t_relationship.user2=#{1}\n" +
                    "   AND\n"+
                    "	t_relationship.type=#{2}"
    )
    int deleteRelationShip(Integer userId1, Integer userId2, String type);

    @Select(
            "SELECT\n" +
                    "   count(t_relationship.relationshipId)\n" +
                    "FROM\n" +
                    "   t_relationship\n" +
                    "WHERE\n" +
                    "	t_relationship.user1=#{0}\n" +
                    "   AND\n"+
                    "	t_relationship.user2=#{1}\n" +
                    "   AND\n"+
                    "	t_relationship.type=#{2}"
    )
    //检查关系
    public int checkRelationShip(Integer userId1, Integer userId2, String type);

    @Select(
            "SELECT \n" +
                    "	t_relationship.type,\n" +
                    "	t_user.userName,\n" +
                    "	t_user.nickName,\n" +
                    "	t_user.avatar,\n" +
                    "	t_user.summary\n" +
                    "FROM \n" +
                    "	t_relationship\n" +
                    "INNER JOIN\n" +
                    "	t_user\n" +
                    "	ON\n" +
                    "	t_relationship.user2=t_user.userId\n" +
                    "WHERE\n" +
                    "	t_relationship.user1= #{0}"
    )
    //获取关注列表
    public List<TRelationship> getUserRelationShip(Integer userId);
}
