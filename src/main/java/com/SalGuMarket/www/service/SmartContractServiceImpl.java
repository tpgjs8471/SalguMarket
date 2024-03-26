package com.SalGuMarket.www.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.SalGuMarket.www.repository.SmartContractMapper;
import com.SalGuMarket.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmartContractServiceImpl implements SmartContractService{

	private final SmartContractMapper smartContractMapper;

//	@Override
//	public int getLoginUserWalletAddress(String account) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String loginUserEamil = authentication.getName();
//		
//		MemberVO user = new MemberVO(loginUserEamil, account);
//		int isOK = smartContractMapper.getLoginUserWalletAddress(user);
//		return 0;
//	}
}
