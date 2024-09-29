package com.fpt.EntranceTest.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private double price;

    private String description;

    private String status;

    private Date createDate;

    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "id_style")
    private Style style;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Categories categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductColorSize> productColorSizes;

}
