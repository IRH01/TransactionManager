package com.irh.transaction.controllers.views;

import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.model.product.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The view controller for views to manage {@link Category}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/product/category")
public class CategoryController extends BaseController {

    /**
     * Gets the path of the "product/category" view.
     *
     * @return the path of the "product/category" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "product/category";
    }
}
