package dev.aloysius.PropertyRentalApplication.Models.Products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Table(name = "properties_table")
@Entity
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String propertyName;
    private String propertyDescription;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;
    @OneToOne(mappedBy = "properties")
    private PropertyLocation location;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private AppUsers appUsers;
}
