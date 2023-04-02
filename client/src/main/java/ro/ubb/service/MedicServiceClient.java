package ro.ubb.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.domain.User;

import ro.ubb.additional.GenericReflect;
import ro.ubb.domain.Medic;
import ro.ubb.domain.Specialty;
import ro.ubb.message.Message;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MedicServiceClient implements MedicService, Serializable {

    @Autowired
    public MedicService medicService;

    public MedicServiceClient() {
    }

    @Override
    public void addMedic( String name, String userName, String password, String email, Specialty specialty, LocalDate beginningDate, LocalDate dateOfBirth, Boolean isActive) {
        medicService.addMedic ( name, userName, password, email, specialty, beginningDate, dateOfBirth, isActive);
    }

    @Override
    public Medic findOne(Integer id) {
        return medicService.findOne(id);
    }

    @Override
    public List<Medic> findAll() {
        return medicService.findAll();
    }

    @Override
    public void delete(Integer id) {
        medicService.delete(id);
    }

    @Override
    public void update( Integer id, String name, String userName, String password, String email,
                       Specialty specialty, LocalDate beginningDate, LocalDate dateOfBirth, Boolean isActive) {
        medicService.update( id, name, userName, password, email,
                specialty, beginningDate, dateOfBirth, isActive);
    }


}
