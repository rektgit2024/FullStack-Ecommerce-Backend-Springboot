package com.practice.fullstackbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long storeId;
    private String storeName;
    private String storeDescription;
    private String address;
    private String contactNumber;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Product> product = new ArrayList<>();

    @OneToOne
    private User user;

}