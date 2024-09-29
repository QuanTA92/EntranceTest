package com.fpt.EntranceTest.dto.request;

import lombok.Data;

@Data
public class ProductColorSizeRequest {

    private int idColor;
    private int idSize;
    private int quantity;

}
