package com.irh.transaction.controllers.views;

import com.irh.transaction.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The view controller for views for statistical data of total sales.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/statistic/total")
public class TotalSalesStatisticController extends BaseController {

    /**
     * Gets the path of the "statistic/total" view.
     *
     * @return the path of the "statistic/total" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view(){
        return "statistic/total";
    }
}
