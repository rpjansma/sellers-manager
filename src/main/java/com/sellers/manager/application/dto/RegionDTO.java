package com.sellers.manager.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {

    private Integer id;

    @JsonProperty("regiao")
    @NotNull(message = "O campo nome deve ser preenchido.")
    private String name;

    @JsonProperty("estados")
    private ArrayList<String> states;

    private Date dhRecordInclusion;

    private Date dhUpdateRecord;
}
