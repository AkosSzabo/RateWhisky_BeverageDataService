package com.as.ratewhisky.beverage.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
@Data
@NoArgsConstructor
public class BeverageResponse extends RepresentationModel<BeverageResponse> {

         private String name;


}
