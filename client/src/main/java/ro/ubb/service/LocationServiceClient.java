package ro.ubb.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.additional.GenericReflect;
import ro.ubb.domain.Location;
import ro.ubb.domain.Location;
import ro.ubb.message.Message;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class LocationServiceClient implements LocationService, Serializable {

    @Autowired
    LocationService locationService;

    @Override
    public void addLocation(String name, String address, String city, String phoneNumber) {
        locationService.addLocation( name, address, city, phoneNumber);
    }

    @Override
    public Location findOne(Integer id) {
        return locationService.findOne(id);
    }

    @Override
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @Override
    public void delete(Integer id) {
        locationService.delete(id);
    }

    @Override
    public void update(Integer id, String name, String address, String city, String phoneNumber) {
        locationService.update( id, name, address, city, phoneNumber);
    }
}
