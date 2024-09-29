package com.fpt.EntranceTest.modal;

import com.fpt.EntranceTest.modal.keys.ProductColorSizeKeys;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class ProductColorSize {

    @EmbeddedId
    private ProductColorSizeKeys productColorSizeKeys;

    private int quantity;

    private String status;

    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_color", insertable = false, updatable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_size", insertable = false, updatable = false)
    private Size size;
}
