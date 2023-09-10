package dev.aloysius.PropertyRentalApplication.Models.Products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PropertyLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String house_number;
    private String streetName;
    private String landmark;
    private String district;
    private String city;
    private String state;
    private String country;
    @OneToOne
    @JoinColumn(name = "property_id")
    private Properties properties;
}
