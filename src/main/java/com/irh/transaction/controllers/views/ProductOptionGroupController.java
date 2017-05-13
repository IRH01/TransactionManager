package com.irh.transaction.controllers.views;

import com.irh.transaction.controllers.BaseController;

import com.irh.transaction.util.exceptions.HCHCException;
import com.irh.transaction.model.product.ProductOptionGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The view controller for views to manage {@link ProductOptionGroup}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/product/product-option-group")
public class ProductOptionGroupController extends BaseController {
    /**
     * Gets the path of the "product/product-option-group" view.
     *
     * @return the path of the "product/product-option-group" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() throws HCHCException{
        return "product/product-option-group";
    }
}
