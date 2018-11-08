package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.SeckillBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * create by lishengbo on 2018-01-04 16:01
 */
public class RedisDao {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    //动态生成schema
    //字节码数据--对应相应的类的属性和方法
    private RuntimeSchema<SeckillBean> schema=RuntimeSchema.createFrom(SeckillBean.class);

    private JedisPool jedisPool;

    public RedisDao(String ip,int port){
        jedisPool=new JedisPool(ip,port);
    }

    /**
     * 从redis中取数据  ---自定义序列化
     * @param seckillId
     * @return
     */
    public SeckillBean getSeckill(long seckillId){
        //redis操作逻辑
        try {
            Jedis resource = jedisPool.getResource();
            try {
                String key="seckill:"+seckillId;
                //redis并没有实现内部序列化操作，与memacache d的客户端不同他会在内部进行序列化
                //redi或memacache存储二进制数据，不关心语言是php或java，get对象时获得二进制数组data[]
                //内部对象，，所以需要反序列化拿到对象->Object(seckill)
                //采用自定义序列化（不使用 RedisDao implements Serializable 此种方式）
                //使用protostuff-优点：性能高：
                //查看地址：github.com/eishay/jvm-serializers/wiki
                //对象转化为二进制存入redis  protostuff:pojo即传入的参数必须是一个类有get，set的
                byte[] bytes = resource.get(key.getBytes());
                //缓存中获取到了
                if(bytes!=null){
                    //new一个空对象
                    SeckillBean seckillBean=schema.newMessage();
                    //利用schema工具类转换,将bytes转换进入空对象中
                    ProtobufIOUtil.mergeFrom(bytes,seckillBean,schema);
                    //此时完成被反序列化,利用此中方式，相比jdk自带的序列化，速度比原来的快2倍左右，所占空间压缩至原来的1/10到1/5
                    return seckillBean;
                }

            } finally {
                resource.close();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * redis中存数据
     * @param seckill
     * @return
     */
    public String putSeckill(SeckillBean seckill){
        //对象---转换为--字节数组byte[]（此为序列化过程）
        try {
            Jedis resource = jedisPool.getResource();
            try {
                String key="seckill:"+seckill.getSeckillId();
                //LinkedBuffer.allocate缓存器，数据量大时，使用
                byte[] bytes = ProtobufIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存 一小时  单位为秒
                String setex = resource.setex(key.getBytes(), 60 * 60, bytes);
                //若正确返回ok，错误则返回错误信息
                return setex;
            } finally {
                resource.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return null;
    }
}
