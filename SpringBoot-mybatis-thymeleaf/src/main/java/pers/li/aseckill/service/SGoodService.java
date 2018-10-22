package pers.li.aseckill.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.li.aseckill.dao.SGoodDao;
import pers.li.aseckill.entity.SGoodsSeckill;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.vo.SGoodsVo;

import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class SGoodService {

    private static Logger log= LoggerFactory.getLogger(SGoodService.class);

    @Autowired
    SGoodDao sGoodDao;

    public List<SGoodsVo> listSGoodsVo(){
        List<SGoodsVo> sGoodsVo = sGoodDao.getSGoodsVo();
        return sGoodsVo;
    }

    public SGoodsVo getGoodsVoByGoodsId(long goodsId) {
        return sGoodDao.getSeckillGoodbyId(goodsId);
    }

    public void reduceStock(SGoodsVo goods) {
        SGoodsSeckill sGoodsSeckill=new SGoodsSeckill();
        sGoodsSeckill.setGoodsId(goods.getId());
        sGoodDao.reduceStock(sGoodsSeckill);
    }
}
