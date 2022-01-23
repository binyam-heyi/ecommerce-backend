package com.binyam.ecommerce.config;

import com.binyam.ecommerce.entity.Country;
import com.binyam.ecommerce.entity.Product;
import com.binyam.ecommerce.entity.ProductCategory;
import com.binyam.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedActions= {HttpMethod.DELETE,HttpMethod.POST,HttpMethod.PUT};
        // Disable the methods for product : PUT, POST and DELETE
        disableHttpMethods(config, unsupportedActions,Product.class);
        // Disable the methods for productCategory : PUT, POST and DELETE
        disableHttpMethods(config, unsupportedActions,ProductCategory.class);
        // Disable the methods for country : PUT, POST and DELETE
        disableHttpMethods(config, unsupportedActions, Country.class);
        // Disable the methods for state : PUT, POST and DELETE
        disableHttpMethods(config, unsupportedActions, State.class);
        // Expose the category Ids
        exposeIds(config);


    }

    private void disableHttpMethods(RepositoryRestConfiguration config, HttpMethod[] unsupportedActions, Class theClass) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities= entityManager.getMetamodel().getEntities();
        List<Class> entityClases= new ArrayList<>();
        for (EntityType entityType: entities) {
            entityClases.add(entityType.getJavaType());
        }
        Class[] domainTypes= entityClases.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }
}
