package com.ZBlog.util;

import com.ZBlog.bean.TUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ZBlog.bean.TUser;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    //登录过期时间设置(300分钟)
    private static final long EXPIRE_TIME = 300*60*1000;
    //验证码过期时间设置(10分钟)
    private static final long MAILEXPIRE_TIME = 10*60*1000;

    //私钥设置(随便乱写的)
    private static final String TOKEN_SECRET = "ByZeroS======";

    Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

    public String getToken(TUser tUser){
        //过期时间和加密算法设置
//        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);

        //头部信息
        Map<String,Object> header=new HashMap<String, Object>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim("userId",tUser.getUserId())
                .withClaim("userName",tUser.getUserName())
                .withClaim("role",tUser.getRole())
                .withClaim("lastLogin",System.currentTimeMillis()+EXPIRE_TIME)
//                .withExpiresAt(date)
                .sign(algorithm);

    }
    //检查Token，如果错误或者超时，返回false
    public Boolean goodToken(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            if(jwt.getClaim("lastLogin").asLong()<System.currentTimeMillis()){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }

    //获取验证码token
    public String getMailToken(Integer mailCode){
        //头部信息
        Map<String,Object> header=new HashMap<String, Object>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim("mailCode",mailCode)
                .withClaim("lastLogin",System.currentTimeMillis()+MAILEXPIRE_TIME)
                .sign(algorithm);
    }

    public String getMailCode(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("mailCode").asInt().toString();
    }

    public Map<String,String> getTokenData(String token){
        DecodedJWT jwt = JWT.decode(token);
        Map<String,String> map = new HashMap<String, String>();

        map.put("userId",jwt.getClaim("userId").asInt().toString());
        map.put("userName",jwt.getClaim("userName").asString());
        map.put("role",jwt.getClaim("role").asString());

        return map;
    }
}
