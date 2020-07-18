package com.ZBlog.dao;

import com.ZBlog.bean.TBlog;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogDao {
    //获取简约列表
    @Select(
            "SELECT \n" +
                    "	t_blog.blogId,\n" +
                    "	t_blog.title ,\n" +
//                    "	t_blog.userId,\n" +
                    "	t_blog.summary,\n" +
                    "	t_blog.blogDate,\n" +
                    "	t_blog.browse,\n" +
                    "	t_blog.type,\n" +
                    "	t_user.avatar,\n" +
                    "	t_user.userName,\n" +
                    "	t_user.nickName,\n" +
                    "	a.likeNum,\n" +
                    "	b.commentNum\n" +
                    "FROM \n" +
                    "	t_blog\n" +
                    "INNER JOIN\n" +
                    "	t_user\n" +
                    "ON\n" +
                    "	t_blog.userId=t_user.userId\n" +
                    "LEFT JOIN\n" +
                    "	(SELECT\n" +
                    "		count(*) as likeNum,\n" +
                    "		blogId\n" +
                    "		FROM\n" +
                    "		t_blogLike\n" +
                    "		GROUP BY\n" +
                    "		t_blogLike.blogId\n" +
                    "		) a\n" +
                    "ON\n" +
                    "a.blogId=t_blog.blogId\n" +
                    "LEFT JOIN\n" +
                    "	(SELECT\n" +
                    "		count(*) as commentNum,\n" +
                    "		blogId\n" +
                    "		FROM\n" +
                    "		t_comment\n" +
                    "		WHERE\n" +
                    "			t_comment.dialogId is null\n" +
                    "		GROUP BY\n" +
                    "			t_comment.blogId\n" +
                    "		) b\n" +
                    "ON\n" +
                    "b.blogId=t_blog.blogId\n" +
                    "WHERE\n" +
                    "	(t_blog.title LIKE #{title}\n" +
                    "	OR\n" +
                    "	t_blog.blogId\n" +
                    "		IN\n" +
                    "		(	SELECT \n" +
                    "				t_blogtags.blogId\n" +
                    "			FROM \n" +
                    "				t_blogtags\n" +
                    "			INNER JOIN\n" +
                    "				t_tags\n" +
                    "			ON\n" +
                    "				t_blogtags.tagsId=t_tags.tagsId\n" +
                    "			WHERE \n" +
                    "				t_tags.tagName LIKE #{tagName})\n" +
                    "		)\n" +
                    "		AND\n" +
                    "		cast(t_blog.classId AS char) Like #{classId}\n" +
                    "	    AND\n" +
                    "	        t_blog.type > 0\n" +
                    "   ORDER BY\n" +
                    "	    t_blog.type DESC,"+
                    "		t_blog.blogDate DESC"
    )
    public List<TBlog> getBlogList(Map map);

    //获取博客详情
    @Select(
            "SELECT \n" +
                    "	t_blog.*,\n" +
                    "	t_user.avatar,\n" +
                    "   t_user.nickName,\n" +
                    "	t_user.userName\n" +
                    "FROM \n" +
                    "	t_blog\n" +
                    "INNER JOIN\n" +
                    "	t_user\n" +
                    "ON\n" +
                    "	t_blog.userId=t_user.userId\n" +
                    "WHERE\n" +
                    "	t_blog.blogId=#{blogId}"
    )
    public TBlog getBlog(Map map);

    @Insert(
            "INSERT INTO\n" +
                    "	t_blog(userId,classId,type,title,blogDate,blogContentHtml,blogContentMd,summary)\n" +
                    "VALUES\n" +
                    "	(#{userId},#{classId},#{type},#{title},#{blogDate},#{blogContentHtml},#{blogContentMd},#{summary})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "blogId", keyColumn = "blogId")
    public int insertBlog(TBlog tBlog);

    @Select(
            "SELECT\n" +
                    "	t_blog.blogId,\n" +
                    "	t_blog.title,\n" +
                    "	t_blog.blogDate,\n" +
                    "	t_blog.browse\n" +
                    "FROM\n" +
                    "	t_blog\n" +
                    "WHERE\n" +
                    "	(t_blog.title LIKE #{0}\n" +
                    "	OR\n" +
                    "	t_blog.blogId\n" +
                    "		IN\n" +
                    "		(	SELECT \n" +
                    "				t_blogtags.blogId\n" +
                    "			FROM \n" +
                    "				t_blogtags\n" +
                    "			WHERE\n" +
                    "				cast(t_blogtags.tagsId AS char) Like #{1}\n" +
                    "	)	)\n" +
                    "	AND\n" +
                    "	cast(t_blog.classId AS char) Like #{2}\n" +
                    "	AND\n" +
                    "	t_blog.userId=#{3}\n" +
                    "ORDER BY \n" +
                    "	t_blog.blogDate DESC"
    )
    public List<TBlog> getBlogListByUserId(String title, String tag, String classId, Integer userId);

    @Update(
            "UPDATE\n" +
                    "	t_blog\n" +
                    "SET\n" +
                    "	t_blog.classId=#{classId},\n" +
                    "	t_blog.blogContentHtml=#{blogContentHtml},\n" +
                    "	t_blog.blogContentMd=#{blogContentMd},\n" +
                    "	t_blog.title=#{title},\n" +
                    "	t_blog.summary=#{summary}\n" +
                    "WHERE\n" +
                    "	t_blog.blogId=#{blogId}\n"
    )
    @Options(useGeneratedKeys = true, keyProperty = "blogId", keyColumn = "blogId")
    public int updateBlog(TBlog tBlog);

    @Delete(
            "DELETE FROM\n" +
                    "	t_blog\n" +
                    "WHERE\n" +
                    "	t_blog.blogId=#{0}"
    )
    public int deleteBlogById(Integer blogId);

    @Update("UPDATE t_blog SET browse = browse + 1 WHERE blogId = #{0}")
    public int addBrowse(Integer blogId);

    @Select("SELECT t_blog.userId FROM t_blog WHERE t_blog.blogId=#{0}")
    public Integer checkBlogByBlogId(Integer blogId);

}
