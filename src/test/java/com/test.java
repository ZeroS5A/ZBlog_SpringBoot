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
        System.out.println(
                "SELECT\n" +
                        "	t_blog.blogId,\n" +
                        "	t_blog.title,\n" +
                        "	t_blog.blogDate,\n" +
                        "	t_blog.browse\n" +
                        "FROM\n" +
                        "	t_blog\n" +
                        "WHERE\n" +
                        "	(t_blog.title LIKE #{title}\n" +
                        "	OR\n" +
                        "	t_blog.blogId\n" +
                        "		IN\n" +
                        "		(	SELECT \n" +
                        "				t_blogtags.blogId\n" +
                        "			FROM \n" +
                        "				t_blogtags\n" +
                        "			WHERE \n" +
                        "				t_tags.tagName LIKE #{tagName})\n" +
                        "		)\n" +
                        "		AND\n" +
                        "		cast(t_blog.classId AS char) Like #{classId}\n" +
                        "	AND\n" +
                        "	t_blog.userId=#{3}\n" +
                        "ORDER BY \n" +
                        "	t_blog.blogDate DESC"
        );
    }


}
