package pers.li.aseckill.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.service.SUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * controller直接接收SUser对象解析赋值
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	SUserService userService;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz = parameter.getParameterType();
		//判断是否为SUser类，是则向下执行resolveArgument 解析
		return clazz==SUser.class;
	}

	/**
	 * Controller中使用SUser接收参数组装-->返回结果
	 * @param parameter
	 * @param mavContainer
	 * @param webRequest
	 * @param binderFactory
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		
		String paramToken = request.getParameter(SUserService.TOKEN_COOKIE);
		String cookieToken = getCookieValue(request, SUserService.TOKEN_COOKIE);
		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
		return userService.getByToken(response, token);
	}

	private String getCookieValue(HttpServletRequest request, String cookiName) {
		Cookie[]  cookies = request.getCookies();
		if(cookies!=null){
				for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookiName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}
