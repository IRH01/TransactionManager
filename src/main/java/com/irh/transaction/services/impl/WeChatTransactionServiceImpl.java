package com.irh.transaction.services.impl;

import com.irh.transaction.model.transaction.WeChatTransaction;
import com.irh.transaction.services.WeChatTransactionService;
import com.irh.transaction.dao.WeChatTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by iritchie.ren on 16/3/11.
 */
@Service
public class WeChatTransactionServiceImpl implements WeChatTransactionService {

    @Autowired
    private WeChatTransactionMapper weChatTransactionMapper;


    @Override
    public WeChatTransaction findByOutTradeNo(String outTradeNo) {
        return weChatTransactionMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public void save(WeChatTransaction weChatTransaction) {
        weChatTransactionMapper.save(weChatTransaction);
    }
}
