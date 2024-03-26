package com.SalGuMarket.www.service;

import java.util.List;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.domain.ProductDTO;
import com.SalGuMarket.www.domain.ProductVO;
import com.SalGuMarket.www.security.MemberVO;

public interface ProductService {

	List<FileVO> getCategoriesSliderImageList10Image();
	
	ProductVO getProductById(Long pno);

	int saveProduct(ProductDTO productDTO);

	int modifyWalletAddress(String staticBackdropInput, String loginEmail);

	List<FileVO> get8MainImage(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	FileVO getMainImageByPno(Long pno);

	List<FileVO> getMinorIamgeListByPno(Long pno);

	MemberVO getSellerNickName(String sellerEmail);

}
