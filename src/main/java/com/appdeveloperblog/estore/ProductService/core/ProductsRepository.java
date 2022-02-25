package com.appdeveloperblog.estore.ProductService.core;

import com.appdeveloperblog.estore.ProductService.core.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity,String> {

    ProductEntity findByProductId(String productId);
    ProductEntity findByProductIdOrTitle(String productId, String title);
}
