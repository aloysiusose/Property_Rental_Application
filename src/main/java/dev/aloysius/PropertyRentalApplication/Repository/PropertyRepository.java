package dev.aloysius.PropertyRentalApplication.Repository;

import dev.aloysius.PropertyRentalApplication.Models.Products.Properties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Properties, Long> {
}
