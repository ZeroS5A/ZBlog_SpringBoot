package com.ZBlog.dao;

import com.ZBlog.bean.TTags;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogTagsDao {

    @Select("SELECT t_tags.* FROM t_blogtags LEFT JOIN t_tags ON t_tags.tagsId = t_blogtags.tagsId WHERE t_blogtags.blogId = #{blogId}")
    public List<TTags> getTagsListByBlogId(Integer blogId);

    //重点写博！
    @Insert({
            "<script>",
            "INSERT INTO t_blogtags(blogId, tagsId) VALUES ",
            "<foreach collection='tagsIdList' item='item' index='index' separator=','>",
            "(#{1}, #{item.tagsId})",
            "</foreach>",
            "</script>"
    })
    public Integer insertByTagsIdListAndBlogId(@Param(value="tagsIdList")List<TTags> tagsIdList, Integer blogId);

    @Delete("DELETE FROM t_blogtags WHERE blogId = #{blogId}")
    public Integer deleteByBlogId(Integer blogId);

}
