package ro.ubb.serverConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.domain.Location;
import ro.ubb.domain.Medic;
import ro.ubb.domain.Patient;
import ro.ubb.domain.Procedure;
import ro.ubb.repository.DataBaseRepository;
import ro.ubb.repository.JDBC_Repository;
import ro.ubb.repository.Repository;
import ro.ubb.service.*;

@Configuration
public class ServerConfig {

    final String URL = System.getProperty("url");
    final String USER = System.getProperty("userName");
    final String PASSWORD = System.getProperty("password");

    @Bean
    RmiServiceExporter medicServiceRMIServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("MedicService");
        rmiServiceExporter.setService(new MedicServiceServer(medicRepository()));
        rmiServiceExporter.setServiceInterface(MedicService.class);
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter locationServiceRMIServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("LocationService");
        rmiServiceExporter.setService(new LocationServiceServer(locationRepository()));
        rmiServiceExporter.setServiceInterface(LocationService.class);
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter patientServiceRMIServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("PatientService");
        rmiServiceExporter.setService(new PatientServiceServer(patientRepository()));
        rmiServiceExporter.setServiceInterface(PatientService.class);
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter procedureServiceRMIServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ProcedureService");
        rmiServiceExporter.setService(new ProcedureServiceServer(procedureRepository()));
        rmiServiceExporter.setServiceInterface(ProcedureService.class);
        return rmiServiceExporter;
    }

    //JDBC Generic Repository test -> find all;
    @Bean
    Repository<Integer, Medic> medicRepository(){
        return new JDBC_Repository<>(Medic.class);
    }

    @Bean
    Repository<Integer, Location> locationRepository(){ return new JDBC_Repository<>(Location.class);}

    @Bean
    Repository<Integer, Procedure> procedureRepository(){ return new JDBC_Repository<>(Procedure.class);}

    @Bean
    Repository<Integer, Patient> patientRepository(){ return new JDBC_Repository<>(Patient.class);}
}
