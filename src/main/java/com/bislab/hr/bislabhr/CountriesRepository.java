package com.bislab.hr.bislabhr;

import com.bislab.hr.bislabhr.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountriesRepository extends JpaRepository<Countries, String> {
}
