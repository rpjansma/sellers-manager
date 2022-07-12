package com.sellers.manager.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sellers.manager.application.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SellerDTO {

    private Integer id;

    @JsonProperty("nome")
    @NotNull
    private String name;

    @JsonProperty("telefone")
    private String phoneNumber;

    @JsonProperty("idade")
    private Integer age;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    private String state;

    @JsonProperty("regiao")
    private String region;

    private Date dhRecordInclusion;
    private Date dhUpdateRecord;

}
