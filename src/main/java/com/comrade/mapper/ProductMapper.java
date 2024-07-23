package com.comrade.mapper;

import com.comrade.domine.ProductEntity;
import com.comrade.model.ProductModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toProductEntity(ProductModel productModel) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productModel, productEntity);
        return productEntity;
    }

    public ProductModel toProductModel(ProductEntity productEntity) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productEntity, productModel);
        return productModel;
    }
}
