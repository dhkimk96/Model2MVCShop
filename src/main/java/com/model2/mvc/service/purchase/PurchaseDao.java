package com.model2.mvc.service.purchase;

import com.model2.mvc.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDao extends JpaRepository<PurchaseEntity, Integer> {

	
}