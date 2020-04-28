package com.as.ratewhisky.beverage.api;

import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Log
public class BeveragesResource {


    @RequestMapping(value = "/api/whisky", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getWhisky() {

    return "";
    }



}
