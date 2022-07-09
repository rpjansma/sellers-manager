package com.sellers.manager.application.dto;

import com.sellers.manager.application.enums.StateEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ActuationDTO {

    String region;
    List<StateEnum> states;

}
