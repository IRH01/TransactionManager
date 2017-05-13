package com.irh.transaction.controllers.views;

import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.model.branch.PrintType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The view controller for views to manage {@link PrintType}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/branch/print-type")
public class PrintTypeController extends BaseController {
    /**
     * Gets the path of the "branch/print-types" view.
     *
     * @return the path of the "branch/print-types" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "branch/print-type";
    }

    /**
     * Gets the path of the "branch/print-type" view.
     *
     * @param id
     *         the print type id.
     *
     * @return path of the "branch/print-type" view.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id) {
        return "branch/print-type";
    }
}
