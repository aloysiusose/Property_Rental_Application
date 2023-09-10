package dev.aloysius.PropertyRentalApplication.Repository;

import dev.aloysius.PropertyRentalApplication.Models.Users.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<UserLocation, Integer> {

}
