package com.bislab.hr.bislabhr;

import com.bislab.hr.bislabhr.model.Countries;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;

public class CustomInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if(entity instanceof Countries){
            if("de".equals(((Countries) entity).getId().toLowerCase(Locale.ROOT))){
                String name = ((Countries) entity).getName();
                ((Countries) entity).setName(name + LocalDateTime.now().getSecond());
                return true;
            }
        } else if(entity instanceof HashMap){
            String name = ((HashMap<String, String>) entity).get("name");
            String result = ((HashMap<String, String>) entity).put("name", name + LocalDateTime.now().getSecond());
            return true;
        }
        return super.onSave(entity, id, state, propertyNames, types);
    }
}
