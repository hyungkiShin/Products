package com.appdeveloperblog.estore.ProductService.rest;

import com.appdeveloperblog.estore.ProductService.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products") // http://localhost:8080/products
public class ProductController {

    private final Environment env;
    private final CommandGateway gateway;

    @Autowired
    public ProductController(Environment env, CommandGateway gateway) {
        this.env = env;
        this.gateway = gateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel model) {
        CreateProductCommand command = CreateProductCommand.builder()
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .title(model.getTitle())
                .productId(UUID.randomUUID().toString()).build();
        String returnValue;
        try {
            returnValue = gateway.sendAndWait(command);
        } catch (Exception ex) {
            returnValue = ex.getLocalizedMessage();
        }
        System.out.println("returnValue " + returnValue);
        return returnValue;
    }

    @GetMapping
    public String getProduct() {
        return "HTTP GET Handled " + env.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduct() {
        return "HTTP PUT Handled";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "HTTP DELETE HANDLED";
    }
}
