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
public class SellerDataAndStatesDTO {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("telefone")
    private String phoneNumber;

    @JsonProperty("idade")
    private Integer age;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    private String state;

    @JsonProperty("estados")
    private ArrayList<String> states;

    @JsonProperty("dataInclusao")
    private Date dhRecordInclusion;
}
