package ro.ubb.service;

import ro.ubb.domain.*;
import ro.ubb.domain.Procedure;
import ro.ubb.domain.Procedure;
import ro.ubb.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ProcedureServiceServer implements ProcedureService{

    private Repository<Integer, Procedure> procedureRepository;

    public ProcedureServiceServer(Repository<Integer, Procedure> procedureRepository) {
        this.procedureRepository = procedureRepository;
    }
    
    @Override
    public void addProcedure(String name, Float price, Specialty specialty, Integer duration) {
        System.out.println("Started adding Procedure");
        Procedure procedure = new Procedure(name,price,specialty,duration);
        procedureRepository.save(procedure);
    }

    @Override
    public Procedure findOne(Integer id) {
        return procedureRepository.findOne(id).get();
    }

    @Override
    public List<Procedure> findAll() {
        return (List<Procedure>) procedureRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        if (procedureRepository.findOne(id).isPresent())
            procedureRepository.delete(id);
    }

    @Override
    public void update(Integer idEntity, String name, Float price, Specialty specialty, Integer duration) {
            Procedure updatedProcedure = new Procedure(idEntity, name, price, specialty, duration);
            procedureRepository.update(updatedProcedure);
    }
}
