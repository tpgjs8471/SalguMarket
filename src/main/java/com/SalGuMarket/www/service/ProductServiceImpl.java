package com.SalGuMarket.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.domain.ProductDTO;
import com.SalGuMarket.www.domain.ProductVO;
import com.SalGuMarket.www.repository.FileMapper;
import com.SalGuMarket.www.repository.MemberMapper;
import com.SalGuMarket.www.repository.ProductMapper;
import com.SalGuMarket.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductMapper productMapper;
	private final FileMapper fileMapper;
	private final MemberMapper memberMapper;

	@Override
	public ProductVO getProductById(Long pno) {
		return productMapper.getProductById(pno);
	}

	@Override
	public List<FileVO> getCategoriesSliderImageList10Image() {
		return fileMapper.getCategoriesSliderImageList10Image();
	}

	@Override
	@Transactional
	public int saveProduct(ProductDTO pdto) {
		int isOK = productMapper.saveProduct(pdto.getPvo());
		
		if(isOK > 0) {
			long pno = productMapper.getRecentPno();
			// 위 productMapper.saveProduct(pDTO.getPvo()); 구문으로 생성된 Pno이다.
			
			for(FileVO fvo : pdto.getFlist1()) {
				fvo.setPno(pno);
				fvo.setMainImage(1);
				isOK *= fileMapper.saveProductFile(fvo);
			}
			
			for(FileVO fvo : pdto.getFlist2()) {
				fvo.setPno(pno);
				fvo.setMainImage(0);
				isOK *= fileMapper.saveProductFile(fvo);
			}
		}
		
		return isOK;
	}

	@Override
	public int modifyWalletAddress(String staticBackdropInput, String loginEmail) {
		return productMapper.modifyWalletAddress(staticBackdropInput, loginEmail);
	}

	@Override
	public List<FileVO> get8MainImage(PagingVO pgvo) {
		return fileMapper.get8MainImage(pgvo);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return productMapper.getTotalCount(pgvo);
	}

	@Override
	public FileVO getMainImageByPno(Long pno) {
		return fileMapper.getMainImageByPno(pno);
	}

	@Override
	public List<FileVO> getMinorIamgeListByPno(Long pno) {
		return fileMapper.getMinorIamgeListByPno(pno);
	}

	@Override
	public MemberVO getSellerNickName(String sellerEmail) {
		return memberMapper.selectEmail(sellerEmail);
	}
}
