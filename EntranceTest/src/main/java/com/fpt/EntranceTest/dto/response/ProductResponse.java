package com.fpt.EntranceTest.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    private String name;
    private String description;
    private double price;
    private List<String> image;
    private String category;
    private String style;
    private List<ProductColorSizeResponse> productColorSizeResponses;

}
