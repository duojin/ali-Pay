package com.alipay.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.google.gson.Gson;

public class TestAliPay {

	@Test
	public void TestBuildAppPayInfo() throws AlipayApiException{
		Map<String, Object> bizContent = new HashMap();
        //该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。
        //m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        bizContent.put("timeout_express", "120m");
        //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        bizContent.put("product_code", "QUICK_MSECURITY_PAY");
        
        bizContent.put("subject", "haohao");
        bizContent.put("total_amount", "0.01");
        bizContent.put("out_trade_no", UUID.randomUUID().toString().replaceAll("-", ""));
        
        String rsp = AliPay.buildAppPayInfo(bizContent);
        
        System.out.println(rsp);
        
	}
	
	@Test
	public void TestTradeUqery() throws AlipayApiException{
		Map<String, Object> bizContent = new HashMap();
        bizContent.put("out_trade_no", "1000085401920190611616");
        AlipayTradeQueryResponse rsp = AliPay.queryTrade(bizContent);
        
        System.out.println(new Gson().toJson(rsp));
        
	}
    @Test
    public void TestDownloadBills() throws AlipayApiException{
        Map<String, Object> bizContent = new HashMap();
        bizContent.put("bill_date", "2019-09-30");
        AliPay.downloadBill(bizContent,"E:/a.zip");
    }
	
}
