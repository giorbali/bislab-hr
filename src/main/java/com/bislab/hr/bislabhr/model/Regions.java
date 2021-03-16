package com.bislab.hr.bislabhr.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Entity
@Audited
@Data
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
@Table(name = "REGIONS")
public class Regions {

    @Id
    @GeneratedValue
    @Column(name = "REGION_ID")
    private Integer id;

    @Column(name = "REGION_NAME")
    private String name;

    @OneToMany(mappedBy = "regions")
    private List<Countries> countriesList;



}
