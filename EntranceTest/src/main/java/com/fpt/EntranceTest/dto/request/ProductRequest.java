package com.fpt.EntranceTest.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private int price;
    private int idCategory;
    private int idStyle;
    private MultipartFile[] image;
    private List<ProductColorSizeRequest> productColorSizes;
}
