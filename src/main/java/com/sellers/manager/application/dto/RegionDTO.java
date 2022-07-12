package com.sellers.manager.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sellers.manager.application.enums.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegionDTO {

    private Integer id;

    @JsonProperty("regiao")
    @NotNull
    private String name;

    @JsonProperty("estados")
    @NotNull
    private List<String> states;

    private Date dhRecordInclusion;

    private Date dhUpdateRecord;
}
