package com.bislab.hr.bislabhr;

import com.bislab.hr.bislabhr.model.Regions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "regions", path = "regions")
public interface RegionsRepository extends JpaRepository<Regions, Integer> {

}
