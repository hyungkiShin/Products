package com.appdeveloperblog.estore.ProductService.command.interceptor;

import com.appdeveloperblog.estore.ProductService.command.CreateProductCommand;
import com.appdeveloperblog.estore.ProductService.core.data.ProductLookupEntity;
import com.appdeveloperblog.estore.ProductService.core.data.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    // 제품 조회
    private final ProductLookupRepository productLookupRepository;

    private  static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {

            LOGGER.info("Intercepted command: " + command.getPayloadType());

            if(CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                // 제품이 DB 에 존재하는지 검사
                ProductLookupEntity productLookupEntity = productLookupRepository
                        .findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
                if(productLookupEntity != null) {
                    throw new IllegalStateException(
                            String.format("Product with productId %s or title %s already exist",
                                    createProductCommand,createProductCommand.getProductId(), createProductCommand.getTitle())
                    );
                }
            }
            return command;
        };
    }
}
