package com.irh.transaction.controllers.views;

import com.irh.transaction.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by iritchie on 2016/4/27 0027.
 */
@Controller
@RequestMapping(value = "/statistic/menu")
public class MenuSalesStatisticController extends BaseController{
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "/statistic/menu";
    }
}
