import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApp {
    public static void main(String[] args) {

        new AnnotationConfigApplicationContext("ro.ubb.serverConfig");

//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//
//        Repository<Integer, Medic> medicRepository = new DataBaseRepository(Medic.class,URL,USER,PASSWORD);
//        MedicServiceServer medicServiceServer = new MedicServiceServer(executorService,medicRepository);
//
//        Repository<Integer, Patient> patientRepository = new DataBaseRepository(Patient.class,URL,USER,PASSWORD);
//        PatientServiceServer patientServiceServer = new PatientServiceServer(executorService,patientRepository);
//
//        Repository<Integer, Location> locationRepository = new DataBaseRepository(Location.class,URL,USER,PASSWORD);
//        LocationServiceServer locationServiceServer = new LocationServiceServer(executorService,locationRepository);
//
//        Repository<Integer, Procedure> procedureRepository = new DataBaseRepository(Procedure.class,URL,USER,PASSWORD);
//        ProcedureServiceServer procedureServiceServer = new ProcedureServiceServer(executorService,procedureRepository);
//
//        TcpServer tcpServer = new TcpServer(executorService);
//
//        tcpServer.startServer();

        System.out.println();
    }
}
