package com.irh.transcation.services.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Contains helper members for test.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
class HelperCase{

    /**
     * The headquarter id.
     */
    static final long HQ_ID = 1;

    /**
     * The headquarter code.
     */
    static final String HQ_CODE = "1";

    /**
     * The {@link ApplicationContext} instance for injection.
     */
    static ApplicationContext Context;

    /**
     * Prevents this class from being initialized.
     */
    private HelperCase(){
    }

    /**
     * Parses the given date value.
     *
     * @param datetime the value indicates if date and time will be parsed.
     * @param value    the date value in string
     * @return the resulted date.
     * @throws ParseException
     */
    static Date parseDate(boolean datetime, String value) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat(datetime ?
                "yyyy-MM-dd HH:mm" : "yyyy-MM-dd");
        return dateFormat.parse(value);
    }

    /**
     * Resets the test data.
     *
     * @throws Exception
     */
    static void restData() throws Exception{
        ScriptUtils.executeSqlScript(Context.getBean(DataSource.class).getConnection(),
                new ClassPathResource("data_irh.sql"));
    }


}
