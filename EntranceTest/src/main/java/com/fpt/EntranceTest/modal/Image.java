package com.fpt.EntranceTest.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String urlImage;

    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

}
