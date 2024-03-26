package com.SalGuMarket.www.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.ProductDTO;
import com.SalGuMarket.www.domain.ProductVO;
import com.SalGuMarket.www.handler.FileHandler;
import com.SalGuMarket.www.security.AuthMember;
import com.SalGuMarket.www.security.MemberVO;
import com.SalGuMarket.www.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
public class ProductController {
	
	private final ProductService productService;
	private final FileHandler fileHandler;
	
	@GetMapping("/productDetail")
	public String transferProductDetail(@RequestParam("pno") Long pno, Model model) {
		ProductVO pvo = productService.getProductById(pno);
		
		FileVO mainImage = productService.getMainImageByPno(pno);
		mainImage.setSaveDir(mainImage.getSaveDir().replace(File.separator, "/"));
		
		List<FileVO> minorIamgeList = productService.getMinorIamgeListByPno(pno);
		for(FileVO file : minorIamgeList) {
			file.setSaveDir(mainImage.getSaveDir().replace(File.separator, "/"));
		}
		
		model.addAttribute("pvo", pvo);
		model.addAttribute("mainImage", mainImage);
		model.addAttribute("minorIamgeList", minorIamgeList);
		return "/product/productDetail";
	}
	
	// ----------------------------------------------------------------------------------------
	
	@GetMapping("/productSale")
	public void sendProductSale() {}
	
	@PostMapping("/productSale")
	public String saveProduct(ProductVO pvo, RedirectAttributes re, Authentication authentication,
			@RequestParam(name="files1", required = false) MultipartFile[] fileMain,
			@RequestParam(name="files2", required = false) MultipartFile[] filesMinor) {
		
		if(pvo.getCategory().equals("free")) {
			pvo.setSell("n");
		}else {
			pvo.setSell("y");
		}
		
		String SellerEmail = authentication.getName();
		pvo.setSellerEmail(SellerEmail);
		MemberVO mvo = productService.getSellerNickName(SellerEmail);
		pvo.setSellerNickName(mvo.getNickName());
		
		List<FileVO> flistMain = null;
		if(fileMain[0].getSize() > 0 || fileMain != null) {
			flistMain = fileHandler.uploadMainIamgeFile(fileMain);
		}
		List<FileVO> flistMinor = null;
		if(filesMinor[0].getSize() > 0 || filesMinor != null) {
			flistMinor = fileHandler.uploadMinorImageFile(filesMinor);
		}
		
		int isOK = productService.saveProduct(new ProductDTO(pvo, flistMain, flistMinor));
		
		re.addFlashAttribute("saveProduct", isOK);
		return "redirect:/";
	}
	
	@GetMapping("/checkWalletAddress")
	@ResponseBody
	public String getWalletAddress(Authentication authentication) {
		AuthMember authMember = (AuthMember)authentication.getPrincipal();
		String WalletAddress = authMember.getMvo().getWalletAddress();
		log.info(">>> Principal WalletAddress >>> {}", WalletAddress);
		return WalletAddress == null ? "1" : "0";
	}
	
	@PostMapping("/staticBackdropModal")
	public String modifyWalletAddress(@RequestParam("staticBackdropInput") String staticBackdropInput,
			Authentication authentication) {
		AuthMember authMember = (AuthMember)authentication.getPrincipal();
		String loginEmail = authMember.getMvo().getEmail(); 
		int isOK = productService.modifyWalletAddress(staticBackdropInput, loginEmail);
		return isOK > 0 ? "redirect:/product/productSale" : "redirect:/";
	}
}
