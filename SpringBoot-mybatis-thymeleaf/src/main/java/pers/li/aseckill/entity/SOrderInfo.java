package pers.li.aseckill.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SOrderInfo {
	private Long id;
	private Long userId;
	private Long goodsId;
	private Long  deliveryAddrId;
	private String goodsName;
	private Integer goodsCount;
	private Double goodsPrice;
	private Double seckillPrice;
	private Integer orderChannel;
	private Integer status;
	private Date createTime;
	private Date payTime;

}
