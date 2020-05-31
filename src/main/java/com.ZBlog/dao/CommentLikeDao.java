package com.ZBlog.dao;

import com.ZBlog.bean.TLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentLikeDao {
    @Insert(
            "INSERT INTO\n" +
                    "	t_commentLike(userId,date,blogId,commentId,dialogId)\n" +
                    "VALUES\n" +
                    "	(#{userId},#{date},#{blogId},#{commentId},#{dialogId})"
    )
    public int insertLike(TLike tLike);

    @Delete(
            "DELETE FROM\n" +
                    "	t_commentLike\n" +
                    "WHERE\n" +
                    "	t_commentLike.blogId=#{0}\n"
    )
    public int deleteLikeByBlogId(Integer blogId);

    @Delete(
            "DELETE FROM\n" +
                    "	t_commentLike\n" +
                    "WHERE\n" +
                    "	t_commentLike.commentId=#{0}\n"+
                    "   AND\n"+
                    "   t_commentLike.userId=#{1}"
    )
    public int deleteLikeByCommentId(Integer commentId,Integer userId);

    @Delete(
            "DELETE FROM\n" +
                    "	t_commentLike\n" +
                    "WHERE\n" +
                    "	t_commentLike.dialogId=#{0}\n"
    )
    public int deleteLikeByDialogId(Integer dialogId);


    @Select(
            "SELECT\n" +
                    "count(*)\n" +
                    "FROM\n" +
                    "	t_like\n" +
                    "WHERE\n" +
                    "	t_like.blogId=#{0}\n" +
                    "	AND\n" +
                    "	t_like.userId=#{1}"
    )
    public int selectLike(Integer blogId,Integer userId);

    @Select(
            "SELECT\n" +
                    "count(*)\n" +
                    "FROM\n" +
                    "	t_commentLike\n" +
                    "WHERE\n" +
                    "	t_commentLike.commentId=#{0}\n" +
                    "	AND\n" +
                    "	t_commentLike.userId=#{1}"
    )
    public int selectLikeByComment(Integer commentId,Integer userId);

    @Select(
            "SELECT\n" +
                    "	count(*)\n" +
                    "FROM\n" +
                    "	t_commentLike\n" +
                    "WHERE\n" +
                    "	t_commentLike.blogId=#{blogId}\n"
    )
    public int selectLikeByBlogId(Integer blogId);

    @Select(
            "SELECT\n" +
                    "	*\n" +
                    "FROM\n" +
                    "	t_commentLike\n" +
                    "WHERE\n" +
                    "	t_commentLike.likeId=#{0}"
    )
    public TLike selectLikeByLikeId(Integer likeId);
}
