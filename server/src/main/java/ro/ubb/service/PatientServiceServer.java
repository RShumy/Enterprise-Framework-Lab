package ro.ubb.service;

import ro.ubb.domain.Patient;
import ro.ubb.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PatientServiceServer implements PatientService{

    private Repository<Integer, Patient> patientRepository;

    public PatientServiceServer(Repository<Integer, Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public void addPatient(String name, String userName, String password, String email,
                           LocalDate dateOfBirth, Boolean hired, Integer cardNumber){
        System.out.println("Started adding Patient");
        Patient patient = new Patient(name, userName, password, email, dateOfBirth, hired, cardNumber);
        patientRepository.save(patient);
    }

    @Override
    public Patient findOne(Integer id){
        return patientRepository.findOne(id).get();
    }

    @Override
    public List<Patient> findAll(){
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
            if (patientRepository.findOne(id).isPresent())
                patientRepository.delete(id);
    }

    @Override
    public void update(Integer id, String name, String userName, String password, String email,
                       LocalDate dateOfBirth, Boolean hired, Integer cardNumber) {
            Patient updatedPatient = new Patient(id, name, userName, password, email, dateOfBirth, hired, cardNumber);
            patientRepository.update(updatedPatient);
        }

}
