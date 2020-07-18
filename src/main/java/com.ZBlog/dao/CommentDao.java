package com.ZBlog.dao;

import com.ZBlog.bean.TComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {

    @Insert(
            "INSERT\n" +
                    "	t_comment(userId,blogId,date,content,rootId,dialogId)\n" +
                    "	VALUES(#{userId},#{blogId},#{date},#{content},#{rootId},#{dialogId})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "commentId", keyColumn = "commentId")
    public int insertComment(TComment tComment);

    @Delete(
            "DELETE FROM\n" +
                    "	t_comment\n" +
                    "WHERE\n" +
                    "	t_comment.blogId=#{0}"

    )
    public int deleteCommentByBlog(Integer blogId);

    @Delete(
            "DELETE FROM\n" +
                    "	t_comment\n" +
                    "WHERE\n" +
                    "	t_comment.commentId=#{0}"

    )
    public int deleteCommentByCommentId(Integer commentId);

    @Delete(
            "DELETE FROM\n" +
                    "	t_comment\n" +
                    "WHERE\n" +
                    "	t_comment.dialogId=#{0}"
    )
    public int deleteCommentByDialogId(Integer dialogId);

    @Delete(
            "DELETE FROM\n" +
                    "	t_comment\n" +
                    "WHERE\n" +
                    "	t_comment.rootId=#{0}"
    )
    public int deleteCommentByRootId(Integer dialogId);

    @Select(
            "SELECT\n" +
                    "	count(commentId)\n" +
                    "FROM\n" +
                    "	t_comment\n" +
                    "WHERE\n" +
                    "	t_comment.dialogId=#{0}"
    )
    public int countCommentByDialogId(Integer dialogId);

    @Select(
            "SELECT\n" +
                    "  t_comment.commentId,\n" +
                    "  t_comment.content,\n" +
                    "  t_comment.date,\n" +
                    "  t_user.userName,\n" +
                    "  t_user.nickName,\n" +
                    "  t_user.avatar,\n" +
                    "	a.commentNum,\n" +
                    "	b.likeNum\n" +
                    "FROM\n" +
                    "  t_comment\n" +
                    "INNER JOIN\n" +
                    "  t_user\n" +
                    "ON\n" +
                    "  t_comment.userId=t_user.userId\n" +
                    "LEFT JOIN\n" +
                    "	(\n" +
                    "		SELECT\n" +
                    "			t_comment.dialogId,\n" +
                    "			count(*) AS commentNum\n" +
                    "		FROM\n" +
                    "			t_comment\n" +
                    "		WHERE\n" +
                    "			t_comment.blogId=#{0}\n" +
                    "			AND\n" +
                    "			t_comment.dialogId is not null\n" +
                    "		GROUP BY\n" +
                    "			t_comment.dialogId	\n" +
                    "	) a\n" +
                    "ON\n" +
                    "	t_comment.commentId=a.dialogId\n" +
                    "LEFT JOIN\n" +
                    "	(\n" +
                    "		SELECT\n" +
                    "			t_commentLike.commentId,\n" +
                    "			count(*) AS likeNum\n" +
                    "		FROM\n" +
                    "			t_commentLike\n" +
                    "		WHERE\n" +
                    "			t_commentLike.commentId is not null\n" +
                    "		GROUP BY\n" +
                    "			t_commentLike.commentId\n" +
                    "	) b\n" +
                    "ON\n" +
                    "	t_comment.commentId=b.commentId\n" +
                    "WHERE\n" +
                    "  t_comment.blogId=#{0}  \n" +
                    "	AND\n" +
                    "  t_comment.dialogId is null\n" +
                    "  AND\n" +
                    "  t_comment.rootId is null  ORDER BY \n" +
                    "     t_comment.date DESC"
    )
    public List<TComment> getCommentListByBlogId(Integer blogId);

    @Select(
            "SELECT\n" +
                    "	a.commentId,\n" +
                    "	a.blogId,\n" +
                    "	a.date,\n" +
                    "	a.content,\n" +
                    "	a.rootId,\n" +
                    "	a.dialogId,\n" +
                    "	t_user.userName,\n" +
                    "   t_user.nickName,\n" +
                    "	t_user.avatar,\n" +
                    "	b.toUserName,\n" +
                    "	c.likeNum\n" +
                    "FROM\n" +
                    "	t_comment a\n" +
                    "INNER JOIN\n" +
                    "		t_user\n" +
                    "	ON\n" +
                    "		a.userId=t_user.userId\n" +
                    "LEFT JOIN\n" +
                    "	(\n" +
                    "	SELECT\n" +
                    "		t_comment.commentId,\n" +
                    "		t_comment.rootId,\n" +
                    "		t_user.userName AS toUserName\n" +
                    "	FROM\n" +
                    "		t_comment\n" +
                    "	INNER JOIN\n" +
                    "		t_user\n" +
                    "	ON\n" +
                    "		t_comment.userId=t_user.userId\n" +
                    "	WHERE\n" +
                    "		dialogId=#{0}\n" +
                    "	) b\n" +
                    "ON\n" +
                    "	a.rootId=b.commentId\n" +
                    "LEFT JOIN\n" +
                    "	(\n" +
                    "		SELECT\n" +
                    "			t_commentLike.commentId,\n" +
                    "			count(*) AS likeNum\n" +
                    "		FROM\n" +
                    "			t_commentLike\n" +
                    "		GROUP BY\n" +
                    "			t_commentLike.commentId\n" +
                    "	)	c\n" +
                    "ON\n" +
                    "	a.commentId=c.commentId\n" +
                    "WHERE\n" +
                    "	a.dialogId =#{0}\n" +
                    "ORDER BY\n" +
                    "	a.date DESC"
    )
    public List<TComment> getCommentChild(Integer dialogId);

    @Select(
            "SELECT\n" +
                    "	*\n" +
                    "FROM\n" +
                    "	t_comment\n" +
                    "WHERE\n" +
                    "	t_comment.commentId=#{0}"
    )
    public TComment getCommentByCommentId(Integer commentId);
}
