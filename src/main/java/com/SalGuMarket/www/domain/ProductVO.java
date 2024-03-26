package com.SalGuMarket.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

	private Long pno, price;
	private String title, category, sell, state, content, regAt, modAt, sellerEmail, sellerNickName, buyerEmail, buyerNickName;
}
