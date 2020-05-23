package com.as.ratewhisky.beverage.persistence.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region")
@Data
public class Region {

    @Id
    @Column( name = "id")
    private Integer id;

    @Column( name = "name")
    private String name;
}
