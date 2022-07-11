package com.sellers.manager.application.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SELLER")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SELLER_ID", nullable = false, columnDefinition = "decimal(3,0)")
    private Integer id;

    @Column(name = "SELLER_NAME", length = 60, nullable = false)
    @NotNull
    private String name;

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
