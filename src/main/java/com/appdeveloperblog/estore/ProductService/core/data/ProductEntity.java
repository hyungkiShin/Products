package com.appdeveloperblog.estore.ProductService.core.data;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "products")
@Data
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = -227264951080660124L;

    @Id
    @Column(unique = true)
    private String productId;

    @Column(unique = true)
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
