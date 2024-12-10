package se.yrgo.userservice.domain;

public class user_address {
    private int user_id;
    private String address;
    private String postal_code;
    private String city;

    public user_address(int user_id, String address, String postal_code, String city) {
        this.user_id = user_id;
        this.address = address;
        this.postal_code = postal_code;
        this.city = city;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
