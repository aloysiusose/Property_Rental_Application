package dev.aloysius.PropertyRentalApplication.Services;

import dev.aloysius.PropertyRentalApplication.Models.Products.Properties;
import dev.aloysius.PropertyRentalApplication.Repository.AppUserRepository;
import dev.aloysius.PropertyRentalApplication.Repository.PropertyLocationRepository;
import dev.aloysius.PropertyRentalApplication.Repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyLocationRepository propertyLocationRepository;
    private final AppUserRepository appUserRepository;

    public PropertyService(PropertyRepository propertyRepository,
                           PropertyLocationRepository propertyLocationRepository, AppUserRepository appUserRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyLocationRepository = propertyLocationRepository;
        this.appUserRepository = appUserRepository;
    }

    public void  addProperty(Properties properties){

    }
    public void updateProperty(){
        //broker only
    }
    public List<Properties> availableProperties(){
        return propertyRepository.findAll();
        //pagination
    }
    public List<Properties> properties(){
        return propertyRepository.findAll();
    }
}
