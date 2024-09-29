package com.fpt.EntranceTest.modal.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ProductColorSizeKeys {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "id_Color")
    private int idColor;

    @Column(name = "id_Size")
    private int idSize;

    public ProductColorSizeKeys() {
    }

    // Constructor với đối số
    public ProductColorSizeKeys(int idProduct, int idColor, int idSize) {
        this.idProduct = idProduct;
        this.idColor = idColor;
        this.idSize = idSize;
    }
}
