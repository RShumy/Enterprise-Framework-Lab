package ro.ubb.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.additional.GenericReflect;
import ro.ubb.domain.Patient;
import ro.ubb.message.Message;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PatientServiceClient implements PatientService, Serializable {

    @Autowired
    PatientService patientService;

    public PatientServiceClient() {
    }

    @Override
    public void addPatient(String name, String userName, String password, String email,
                           LocalDate dateOfBirth, Boolean hired, Integer cardNumber) {
            patientService.addPatient(name, userName, password, email, dateOfBirth, hired, cardNumber);
    }

    @Override
    public Patient findOne(Integer id) {
            return patientService.findOne(id);
    }

    @Override
    public List<Patient> findAll() {
            return patientService.findAll();
    }

    @Override
    public void delete(Integer id) {
       patientService.delete(id);
    }

    @Override
    public void update(Integer id, String name, String userName, String password, String email,
                       LocalDate dateOfBirth, Boolean hired, Integer cardNumber) {
       patientService.update(id, name, userName, password, email, dateOfBirth, hired, cardNumber);
    }

}
