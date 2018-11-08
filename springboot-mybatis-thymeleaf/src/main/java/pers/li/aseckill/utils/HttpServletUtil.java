package pers.li.aseckill.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:luofeng
 * @createTime : 2018/10/11 11:37
 * @description: 获取当前会话的request和response:在获取时，可能会出现空指针异常
 */
@Deprecated
public class HttpServletUtil {

    private static RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    private static WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

    public static <T> T getHttpServletObject( Class<T> clazz){
        if(clazz == HttpServletRequest.class){
            return (T) ((ServletRequestAttributes)ra).getRequest();
        }else if(clazz == HttpServletResponse.class){
            return (T) ((ServletRequestAttributes) ra).getResponse();
        }
        return  null;
    }


    public  static ServletContext getServletContext(){
        ServletContext servletContext = webApplicationContext.getServletContext();
        return servletContext;
    }
}
