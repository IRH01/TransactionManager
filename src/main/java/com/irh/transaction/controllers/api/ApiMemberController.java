package com.irh.transaction.controllers.api;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.dto.search.VipCardSearchFilter;
import com.irh.transaction.util.exceptions.ApiException;
import com.irh.transaction.util.code.ApiResponse;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.marketing.VipCard;
import com.irh.transaction.model.marketing.VipCardStatus;
import com.irh.transaction.services.VipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iritchie.ren on 2016/5/23 0023.
 */
@RequestMapping(value = "/api/marketing/member")
@RestController
public class ApiMemberController extends BaseController{
    @Autowired
    private VipCardService vipCardService;

    /**
     * The request mapping search list
     *
     * @param params is filter conditions.
     * @return ApiResponse
     * @throws ApiException if exception,It will deal with the exception and return message to view
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse search(@RequestParam Map<String, String> params) throws ApiException{
        try{
            VipCardSearchFilter filter = new VipCardSearchFilter();
            WebHelper.setVipSearchFilter(filter, params, getAccount());
            SearchResult<VipCard> search = this.vipCardService.search(filter);
            Long activeCount = this.vipCardService.statisticsByStatus(filter, VipCardStatus.ACTIVE);
            Long frozenCount = this.vipCardService.statisticsByStatus(filter, VipCardStatus.FROZEN);
            Long disableCount = this.vipCardService.statisticsByStatus(filter, VipCardStatus.DISABLED);
            Map result = new HashMap();
            result.put("search", search);
            result.put("activeCount", activeCount);
            result.put("frozenCount", frozenCount);
            result.put("disableCount", disableCount);
            return new ApiResponse(result);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiMemberController.class.getName() + "#search(params)",
                    new ApiException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * @param vipCard
     * @return ApiResponse
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody VipCard vipCard) throws ApiException{
        try{
            Account account = getAccount();
            vipCard.setBalance(new BigDecimal(0.00));
            vipCard.setInvoiceQuota(new BigDecimal(0.00));
            vipCard.setStatus(VipCardStatus.ACTIVE);
            vipCard.setCreatedAt(new Date());
            vipCard.setHqId(account.getHqId());
            vipCard.setBranch(account.getBranch());
            this.vipCardService.save(vipCard);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiMemberController.class.getName() + "#add(VipCard vipCard)",
                    new ApiException("Erro occurred while processing the request.", ex));
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse update(@RequestBody VipCard vipCard) throws ApiException{
        try{
            if(vipCard.getId() <= 0){
                throw new IllegalArgumentException(ApiMemberController.class.getName() +
                        "#update(id,vipCard) the occurred argument is error:" + vipCard.getId());
            }
            this.vipCardService.update(vipCard);
            return new ApiResponse();
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiMemberController.class.getName() + "#update(vipCard)",
                    new ApiException("Erro occurred while processing the request", ex));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse findOne(@PathVariable long id) throws ApiException{
        try{
            VipCard vipCard = this.vipCardService.findOne(id);
            return new ApiResponse(vipCard);
        }catch(Exception ex){
            throw WebHelper.logException(getLogger(), ApiMemberController.class.getName() + "#findOneById(id)",
                    new ApiException("The error occurred while processing the request"));
        }
    }
}
