package com.irh.transcation.services;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The base class for all tests.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public abstract class BaseTest{

    /**
     * Sets up the fixture for test.
     */
    @BeforeClass
    public static void setUpFixture(){
        TestHelper.Context = new AnnotationConfigApplicationContext(TestConfig.class);
    }

    /**
     * Tears down the fixture.
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownFixture() throws Exception{
        TestHelper.resetData();
    }
}
