package ro.ubb.ui;

import ro.ubb.additional.DateTimeParser;
import ro.ubb.domain.*;
import ro.ubb.service.LocationService;
import ro.ubb.service.MedicService;
import ro.ubb.service.PatientService;
import ro.ubb.service.ProcedureService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ClientConsole {
    private MedicService medicService;
    private PatientService patientService;
    private LocationService locationService;
    private ProcedureService procedureService;

    Scanner scan = new Scanner(System.in);

    public ClientConsole(MedicService medicService, PatientService patientService, LocationService locationService, ProcedureService procedureService) {
        this.medicService = medicService;
        this.patientService = patientService;
        this.locationService = locationService;
        this.procedureService = procedureService;
    }

    public ClientConsole(MedicService medicService) {
        this.medicService = medicService;
    }

    public ClientConsole(MedicService medicService, LocationService locationService) {
        this.medicService = medicService;
        this.locationService = locationService;
    }

    public ClientConsole(MedicService medicService, PatientService patientService) {
        this.medicService = medicService;
        this.patientService = patientService;
    }

    public ClientConsole() {
    }

    public void printMenu() {
        System.out.println(
                "a. Medics menu    || b. Patients menu   || c. Locations menu  || d. Procedures menu\n" +
                        "X. Exit");
    }

    public void medicMenu() {
        System.out.println(
                "1. List all medics     \n" +
                        "2. Add new Medic       \n" +
                        "3. Delete Medic        \n" +
                        "4. Find Medic by id    \n" +
                        "5. Update Medic of id  \n" +
                        "6. Filter User         \n" +
                        "<. Go back             \n" +
                        "X. Exit");
    }

    public void patientMenu() {
        System.out.println(
                "1. List all Patients     \n" +
                        "2. Add Patient           \n" +
                        "3. Delete Patient        \n" +
                        "4. Find Patient by id    \n" +
                        "5. Update Patient of id  \n" +
                        "6. Filter Patient        \n" +
                        "<. Go back               \n" +
                        "X. Exit");
    }

    public void locationMenu() {
        System.out.println(
                "1. List all Locations     \n" +
                        "2. Add Location           \n" +
                        "3. Delete Location        \n" +
                        "4. Find Location by id    \n" +
                        "5. Update Location of id  \n" +
                        "6. Filter Location        \n" +
                        "<. Go back               \n" +
                        "X. Exit");
    }

    public void procedureMenu() {
        System.out.println(
                "1. List all Procedures     \n" +
                        "2. Add Procedure           \n" +
                        "3. Delete Procedure        \n" +
                        "4. Find Procedure by id    \n" +
                        "5. Update Procedure of id  \n" +
                        "6. Filter Procedure        \n" +
                        "<. Go back               \n" +
                        "X. Exit");
    }

    public void runConsole() {
        Boolean exit = false;
        while (!exit) {
            printMenu();
            String option = scan.nextLine();

            switch (option) {
                case "a":
                    exit = medicOptions();
                    break;
                case "b":
                    exit = patientOptions();
                    break;
                case "c":
                    exit = locationOptions();
                    break;
                case "d":
                    exit = procedureOptions();
                    break;
                case "X", "x":
                    exit = true;
                    break;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }

    private Boolean medicOptions() {
        while (true) {
            medicMenu();

            String option = scan.nextLine();
            switch (option) {
                case "1":
                    handleFindAllMedics();
                    break;
                case "2":
                    handleAddMedic();
                    break;
                case "3":
                    handleDeleteMedic();
                    break;
                case "4":
                    handleFindMedic();
                    break;
                case "5":
                    handleUpdateMedic();
                    break;
                case "<":
                    return false;
                case "X", "x":
                    return true;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }

    private Boolean patientOptions() {
        while (true) {
            patientMenu();

            String option = scan.nextLine();
            switch (option) {
                case "1":
                    handleFindAllPatients();
                    break;
                case "2":
                    handleAddPatient();
                    break;
                case "3":
                    handleDeletePatient();
                    break;
                case "4":
                    handleFindPatient();
                    break;
                case "5":
                    handleUpdatePatient();
                    break;
                case "<":
                    return false;
                case "X", "x":
                    return true;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }

    private Boolean locationOptions() {
        while (true) {
            locationMenu();

            String option = scan.nextLine();
            switch (option) {
                case "1":
                    handleFindAllLocations();
                    break;
                case "2":
                    handleAddLocation();
                    break;
                case "3":
                    handleDeleteLocation();
                    break;
                case "4":
                    handleFindLocation();
                    break;
                case "5":
                    handleUpdateLocation();
                    break;
                case "<":
                    return false;
                case "X", "x":
                    return true;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }

    private Boolean procedureOptions() {
        while (true) {
            procedureMenu();

            String option = scan.nextLine();
            switch (option) {
                case "1":
                    handleFindAllProcedures();
                    break;
                case "2":
                    handleAddProcedure();
                    break;
                case "3":
                    handleDeleteProcedure();
                    break;
                case "4":
                    handleFindProcedure();
                    break;
                case "5":
                    handleUpdateProcedure();
                    break;
                case "<":
                    return false;
                case "X", "x":
                    return true;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }


    private boolean updateChoice(String subjectOfChange) {
        System.out.println("Change " + subjectOfChange + " ?  1.Yes  0.No");
        boolean choice;
        String option = scan.nextLine();
        switch (option) {
            case "1", "Yes":
                choice = true;
                break;
            case "0", "No":
                choice = false;
                break;
            default:
                choice = false;
                System.out.println("Not a valid choice, by default the choice is set to false");
                break;
        }
        return choice;
    }

    private void printSpecialtyChoices(){
        System.out.println("Choose Specialty by entering the corresponding number:\n" +
                "Dermatology(1)\n" +
                "Cardiology(2)\n" +
                "Family(3)\n" +
                "Internal(4)\n" +
                "Neurology(5)\n" +
                "Gynecology(6)\n" +
                "Ophthalmology(7)\n" +
                "Pathology(8)\n" +
                "Pediatrics(9)\n" +
                "Recuperation(10)\n" +
                "Psychiatry(11)\n" +
                "Radiology(12)\n" +
                "Surgery(13)\n" +
                "Urology(14)\n");
    }


    private void handleUpdateMedic() {
        System.out.println("Enter Medic ID:");
        Integer id = Integer.parseInt(scan.nextLine());

        Medic currentMedic = medicService.findOne(id);
        System.out.println(currentMedic);

        String name; String userName; String password; String email;
        Specialty specialty; LocalDate beginningDate; LocalDate dateOfBirth;
        String active;

        if (updateChoice("name")) {
            System.out.println("Enter new name:");
            name = scan.nextLine();
        } else name = currentMedic.getName();
        if (updateChoice("user name")) {
            System.out.println("Enter new username:");
            userName = scan.nextLine();
        } else userName = currentMedic.getUserName();
        if (updateChoice("password")) {
            System.out.println("Enter new password:");
            password =scan.nextLine();
        } else password = currentMedic.getPassword();
        if (updateChoice("email")) {
            System.out.println("Enter new email:");
            email = scan.nextLine();
        } else email = currentMedic.getEmail();
        if (updateChoice("specialty")) {
            printSpecialtyChoices();
            Integer valueOfLabel = Integer.parseInt(scan.nextLine());
            specialty = Specialty.valueOfLabel(valueOfLabel);
        }
        else specialty = currentMedic.getSpecialty();
        if (updateChoice("beginning date")) {
            System.out.println("Enter Updated Beginning Date");
            beginningDate = DateTimeParser.parseDate(scan.nextLine());
        } else beginningDate = currentMedic.getBeginningDate();
        if (updateChoice("birth date")) {
            System.out.println("Enter Updated Birth Date");
            dateOfBirth = DateTimeParser.parseDate(scan.nextLine());
        } else dateOfBirth = currentMedic.getDateOfBirth();
        if (updateChoice("is active")) {
            System.out.println("Update is active:\n" +
                    "0.NO     1.YES\n");
            active = scan.nextLine();
        } else active = String.valueOf(currentMedic.getIsActive());
        boolean isActive = true;
        if (active.equals("1") || active.matches("[yesYES]{1,}")) isActive = true;
        if (active.equals("0") || active.matches("[noNO]{1,2}")) isActive = false;

        medicService.update(id, name, userName, password, email, specialty, beginningDate, dateOfBirth, isActive);
    }

    private void handleDeleteMedic() {
        System.out.println("Enter Id to delete:");
        Integer id = Integer.parseInt(scan.nextLine());
        medicService.delete(id);
    }

    private void handleFindAllMedics() {
        medicService.findAll().forEach(System.out::println);
    }

    private void handleFindMedic() {
        System.out.println("Enter Id to find:");
        Integer id = Integer.parseInt(scan.nextLine());
        Medic medic = medicService.findOne(id);
        System.out.println(medic);
    }

    private void handleAddMedic() {
        System.out.println("Enter Name:");
        String name = scan.nextLine();
        System.out.println("Enter User Name:");
        String userName = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();
        System.out.println("Enter email:");
        String email = scan.nextLine();
        printSpecialtyChoices();
        Integer valueOfLabel = Integer.parseInt(scan.nextLine());
        Specialty specialty;
        if (valueOfLabel <= 0 || valueOfLabel > 14) {
            System.out.println("Incorrect value entered, Speciality chosen by default is Dermatology");
            specialty = Specialty.Dermatology;
        } else specialty = Specialty.valueOfLabel(valueOfLabel);
        System.out.println("Enter Beginning Date");
        LocalDate beginningDate = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Enter BirthDate");
        LocalDate dateOfBirth = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Check if active:\n" +
                "0.NO     1.YES\n");
        String active = scan.nextLine();
        boolean isActive = true;
        if (active.equals("1") || active.matches("[yesYES]{1,3}")) isActive = true;
        if (active.equals("0") || active.matches("[noNO]{1,2}")) isActive = false;

        medicService.addMedic(name, userName, password, email,
                specialty, beginningDate, dateOfBirth, isActive);

//        medicService.addMedic("Aaaa","aaa","Aaaa1!","aaaa@aaaa.aa",Specialty.Dermatology
//                ,DateTimeParser.parseDate("1.1.2020"),DateTimeParser.parseDate("1.1.1980"),true);

    }

    private void handleUpdatePatient() {
        System.out.println("Enter Patient ID:");
        Integer id = Integer.parseInt(scan.nextLine());

        Patient currentPatient = patientService.findOne(id);
        System.out.println(currentPatient);

        String name;
        String userName;
        String password;
        String email;
        LocalDate dateOfBirth;
        String hiredChoice;
        Integer cardNumber;

        if (updateChoice("name")) {
            System.out.println("Enter new name:");
            name = scan.nextLine();
        } else name = currentPatient.getName();
        if (updateChoice("user name")) {
            System.out.println("Enter new username:");
            userName = scan.nextLine();
        } else userName = currentPatient.getUserName();
        if (updateChoice("password")) {
            System.out.println("Enter new password:");
            password =scan.nextLine();
        } else password = currentPatient.getPassword();
        if (updateChoice("email")) {
            System.out.println("Enter new email:");
            email = scan.nextLine();
        } else email = currentPatient.getEmail();
        if (updateChoice("birth date")) {
            System.out.println("Enter new birth date");
            dateOfBirth = DateTimeParser.parseDate(scan.nextLine());
        } else dateOfBirth = currentPatient.getDateOfBirth();
        if (updateChoice("hired")) {
            System.out.println("Choose new hired value:\n" +
                    "True(1)\n" +
                    "False(0)\n" );
            hiredChoice = scan.nextLine();
        } else hiredChoice = String.valueOf(currentPatient.getHired());
        if (updateChoice("card number")) {
            System.out.println("Enter new card number:");
            cardNumber = Integer.parseInt(scan.nextLine());
        } else cardNumber = currentPatient.getCardNumber();
        //Deocamdata facem update cu lista goala pana facem CRUD
//        System.out.println("Update afflictions:");
        // aici cum putem face? ne trebuie toate afflictions din DB, nu?
//        List<Diagnosis> afflictions = new ArrayList<>();
        boolean hired = true;
        if(hiredChoice.equals("1") || hiredChoice.matches("[trueTYys]{1,3}")) hired = true;
        if(hiredChoice.equals("0") || hiredChoice.matches("[falseFNno]{1,2}")) hired = false;

        patientService.update(id, name,userName,password,email,dateOfBirth,hired,cardNumber);
    }

    private void handleDeletePatient() {
        System.out.println("Enter Id to delete:");
        Integer id = Integer.parseInt(scan.nextLine());
        patientService.delete(id);
    }

    private void handleFindAllPatients() {
        patientService.findAll().forEach(System.out::println);
    }

    private void handleFindPatient() {
        System.out.println("Enter Id to find:");
        Integer id = Integer.parseInt(scan.nextLine());
        Patient patient = patientService.findOne(id);
        System.out.println(patient);
    }

    private void handleAddPatient() {
        System.out.println("Enter new name:");
        String name = scan.nextLine();
        System.out.println("Enter new username:");
        String userName = scan.nextLine();
        System.out.println("Enter new password:");
        String password = scan.nextLine();
        System.out.println("Enter new email:");
        String email = scan.nextLine();
        System.out.println("Enter new birth date");
        LocalDate dateOfBirth = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Choose new hired value:\n" +
                "True(1)\n" +
                "False(0)\n" );
        String hiredNumber = scan.nextLine();
        System.out.println("Enter new card number:");
        int cardNumber = Integer.parseInt(scan.nextLine());
        boolean hired = true;
        if(hiredNumber.equals("1") || hiredNumber.matches("[trueTrue]{1,3}")) hired = true;
        if(hiredNumber.equals("0") || hiredNumber.matches("[falseFalse]{1,2}")) hired = false;

        patientService.addPatient(name,userName,password,email,
                dateOfBirth,hired,cardNumber);
    }

    private void handleUpdateLocation() {
        System.out.println("Enter Location ID:");
        Integer id = Integer.parseInt(scan.nextLine());
        String name;String address;String city;String phoneNumber;
        Location currentLocation = locationService.findOne(id);
        System.out.println(currentLocation);
        if(updateChoice("name")) {
            System.out.println("Enter new name:");
            name = scan.nextLine();
        }
        else name = currentLocation.getName();
        if(updateChoice("address")) {
            System.out.println("Enter new address:");
            address = scan.nextLine();
        }
        else address = currentLocation.getAddress();
        if(updateChoice("city")) {
            System.out.println("Enter new city:");
            city = scan.nextLine();
        }
        else city = currentLocation.getCity();
        if(updateChoice("phone number")) {
            System.out.println("Enter new phone number:");
            phoneNumber = scan.nextLine();
        }
        else phoneNumber = currentLocation.getPhoneNumber();
        locationService.update(id,name,address,city,phoneNumber);
    }

    private void handleDeleteLocation() {
        System.out.println("Enter Id to delete:");
        Integer id = Integer.parseInt(scan.nextLine());
        locationService.delete(id);
    }

    private void handleFindAllLocations() {
        locationService.findAll().forEach(System.out::println);
    }

    private void handleFindLocation() {
        System.out.println("Enter Id to find:");
        Integer id = Integer.parseInt(scan.nextLine());
        Location location = locationService.findOne(id);
        System.out.println(location);
    }

    private void handleAddLocation() {
        System.out.println("Enter name:");
        String name = scan.nextLine();
        System.out.println("Enter address:");
        String address = scan.nextLine();
        System.out.println("Enter city:");
        String city = scan.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber = scan.nextLine();
        locationService.addLocation(name,address,city,phoneNumber);
    }

    private void handleUpdateProcedure() {
        System.out.println("Enter Procedure ID:");
        Integer id = Integer.parseInt(scan.nextLine());

        Procedure currentProcedure = procedureService.findOne(id);
        System.out.println(currentProcedure);

        String name;
        Float price;
        Specialty specialty;
        Integer duration = 0;

        if (updateChoice("name")) {
            System.out.println("Enter new name:");
            name= scan.nextLine();
        }
        else name = currentProcedure.getName();

        if (updateChoice("price")) {
            System.out.println("Enter new price:");
            price = Float.parseFloat(scan.nextLine());
        }
        else price = currentProcedure.getPrice();

        if (updateChoice("duration")) {
            System.out.println("Enter new duration in minutes:");
            duration = Integer.parseInt(scan.nextLine());
        }
        else duration = currentProcedure.getDuration();

        if (updateChoice("specialty")) {
            printSpecialtyChoices();
            Integer valueOfLabel = Integer.parseInt(scan.nextLine());
            specialty = Specialty.valueOfLabel(valueOfLabel);
        }
        else specialty = currentProcedure.getSpecialty();

        procedureService.update(id,name,price,specialty,duration);
    }

    private void handleDeleteProcedure() {
        System.out.println("Enter Id to delete:");
        Integer id = Integer.parseInt(scan.nextLine());
        procedureService.delete(id);
    }

    private void handleFindAllProcedures() {
        procedureService.findAll().forEach(System.out::println);
    }

    private void handleFindProcedure() {
        System.out.println("Enter Id to find:");
        Integer id = Integer.parseInt(scan.nextLine());
        Procedure procedure = procedureService.findOne(id);
        System.out.println(procedure);
    }

    private void handleAddProcedure() {
        System.out.println("Enter name:");
        String name = scan.nextLine();
        System.out.println("Enter price:");
        Float price = Float.parseFloat(scan.nextLine());
        System.out.println("Enter duration in minutes:");
        Integer duration = Integer.parseInt(scan.nextLine());
        printSpecialtyChoices();
        Integer valueOfLabel = Integer.parseInt(scan.nextLine());
        Specialty specialty;
        if (valueOfLabel <= 0 || valueOfLabel > 14) {
            System.out.println("Incorrect value entered, Speciality chosen by default is Dermatology");
            specialty = Specialty.Dermatology;
        }
        else specialty = Specialty.valueOfLabel(valueOfLabel);
        procedureService.addProcedure(name,price,specialty,duration);
    }
}
