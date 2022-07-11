package com.sellers.manager.application.dto;

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

    private Integer regionId;

    @NotNull
    private String regionName;

    @NotNull
    private List<StateEnum> states;

    private Date dhRecordInclusion;

    private Date dhUpdateRecord;
}
