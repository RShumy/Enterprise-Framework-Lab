package ro.ubb.service;

import ro.ubb.domain.Location;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface LocationService {

    void addLocation(String name, String address, String city, String phoneNumber);

    Location findOne(Integer id);

    List<Location> findAll();

    void delete(Integer id);

    void update(Integer id, String name, String address, String city, String phoneNumber);
}
