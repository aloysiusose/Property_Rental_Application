package dev.aloysius.PropertyRentalApplication.Models.Users;

import dev.aloysius.PropertyRentalApplication.Models.Products.Properties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class AppUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userPassword;
    @OneToOne
    @JoinColumn(name = "user_location_id")
    private UserLocation location;
    @Enumerated(EnumType.STRING)
    private UserCategory userCategory;
    @OneToMany(mappedBy = "appUsers")
    private List<Properties> propertiesList;

    public AppUsers(AppUsers users){
        this.firstName = users.firstName;
        this.lastName = users.lastName;
        this.email = users.email;
        this.userPassword = users.userPassword;
        this.userCategory = users.userCategory;
    }
    public AppUsers(){

    }
}
