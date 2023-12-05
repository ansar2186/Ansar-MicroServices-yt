package com.ansar.batch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product-table")
@Data
public class Product {
    private  Long id;
    private String title;
    private String author;
    private Integer yearOfPublishing;

}
