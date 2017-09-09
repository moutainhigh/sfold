package com.shifeng.pay.service.pay;

import com.shifeng.pay.entity.pay.AlipayPaymentRecord;
/** 
 * 支付宝支付流水信息(alipay_payment_record)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-03-08 13:42:17 
 */  
public interface AlipayPaymentRecordService {

	/**
	 * 保存支付流水信息
	 * @param paymentRecord
	 */
    void savePaymentRecord(AlipayPaymentRecord paymentRecord);

	
}
