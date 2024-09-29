package com.fpt.EntranceTest.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private Date createDate;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
