package ro.ubb.service;

import ro.ubb.domain.Location;
import ro.ubb.repository.Repository;
import java.util.List;
import java.util.Optional;


public class LocationServiceServer implements LocationService{
    private Repository<Integer, Location> locationRepository;

    public LocationServiceServer(Repository<Integer, Location> locationRepository) {
        this.locationRepository = locationRepository;
    }
    
    @Override
    public void addLocation(String name, String address, String city, String phoneNumber) {
        System.out.println("Started adding Location");
        Location location = new Location(name, address, city, phoneNumber);
        locationRepository.save(location);
    }

    @Override
    public Location findOne(Integer id) {
        return locationRepository.findOne(id).get();
    }

    @Override
    public List<Location> findAll() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
       if (locationRepository.findOne(id).isPresent())
           locationRepository.delete(id);
    }

    @Override
    public void update(Integer id, String name, String address, String city, String phoneNumber) {
        Location updatedLocation = new Location(id, name, address, city, phoneNumber);
        locationRepository.update(updatedLocation);
    }
}
