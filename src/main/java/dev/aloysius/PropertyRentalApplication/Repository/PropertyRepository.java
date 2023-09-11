package dev.aloysius.PropertyRentalApplication.Repository;

import dev.aloysius.PropertyRentalApplication.Models.Products.Properties;
import dev.aloysius.PropertyRentalApplication.Models.Products.PropertyStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Properties, Long> {



    List<Properties> findAllByPropertyStatus(PropertyStatus propertyStatus, Pageable pageable);

}
