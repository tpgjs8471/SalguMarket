
package com.SalGuMarket.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SalGuMarket.www.service.SmartContractService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/smartContract/*")
public class SmartContractController {

	private final SmartContractService smartContractService;
	
	@PostMapping("/getLoginUserWalletAddress")
	public String getLoginUserWalletAddress(@RequestBody String account) {
		int isOK = smartContractService.getLoginUserWalletAddress(account);
		return isOK > 0 ? "1":"-1";
	}
	
	@GetMapping("/productDetail")
	public String pro() {
		return "product/productDetail";
	}
}
