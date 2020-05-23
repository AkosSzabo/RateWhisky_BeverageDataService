package com.as.ratewhisky.beverage.api;

import com.as.ratewhisky.beverage.api.request.BeverageUpdateRequest;
import com.as.ratewhisky.beverage.api.response.BeverageResponse;
import com.as.ratewhisky.beverage.persistence.BeverageRepository;
import com.as.ratewhisky.beverage.service.BeverageService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Validated
@Log
public class BeveragesResource {

    private BeverageService beverageService;
    private ConversionService conversionService;

    @Autowired
    public BeveragesResource(final BeverageService beverageService,final ConversionService conversionService){
        this.beverageService = beverageService;
        this.conversionService = conversionService;
    }

    @RequestMapping(value = "/api/beverage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<BeverageResponse> getBeverageById(@RequestParam @NotNull final Integer id) {
        BeverageResponse response = conversionService.convert(beverageService.getBeverageById(id).get(),BeverageResponse.class).add(linkTo(methodOn(BeveragesResource.class).getBeverageById(id)).withSelfRel());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/beverage/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBeverage(BeverageUpdateRequest request) {
        List<BeverageResponse> response = beverageService.getAllBeverages().stream().map( b -> conversionService.convert(b,BeverageResponse.class).add(linkTo(methodOn(BeveragesResource.class).getBeverageById(b.getId())).withSelfRel())).collect(Collectors.toList());
        // beverageRepository.save()
        return new ResponseEntity<List>(response, null, HttpStatus.OK);
    }



}
