package pers.li.aseckill.entity;

import lombok.Data;

@Data
public class SGoods {
	private Long id;
	private String goodsName;
	private String goodsTitle;
	private String goodsImg;
	private String goodsDetail;
	private Double goodsPrice;
	private Integer goodsStock;

}
