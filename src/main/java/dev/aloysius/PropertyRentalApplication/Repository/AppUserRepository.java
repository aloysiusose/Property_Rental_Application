package dev.aloysius.PropertyRentalApplication.Repository;

import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUsers, Long> {
    Optional<AppUsers> findByEmail(String email);
}
