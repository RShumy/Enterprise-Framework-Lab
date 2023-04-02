package ro.ubb.clientConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.service.*;
import ro.ubb.ui.ClientConsole;


@Configuration
public class ClientConfig {
    @Bean
    RmiProxyFactoryBean medicServiceProxyRMI() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/MedicService");
        rmiProxyFactoryBean.setServiceInterface(MedicService.class);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean locationServiceProxyRMI() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/LocationService");
        rmiProxyFactoryBean.setServiceInterface(LocationService.class);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean patientServiceProxyRMI() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/PatientService");
        rmiProxyFactoryBean.setServiceInterface(PatientService.class);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean procedureServiceProxyRMI() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/ProcedureService");
        rmiProxyFactoryBean.setServiceInterface(ProcedureService.class);
        return rmiProxyFactoryBean;
    }

    @Bean
    ClientConsole clientConsole() {
        ClientConsole clientConsole = new ClientConsole(medicServiceClient(), patientServiceClient(), locationServiceClient(), procedureServiceClient());
        return clientConsole;
    }

    @Bean
    MedicService medicServiceClient() {
        return new MedicServiceClient();
    }
    
    @Bean
    LocationService locationServiceClient() {
        return new LocationServiceClient();
    }

    @Bean
    PatientService patientServiceClient() {
        return new PatientServiceClient();
    }

    @Bean
    ProcedureService procedureServiceClient() {
        return new ProcedureServiceClient();
    }
}

