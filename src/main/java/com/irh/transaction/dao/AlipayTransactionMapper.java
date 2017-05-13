package com.irh.transaction.dao;


import com.irh.transaction.model.transaction.AlipayTransaction;

/**
 * Created by iritchie.ren on 16/3/11.
 */
public interface AlipayTransactionMapper{
    AlipayTransaction findByOutTradeNo(String outTradeNo);

    void save(AlipayTransaction alipayTransaction);
}
