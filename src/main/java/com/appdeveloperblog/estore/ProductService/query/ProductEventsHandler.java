package com.appdeveloperblog.estore.ProductService.query;

import com.appdeveloperblog.estore.ProductService.core.ProductsRepository;
import com.appdeveloperblog.estore.ProductService.core.data.ProductEntity;
import com.appdeveloperblog.estore.ProductService.core.events.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        productsRepository.save(productEntity);
    }
}
