package ro.ubb.service;

import ro.ubb.domain.Medic;
import ro.ubb.domain.Specialty;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface MedicService {
    void addMedic(String name, String userName, String password, String email,
                         Specialty specialty, LocalDate beginningDate, LocalDate dateOfBirth, Boolean isActive);

    Medic findOne(Integer id);

    List<Medic> findAll();

    void delete(Integer id);

    void update(Integer id,String name, String userName, String password, String email,
                Specialty specialty, LocalDate beginningDate, LocalDate dateOfBirth, Boolean isActive);
}
