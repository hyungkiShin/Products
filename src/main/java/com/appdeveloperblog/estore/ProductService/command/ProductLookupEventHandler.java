package com.appdeveloperblog.estore.ProductService.command;

import com.appdeveloperblog.estore.ProductService.core.data.ProductLookupRepository;
import com.appdeveloperblog.estore.ProductService.core.events.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {

    private final ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {

    }
}
