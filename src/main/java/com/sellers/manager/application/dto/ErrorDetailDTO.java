package com.sellers.manager.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ErrorDetailDTO {
    private LocalDateTime requestDateTime;
    private String detail;
    private String title;
    private Integer code;
}