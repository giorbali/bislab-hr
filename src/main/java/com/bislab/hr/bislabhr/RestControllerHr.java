package com.bislab.hr.bislabhr;

import com.bislab.hr.bislabhr.model.Countries;
import com.bislab.hr.bislabhr.model.Regions;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.LogManager;

@RestController
@RequestMapping("hr")
public class RestControllerHr {

    Logger logger = LoggerFactory.getLogger(RestControllerHr.class);

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private CountriesRepository countriesRepository;

    //@GetMapping("/regions")
    public ResponseEntity<List<Regions>> regions(){
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        List<Regions> regionsList = entityManager.createQuery("select r from Regions r", Regions.class).getResultList();
        if(messageSource instanceof ReloadableResourceBundleMessageSource){
            ((ReloadableResourceBundleMessageSource)messageSource).clearCache();
        }
        if(logger.isDebugEnabled()){
        logger.debug("reloadable: " + messageSource.getMessage("reloadable.message", null, Locale.ENGLISH));
        }
        return Optional.of(regionsList).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    //@GetMapping("/regions/{name}")
    public List<Regions> findRegionByName(@PathVariable String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Regions> criteriaQuery = criteriaBuilder.createQuery(Regions.class);
        Root<Regions> regionsRoot = criteriaQuery.from(Regions.class);
        criteriaQuery.select(regionsRoot);
        criteriaQuery.where(criteriaBuilder.like(regionsRoot.get("name"), String.format("%%%s%%", name)));
        Query<Regions> query = (Query<Regions>) entityManager.createQuery(criteriaQuery);
        List<Regions> resultList = query.getResultList();
        if(CollectionUtils.isEmpty(resultList)){
            throw new IllegalArgumentException("nothing found");
        }
        return resultList;
    }


    //@PostMapping("/countries/{id}")
    public ResponseEntity<Countries> saveCountries(@PathVariable String id, @RequestBody Countries countriesModified){

        Optional<Countries> countries = countriesRepository.findById(id);
        if(countries.isPresent()){
            if(countriesModified.getRegions() == null){
                countriesModified.setRegions(countries.get().getRegions());
            }
            Countries result = countriesRepository.save(countriesModified);
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/countries/{id}")
    public void delete(@PathVariable long id){
        System.out.println("DELETE country id:" + id);
    }


}
