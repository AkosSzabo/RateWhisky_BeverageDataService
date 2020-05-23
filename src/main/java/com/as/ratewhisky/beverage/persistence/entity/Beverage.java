package com.as.ratewhisky.beverage.persistence.entity;


import lombok.Data;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "beverage")
@Data
public class Beverage {

    @Id
    @GeneratedValue
    @Column( name = "id")
    private Integer id;

    @Column( name = "name")
    private String name;

    @Column( name = "age")
    private Integer age;

    @Column( name = "text")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "distillery_id")
    private Distillery distillery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

}
