package com.irh.transaction.services;

import com.irh.transaction.model.transaction.AlipayTransaction;

/**
 * Created by iritchie.ren on 16/3/11.
 */
public interface AlipayTransactionService{
    AlipayTransaction findByOutTradeNo(String outTradeNo);

    void save(AlipayTransaction alipayTransaction);
}
