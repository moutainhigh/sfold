package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.domain.MerchantCard;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.marketing.card.open response.
 * 
 * @author auto create
 * @since 1.0, 2017-01-13 12:16:34
 */
public class AlipayMarketingCardOpenResponse extends AlipayResponse {

	private static final long serialVersionUID = 6122375115981926653L;

	/** 
	 * 商户卡信息（包括支付宝分配的业务卡号）
	 */
	@ApiField("card_info")
	private MerchantCard cardInfo;

	public void setCardInfo(MerchantCard cardInfo) {
		this.cardInfo = cardInfo;
	}
	public MerchantCard getCardInfo( ) {
		return this.cardInfo;
	}

}
