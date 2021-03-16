package com.bislab.hr.bislabhr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Audited
@Data
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
@Table(name = "countries")
public class Countries {

    public Countries(){
    }


    @Id
    @Size(min = 2, max = 2)
    @Column(name = "country_id")
    private String id;

    @Column(name = "country_name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "region_id")
    //@RestResource(exported=false)
    @JsonIgnore
    private Regions regions;

}
