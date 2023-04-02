package ro.ubb.service;

import ro.ubb.domain.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface PatientService {
    void addPatient(String name, String userName, String password, String email,
                    LocalDate dateOfBirth, Boolean hired, Integer cardNumber);

    Patient findOne(Integer id);

    List<Patient> findAll();

    void delete(Integer id);

    void update(Integer id, String name, String userName, String password, String email,
                LocalDate dateOfBirth, Boolean hired, Integer cardNumber);
}
