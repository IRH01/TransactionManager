package com.irh.transcation.services.impl;

import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The base class for all tests.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public abstract class BaseCase{

    /**
     * Sets up the fixture for test.
     */
    @BeforeClass
    public static void setUpFixture() throws Exception{
        HelperCase.Context = new AnnotationConfigApplicationContext(ConfigCase.class);
        HelperCase.restData();
    }

}
