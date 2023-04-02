package ro.ubb.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.additional.GenericReflect;
import ro.ubb.domain.*;
import ro.ubb.domain.Procedure;
import ro.ubb.domain.Procedure;
import ro.ubb.message.Message;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ProcedureServiceClient implements ProcedureService, Serializable {

    @Autowired

    ProcedureService procedureService;

    public ProcedureServiceClient(){
    }

    @Override
    public void addProcedure(String name, Float price, Specialty specialty, Integer duration) {
        procedureService.addProcedure(name, price, specialty, duration);
    }

    @Override
    public Procedure findOne(Integer id) {
        return procedureService.findOne(id);
    }

    @Override
    public List<Procedure> findAll() {
        return procedureService.findAll();
    }

    @Override
    public void delete(Integer id) {
        procedureService.delete(id);
    }

    @Override
    public void update(Integer idEntity, String name, Float price, Specialty specialty, Integer duration) {
        procedureService.update(idEntity, name, price, specialty, duration);
    }
}
