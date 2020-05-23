package com.as.ratewhisky.beverage.persistence.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distillery")
@Data
public class Distillery {

    @Id
    @Column( name = "id")
    private Integer id;

    @Column( name = "name")
    private String name;
}
