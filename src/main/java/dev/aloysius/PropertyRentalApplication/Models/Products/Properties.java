package dev.aloysius.PropertyRentalApplication.Models.Products;

import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Table(name = "properties_table")
public class Properties {

    private long id;
    private String propertyName;
    private String description;
    @Enumerated(EnumType.STRING)
    private List<PropertyType> propertyType;
    @OneToOne(mappedBy = "property_location_id")
    private PropertyLocation location;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUsers appUsers;
}
