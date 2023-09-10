package dev.aloysius.PropertyRentalApplication.Repository;

import dev.aloysius.PropertyRentalApplication.Models.Products.PropertyLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyLocationRepository extends JpaRepository<PropertyLocation, Long>{
}
