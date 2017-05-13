package com.irh.transcation.services.impl;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by iritchie.ren on 2016/5/24 0024.
 */
public class UtilsTest{
    @Test
    public void format1(){
        BigDecimal bd = new BigDecimal(10);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println(bd);
    }
}
