package com.irh.transaction.services;

import com.google.zxing.WriterException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: iritchie.ren
 * Date: 2016/2/24
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
public interface QcodeService{


    /**
     * 生成二维码，返回二维码httpUrl
     *
     * @param content
     * @return
     */
    String gerateQcodeUrl(String content) throws WriterException, IOException;
}
