package com.model2.mvc.mapper;

import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.service.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "proTranCode", ignore = true)
    @Mapping(source = "manuDate", target = "manuDate", qualifiedByName = "dateToString")
    Product productEntityToProduct(ProductEntity entity);

    @Mapping(source = "manuDate", target = "manuDate", qualifiedByName = "stringToDate")
//    @Mapping(target = "purchaseEntities", ignore = true)
    ProductEntity productToProductEntity(Product product);

    //List<Product> productEntitiesToProduct(List<ProductEntity> productEntities);

    @Named("dateToString")
    default String dateToString(Date manuDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(manuDate);
    }

    @Named("stringToDate")
    default Date stringToDate(String manuDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(sdf.parse(manuDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
