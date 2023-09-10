package dev.aloysius.PropertyRentalApplication.Models.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserLocation {
    @Id
    @GeneratedValue
    private int id;
    private String Country;
    private String province;
    private String city;
    private String address;

    @Override
    public String toString() {
        return "UserLocation{" +
                "Country='" + Country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
