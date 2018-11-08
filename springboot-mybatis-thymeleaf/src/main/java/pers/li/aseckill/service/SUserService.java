package pers.li.aseckill.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.li.aseckill.dao.SUserDao;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.exception.GlobalException;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.redis.service.SUserKey;
import pers.li.aseckill.result.CodeMsg;
import pers.li.aseckill.utils.MD5Util;
import pers.li.aseckill.utils.UUIDUtil;
import pers.li.aseckill.vo.LoginVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class SUserService {

    private static Logger log= LoggerFactory.getLogger(SUserService.class);
    public static final String TOKEN_COOKIE="token";

    @Autowired
    SUserDao sUserDao;
    @Autowired
    RedisService redisService;

    /**
     * 优化点：设置对象级缓存永久有效
     * @param mobile
     * @return
     */
    public SUser getById(long mobile){
        //取缓存
        SUser user = redisService.get(SUserKey.getById, ""+mobile, SUser.class);
        if(user != null) {
            return user;
        }
        //取数据库
        user = sUserDao.getUserById(mobile);
        if(user != null) {
            redisService.set(SUserKey.getById, ""+mobile, user);
        }
        return user;
    }

    /**
     * 修改时，要修改对象缓存--永久性
     * @param token
     * @param id
     * @param formPass
     * @return
     */
    // http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
    public boolean updatePassword(String token, long id, String formPass) {
        //取user
        SUser user = getById(id);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        SUser toBeUpdate = new SUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        sUserDao.update(toBeUpdate);
        //处理缓存
        redisService.delete(SUserKey.getById, ""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(SUserKey.token, token, user);
        return true;
    }



    public String login(HttpServletResponse response,LoginVo loginVo) {
        log.info("------------------------->");
        if(loginVo==null){
            throw new GlobalException( CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //判断手机号是否存在
        SUser byId = getById(Long.parseLong(mobile));
        if(byId==null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbpass=byId.getPassword();
        String dbsalt=byId.getSalt();
        String toDBPass = MD5Util.formPassToDBPass(password, dbsalt);
        if(!dbpass.equals(toDBPass)){
            throw new GlobalException( CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie,有效期设置为与session一致
        String token = UUIDUtil.uuid();
        addCookie(response,token,byId);
        return token;


    }

    private void addCookie(HttpServletResponse response,String token,SUser byId) {
        log.info("==================》");
        redisService.set(SUserKey.token,token,byId);
        Cookie cookie = new Cookie(TOKEN_COOKIE,token);
        cookie.setMaxAge(SUserKey.token.getExpireSeconds());
        cookie.setPath("/");
//        HttpServletResponse response = HttpServletUtil.getHttpServletObject(HttpServletResponse.class);
        response.addCookie(cookie);
    }

    public SUser getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        SUser sUser = redisService.get(SUserKey.token, token, SUser.class);
        if(sUser!=null){
            //延长token有效期--新建token--使用原有token
            addCookie(response, token,sUser);
        }
        return sUser;
    }
}
