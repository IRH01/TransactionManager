package com.irh.transaction.controllers.views;

import com.irh.transaction.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The view controller for index view.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    /**
     * Gets the path of the "index" view.
     *
     * @return the path of the "index" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "index";
    }

    /**
     * Gets the path of the "login" view if not authenticated, otherwise redirection to the index.
     *
     * @return the path of the "login" view if not authenticated, otherwise redirection to the index.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return getAccount() == null ? "login" : "redirect:/";
    }
}
