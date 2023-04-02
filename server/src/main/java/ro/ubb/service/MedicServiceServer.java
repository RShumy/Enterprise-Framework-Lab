package ro.ubb.service;

import ro.ubb.domain.Medic;
import ro.ubb.domain.Specialty;
import ro.ubb.repository.Repository;
import ro.ubb.validators.Validator;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MedicServiceServer implements MedicService{
    private ExecutorService executorService;
    private Repository<Integer, Medic> medicRepository;
    public static final String AddMedic = "addMedic";

    public MedicServiceServer(Repository<Integer, Medic> medicRepository) {
        this.medicRepository = medicRepository;
    }

    public MedicServiceServer() {}

    @Override
    public void addMedic(String name, String userName, String password, String email,
                         Specialty specialty, LocalDate beginningDate, LocalDate dateOfBirth, Boolean isActive){
            System.out.println("Started adding Medic");
            Medic medic = new Medic(name,userName,password,email,specialty,beginningDate,dateOfBirth,isActive);
            medicRepository.save(medic);
    }

    @Override
    public Medic findOne(Integer id){
        return medicRepository.findOne(id).get();
    }

    @Override
    public List<Medic> findAll(){
        return (List<Medic>) medicRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        if (medicRepository.findOne(id).isPresent())
            medicRepository.delete(id);
    }

    @Override
    public void update(Integer id, String name, String userName, String password, String email, Specialty specialty, LocalDate beginningDate, LocalDate dateOfBirth, Boolean isActive) {
        Medic updatedMedic = new Medic(id, name, userName, password, email, specialty, beginningDate, dateOfBirth, isActive);
        medicRepository.update(updatedMedic);
    }

}
