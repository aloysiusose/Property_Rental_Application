package dev.aloysius.PropertyRentalApplication.Services;

import dev.aloysius.PropertyRentalApplication.Models.Products.Properties;
import dev.aloysius.PropertyRentalApplication.Models.Products.PropertyStatus;
import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import dev.aloysius.PropertyRentalApplication.Repository.AppUserRepository;
import dev.aloysius.PropertyRentalApplication.Repository.PropertyLocationRepository;
import dev.aloysius.PropertyRentalApplication.Repository.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUsers appUsers = appUserRepository.findByEmail(name).get();
        properties.setAppUsers(appUsers);
        propertyRepository.save(properties);


        //appUserRepository.findByEmail(name).ifPresent(appUserRepository.save(x -> x.getPropertiesList().add(properties)));

    }
    public void updateProperty(){
        //broker only
    }
    public List<Properties> availableProperties(int page, int size){

        PropertyStatus available = PropertyStatus.AVAILABLE;
        return propertyRepository.findAllByPropertyStatus(available, PageRequest.of(page, size));
    }
    public Page<Properties> properties(int page, int size){
        return propertyRepository.findAll(PageRequest.of(page, size));
    }
}
