package com.irh.transcation.services;

import com.irh.transaction.model.common.IdentifiableEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Contains helper members for test.
 *
 * @author Iritchie.ren
 * @version 1.1
 */
class TestHelper{

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
    private TestHelper(){
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
    static void resetData() throws Exception{
        ScriptUtils.executeSqlScript(Context.getBean(DataSource.class).getConnection(),
                new ClassPathResource("data.sql"));
    }

    static <T extends IdentifiableEntity> T getEntity(List<T> candidates, long id){
        for(T entity : candidates){
            if(entity.getId() == id){
                return entity;
            }
        }
        return null;
    }
}
