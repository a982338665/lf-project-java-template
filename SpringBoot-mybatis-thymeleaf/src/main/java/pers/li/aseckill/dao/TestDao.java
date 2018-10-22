package pers.li.aseckill.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.li.aseckill.entity.Test;

import java.util.Map;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:26
 */
@Mapper
public interface TestDao {

    @Select("select * from test where id=#{id}")
    Test getTestById(@Param("id") int x);

    @Select("select * from test where id=#{id}")
    Map getByIdMAP(Integer id);

    @Insert("insert into test(id,name) values(#{id},#{name})")
    int insert(Test test);
}
