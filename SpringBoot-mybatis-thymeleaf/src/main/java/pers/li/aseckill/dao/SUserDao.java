package pers.li.aseckill.dao;

import org.apache.ibatis.annotations.*;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.entity.Test;

import java.util.Map;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:26
 */
@Mapper
public interface SUserDao {

    @Select("select * from s_user where id=#{id}")
    SUser getUserById(@Param("id") long mobile);

    @Update("update s_user set password=#{password} where id = #{id}")
    void update(SUser toBeUpdate);

  /*  @Select("select * from test where id=#{id}")
    Map getByIdMAP(Integer id);

    @Insert("insert into test(id,name) values(#{id},#{name})")
    int insert(Test test);*/
}
