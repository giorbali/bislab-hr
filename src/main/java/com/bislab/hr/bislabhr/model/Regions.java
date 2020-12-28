package com.bislab.hr.bislabhr.model;

import javax.persistence.*;

@Entity
@Table(name = "REGIONS")
public class Regions {

    @Id
    @GeneratedValue
    @Column(name = "REGION_ID")
    private int id;

    @Column(name = "REGION_NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
