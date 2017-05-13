package com.irh.transaction.controllers.views;

import com.irh.transaction.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by iritchie.ren on 2016/5/23 0023.
 */
@Controller
@RequestMapping(value = "/marketing/member")
public class MemberController extends BaseController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "marketing/member";
    }
}
