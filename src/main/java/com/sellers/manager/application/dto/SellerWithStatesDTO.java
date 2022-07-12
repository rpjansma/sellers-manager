package com.sellers.manager.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerWithStatesDTO {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("estados")
    private ArrayList<String> states;

    @JsonProperty("dataInclusao")
    private Date dhRecordInclusion;

}
