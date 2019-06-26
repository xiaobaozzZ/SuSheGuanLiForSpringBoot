package cn.xiaobao.management_system.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.xiaobao.management_system.util.JWTUtils;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取所有的Cookie
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		//循环Cookie集合,如果有一个名叫token的Cookie就取出来并跳出循环
		if(cookies!=null) {
			for (Cookie c : cookies) {
				if("token".equals(c.getName())) {
					cookie = c;
					break;
				}
			}
			//如果得到了这个Cookie就进行验证
			if(cookie!=null) {
				String token = cookie.getValue();
				try {
					JWTUtils.verifyToken(token);
					//token验证成功,放行
					return true;
				} catch (Exception e) {
				}
			}else {
				//没有得到cookie表示没有token不能经过验证跳转回登录页面
				response.sendRedirect("index.html");
				return false;
			}
		}else {
			//没有cookie跳转
			response.sendRedirect("index.html");
			return false;
		}
		return false;
		
	}
	
}
