package com.irh.transaction.controllers.views;

import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.model.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The view controller for views to manage {@link Product}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/product/product")
public class ProductController extends BaseController {

    /**
     * Gets the path of the "product/product" view.
     *
     * @return the path of the "product/product" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "product/product";
    }
}
