package com.ZBlog.dao;

import com.ZBlog.bean.TLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlogLikeDao {
    @Insert(
            "INSERT INTO\n" +
                    "	t_blogLike(userId,date,blogId)\n" +
                    "VALUES\n" +
                    "	(#{userId},#{date},#{blogId})"
    )
    public int insertLike(TLike tLike);

    @Select(
            "SELECT\n" +
                    "	count(*)\n" +
                    "FROM\n" +
                    "	t_blogLike\n" +
                    "WHERE\n" +
                    "	t_blogLike.blogId=#{blogId}\n"
    )
    public int selectLikeByBlogId(Integer blogId);

    @Delete(
            "DELETE FROM\n" +
                    "	t_blogLike\n" +
                    "WHERE\n" +
                    "	t_blogLike.blogId=#{0}\n"+
                    "   AND\n"+
                    "   t_blogLike.userId=#{1}"
    )
    public int deleteLike(Integer blogId,Integer userId);

    @Select(
            "SELECT\n" +
                    "count(*)\n" +
                    "FROM\n" +
                    "	t_blogLike\n" +
                    "WHERE\n" +
                    "	t_blogLike.blogId=#{0}\n" +
                    "	AND\n" +
                    "	t_blogLike.userId=#{1}"
    )
    public int selectLike(Integer blogId,Integer userId);

    @Delete("DELETE FROM t_blogLike WHERE t_blogLike.blogId=#{0}")
    public int deleteLikeByBlogId(Integer blogId);
}
