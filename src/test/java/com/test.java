package com;

import com.alibaba.fastjson.JSONObject;

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

//    public static void main(String args[]){
//        System.out.println(
//                "SELECT\n" +
//                        "	a.commentId,\n" +
//                        "	a.blogId,\n" +
//                        "	a.date,\n" +
//                        "	a.content,\n" +
//                        "	a.rootId,\n" +
//                        "	a.dialogId,\n" +
//                        "	t_user.userName,\n" +
//                        "	t_user.avatar,\n" +
//                        "	b.toUserName,\n" +
//                        "	c.likeNum\n" +
//                        "FROM\n" +
//                        "	t_comment a\n" +
//                        "INNER JOIN\n" +
//                        "		t_user\n" +
//                        "	ON\n" +
//                        "		a.userId=t_user.userId\n" +
//                        "LEFT JOIN\n" +
//                        "	(\n" +
//                        "	SELECT\n" +
//                        "		t_comment.commentId,\n" +
//                        "		t_comment.rootId,\n" +
//                        "		t_user.userName AS toUserName\n" +
//                        "	FROM\n" +
//                        "		t_comment\n" +
//                        "	INNER JOIN\n" +
//                        "		t_user\n" +
//                        "	ON\n" +
//                        "		t_comment.userId=t_user.userId\n" +
//                        "	WHERE\n" +
//                        "		dialogId=#{0}\n" +
//                        "	) b\n" +
//                        "ON\n" +
//                        "	a.rootId=b.commentId\n" +
//                        "LEFT JOIN\n" +
//                        "	(\n" +
//                        "		SELECT\n" +
//                        "			t_commentLike.commentId,\n" +
//                        "			count(*) AS likeNum\n" +
//                        "		FROM\n" +
//                        "			t_commentLike\n" +
//                        "		GROUP BY\n" +
//                        "			t_commentLike.commentId\n" +
//                        "	)	c\n" +
//                        "ON\n" +
//                        "	a.commentId=c.commentId\n" +
//                        "WHERE\n" +
//                        "	a.dialogId =#{0}\n" +
//                        "ORDER BY\n" +
//                        "	a.date DESC"
//        );
//    }


}
