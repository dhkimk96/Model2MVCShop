package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.mapper.PurchaseMapper;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
      
	///Field
	private final PurchaseDao purchaseDao;
	private final PurchaseMapper purchaseMapper;

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		System.out.println("in ProductServiceImpl:: purchase::"+ purchase);
		purchaseDao.addPurchase(purchase);
	}
	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.getPurchase(tranNo);
	}
	@Override
	public Map<String, Object> getPurchaseList(Search search) throws Exception {
		List<Purchase> list= purchaseDao.getPurchaseList(search);
		int totalCount = purchaseDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		System.out.println("PurchaseServiceImpl :: updatePurchase");
		purchaseDao.updatePurchase(purchase);
	}
	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		System.out.println("PurchaseServiceImpl :: updateTranCode");
		purchaseDao.updateTranCode(purchase);
	}

}
