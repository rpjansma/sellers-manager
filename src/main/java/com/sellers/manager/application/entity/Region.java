package com.sellers.manager.application.entity;

import com.sellers.manager.application.enums.StateEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID", nullable = false, columnDefinition = "decimal(3,0)")
    private Integer regionId;

    @Column(name = "REGION_NAME", length = 50, nullable = false)
    @NotNull
    private String regionName;

    @Column(name = "STATES", length = 50, nullable = false)
    @NotNull
    private List<StateEnum> states;

}
