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
@Table(name = "SELLER")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SELLER_ID", nullable = false, columnDefinition = "decimal(3,0)")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "NAME", length = 60, nullable = false)
    @NotNull
    private String name;

    @Column(name = "PHONE", length = 15)
    private String phoneNumber;

    @Column(name = "AGE", length = 3)
    private Integer age;

    @Column(name = "CITY", length = 40)
    private String city;

    @Column(name = "STATE", length = 2)
    private String state;

    @JoinColumn(name = "REGION_ID", referencedColumnName = "REGION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Region region;

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

    public ArrayList<String> getRegionStates() {
        return this.getRegion().getStates();
    }

}
