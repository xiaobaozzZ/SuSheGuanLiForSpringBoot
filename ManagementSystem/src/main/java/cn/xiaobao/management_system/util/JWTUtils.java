package cn.xiaobao.management_system.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
/**
 * 生成和验证Token的工具类
 * @author xiaobao
 *
 */
public class JWTUtils {
	private static String secret = "xxx";
	private static Algorithm alg = Algorithm.HMAC256(secret);
	
	public static String getToken(String username) {
		Date createTime = new Date();
		String token = JWT.create()
				.withIssuer("小宝")//发行者
				.withIssuedAt(createTime)//发行时间
				.withSubject(username)//用户名身份验证
				.withExpiresAt(new Date(createTime.getTime()+1000L*60*24))
				.sign(alg);
		return token;
	}
	public static boolean verifyToken(String token) {
		boolean flag = false;
		JWTVerifier verifier = JWT.require(alg).build();
		try {
			verifier.verify(token);
			flag = true;
		} catch (JWTVerificationException e) {
		}
		return flag;
	}
	
}
