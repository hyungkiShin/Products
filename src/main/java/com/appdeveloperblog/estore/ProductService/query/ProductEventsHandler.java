package com.appdeveloperblog.estore.ProductService.query;

import com.appdeveloperblog.estore.ProductService.core.events.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    @EventHandler
    public void on(ProductCreatedEvent event) {
    }
}
