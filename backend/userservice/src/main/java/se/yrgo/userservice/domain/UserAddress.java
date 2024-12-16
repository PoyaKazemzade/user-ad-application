package se.yrgo.userservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "USER_ADDRESS")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;
    private String city;
    private String zipCode;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    @JsonBackReference
    private User user;

    public UserAddress() {
    }

    public UserAddress( String street, String city, String zipCode, User user) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
