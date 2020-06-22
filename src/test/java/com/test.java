package com;

//import com.alibaba.fastjson.JSONObject;

import com.ZBlog.util.MailUtil;
import com.util.TokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    DataSource dataSource;

    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void hello() throws SQLException {
        logger.trace("日志测试");
        logger.debug("debug信息");
        dataSource.getClass();
        Connection connection =dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    public void userLogin(){
        TokenUtil tokenUtil =new TokenUtil();
        System.out.println(tokenUtil.getTokenData("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsYXN0TG9naW4iOjE1OTAzOTg3MzA2OTgsInJvbGUiOiJhZG1pbiIsInVzZXJOYW1lIjoiWmVyb1MiLCJ1c2VySWQiOjF9.xmvhYk646HxxdH_CR7ua_tfq0r8jTq_N85FHslbBsGc").get("role"));
    }

    @Test
    public void testMail(){
        MailUtil mailUtil = new MailUtil();
        mailUtil.sendSimpleMail("LC1164326212@163.com","Hello!","word");
    }

    public static void main(String args[]){

//        TokenUtil tokenUtil = new TokenUtil();
//        String token = tokenUtil.getMailToken(123456);
//        System.out.println(token);
//        System.out.println(tokenUtil.getMailCode(token));
        Integer i = null;
        Integer j = 1;
        if (i != j)
            System.out.println("yes");

        System.out.println(
                "SELECT \n" +
                        "	t_blog.blogId,\n" +
                        "	t_blog.title ,\n" +
                        "	t_blog.userId,\n" +
                        "	t_blog.summary,\n" +
                        "	t_blog.blogDate,\n" +
                        "	t_blog.browse,\n" +
                        "	t_user.avatar,\n" +
                        "	t_user.userName,\n" +
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
                        "	ORDER BY \n" +
                        "		t_blog.blogDate DESC"
        );
    }


}
