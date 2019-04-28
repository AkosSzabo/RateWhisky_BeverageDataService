package com.akosszabo.demo.fuelexpenseservice.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "fuel_price")
@Data
public class FuelPriceRow {

    @Id
    @Column( name = "fuel_date")
    private Date fueldate;

    @Column( name = "ulsp_pp")
    private String ulsppp;

    @Column( name = "ulsp_pp_week_diff")
    private String ulspppweekdiff;

    @Column( name = "ulsp_pp_year_diff")
    private String ulspppyeardiff;

    @Column( name = "ulsp_duty")
    private String ulspduty;

    @Column( name = "ulsp_vat")
    private String ulspvat;

    @Column( name = "ulsd_pp")
    private String ulsdpp;

    @Column( name = "ulsd_pp_week_diff")
    private String ulsdppweekdiff;

    @Column( name = "ulsd_pp_year_diff")
    private String ulsdppyeardiff;

    @Column( name = "ulsd_duty")
    private String ulsdduty;

    @Column( name = "ulsd_vat")
    private String ulsdvat;

}
