package ro.ubb.domain;

import java.io.Serializable;

public class Location extends BaseEntity<Integer> implements Serializable {
    private String name;
    private String address;
    private String city;
    private String phoneNumber;

    public Location(){}

    public Location(String name, String address, String city, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public Location(Integer idEntity, String name, String address, String city, String phoneNumber) {
        super(idEntity);
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Location{" +
                "idEntity=" + idEntity +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
