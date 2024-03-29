package com.sellers.manager.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {

    private Integer id;

    @JsonProperty("nome")
    @NotNull(message = "O campo nome deve ser preenchido.")
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

}
