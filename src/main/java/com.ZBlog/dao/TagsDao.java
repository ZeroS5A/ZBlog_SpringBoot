package com.ZBlog.dao;

import com.ZBlog.bean.TClassification;
import com.ZBlog.bean.TTags;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagsDao {

    @Select("SELECT * FROM t_tags WHERE id = #{id}")
    public TTags getById(Integer id);

    @Select("SELECT * FROM t_tags WHERE name = #{name}")
    public TTags getByName(String name);

    @Select("SELECT * FROM t_tags ORDER BY RAND() LIMIT 10")
    public List<TTags> getListByRand();

    @Insert("INSERT INTO t_tags(tagName,classification,createUser) VAlUES(#{tagName},#{classification},#{createUser})")
    //返回主键
    @Options(useGeneratedKeys = true, keyProperty = "tagsId", keyColumn = "tagsId")
    public Integer insert(TTags tags);

    @Select(
            "SELECT\n" +
                    "	*\n" +
                    "FROM\n" +
                    "	t_tags\n" +
                    "WHERE\n" +
                    "	(t_tags.createUser='Admin'\n" +
                    "	OR\n" +
                    "	t_tags.createUser=#{0})\n" +
                    "	AND\n" +
                    "	t_tags.classification=#{1}"
    )
    public List<TTags> getTagsList(String userName,Integer classId);

    @Select("SELECT * FROM t_classification")
    public List<TClassification> getClassificationList();

    @Update("UPDATE t_tags SET name = #{name} WHERE id = #{id}")
    public Integer update(TTags tags);

    @Delete("DELETE FROM t_tags WHERE id = #{id}")
    public Integer deleteById(Integer id);

}
