package com.sellers.manager.application.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACTUATION_REGION")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID", nullable = false, columnDefinition = "decimal(3,0)")
    private Integer id;

    @Column(name = "NAME", length = 50, nullable = false)
    @NotNull
    private String name;

    private ArrayList<String> states;

    @Column(name = "INCLUSION_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    private Date dhRecordInclusion;

    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    private Date dhUpdateRecord;

}
