package com.model2.mvc.mapper;

import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.entity.PurchaseEntity;
import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    @Mapping(source = "buyer", target = "userEntity", qualifiedByName = "getBuyerId")
    @Mapping(source = "purchaseProd", target = "productEntity", qualifiedByName = "getProdNo")
    PurchaseEntity purchaseToPurchaseEntity(Purchase purchase);

    @Mapping(source = "userEntity", target = "buyer", qualifiedByName = "getBuyer")
    @Mapping(source = "productEntity", target = "purchaseProd", qualifiedByName = "getProduct")
    Purchase purchaseEntityToPurchase(PurchaseEntity purchaseEntity);

    @Named("getBuyerId")
    default UserEntity getBuyerId(User buyer){
        return UserEntity.builder().userId(buyer.getUserId()).build();
    }

    @Named("getProdNo")
    default ProductEntity getProdNo(Product purchaseProd){
        return ProductEntity.builder().prodNo(purchaseProd.getProdNo()).build();
    }

    @Named("getBuyer")
    default User getBuyer(UserEntity userEntity){
        return User.builder().userId(userEntity.getUserId()).build();
    }

    @Named("getProduct")
    default Product getProduct(ProductEntity productEntity){
        return Product.builder().prodNo(productEntity.getProdNo()).build();
    }

}
