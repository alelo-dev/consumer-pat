package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.PurchaseDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/purchase")
@Api(value = "Purchase Controller", tags = PurchaseController.TAG_PURCHASE_CONTROLLER)
public class PurchaseController {
	
	static final String TAG_PURCHASE_CONTROLLER = "Purchase Controller";

    @Autowired
    private PurchaseService purchaseService;
    
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Make a purchase", tags = TAG_PURCHASE_CONTROLLER, response = Extract.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful purchase."),
							@ApiResponse(code = 412, message = "Precondition Failed.")})
    public Extract buy(@RequestBody PurchaseDTO purchaseDTO) {
    	try {
    		return purchaseService.buy(purchaseDTO);
    	} catch (BusinessException be) {
    		throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, be.getMessage());
    	}
    }
}
