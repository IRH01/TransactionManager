package com.irh.transaction.services.impl;


import com.irh.transaction.dao.AlipayTransactionMapper;
import com.irh.transaction.model.transaction.AlipayTransaction;
import com.irh.transaction.services.AlipayTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by iritchie.ren on 16/3/11.
 */
@Service
public class AlipayTransactionServiceImpl implements AlipayTransactionService{

    @Autowired
    private AlipayTransactionMapper alipayTransactionMapper;

    @Override
    public AlipayTransaction findByOutTradeNo(String outTradeNo) {
        return alipayTransactionMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public void save(AlipayTransaction alipayTransaction) {
        alipayTransactionMapper.save(alipayTransaction);
    }
}
