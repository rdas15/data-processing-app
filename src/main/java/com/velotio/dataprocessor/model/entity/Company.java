package com.velotio.dataprocessor.model.entity;

import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortName;
    private String name;
    private Date incorporationDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.ALL)
    private List<Address> addresses = new java.util.ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.ALL)
    private List<Product> products = new java.util.ArrayList<>();

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getIncorporationDate() {
        return incorporationDate;
    }

    public void setIncorporationDate(Date incorporationDate) {
        this.incorporationDate = incorporationDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", name='" + name + '\'' +
                ", incorporationDate=" + incorporationDate +
                ", addresses=" + addresses +
                ", products=" + products +
                '}';
    }
}
