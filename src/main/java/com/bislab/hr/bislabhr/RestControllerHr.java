package com.bislab.hr.bislabhr;

import com.bislab.hr.bislabhr.model.Regions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("hr")
public class RestControllerHr {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/regions")
    public List<Regions> regions(){
        List<Regions> regionsList = getSession().createQuery("select r from Regions r", Regions.class).getResultList();
        if(messageSource instanceof ReloadableResourceBundleMessageSource){
            ((ReloadableResourceBundleMessageSource)messageSource).clearCache();
        }
        System.out.println("reloadable: " + messageSource.getMessage("reloadable.message", null, Locale.ENGLISH));
        return regionsList;
    }

    @GetMapping("/regions/{name}")
    public List<Regions> findRegionByName(@PathVariable String name){
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Regions> criteriaQuery = criteriaBuilder.createQuery(Regions.class);
        Root<Regions> regionsRoot = criteriaQuery.from(Regions.class);
        criteriaQuery.select(regionsRoot);
        criteriaQuery.where(criteriaBuilder.like(regionsRoot.get("name"), String.format("%%%s%%", name)));
        Query<Regions> query = getSession().createQuery(criteriaQuery);
        List<Regions> resultList = query.getResultList();
        return resultList;
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

}
