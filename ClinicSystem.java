import java.time.*;
import java.io.*;

// Main class to test the system
public class ClinicSystem {
    public static void main(String[] args) {
        // Creating a doctor
        // Doctor doctor = new Doctor("Emily", "Stone", "123 Main St", LocalDate.of(1975, 5, 5), "555-123-4567", LocalDate.of(2005, 5, 5), "Cardiology");

        // Creating a patient
        Patient patient = new Patient("John", "Doe", LocalDate.of(1980, 1, 1), "Company A", "Insurance A",LocalDate.of(2017, 1, 5));

        // Recording a treatment
        // Treatment treatment = new Treatment("2024-04-09", "10:00", "11:00", doctor,
        // patient);

        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        // Person person1 = new Person("John", "Doe", "123 Main St, Anytown, USA",
        // birthdate, "555-123-4567");
        // System.out.println(person1.getInfo());
    }
}

abstract class Person {
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate birthdate;
    private String phone;
    private int age;

    public Person(String firstName, String lastName, String address, LocalDate birthdate, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthdate = birthdate;
        this.phone = phone;
        this.calculateAge();
    }

    private void calculateAge() {
        LocalDate today = LocalDate.now();
        this.age = Period.between(this.birthdate, today).getYears();
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        this.calculateAge();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public String getInfo() {
        return "Name: " + getName() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Birthdate: " + getBirthdate() + "\n" +
                "Phone: " + getPhone() + "\n" +
                "Age: " + getAge();
    }
}

// Doctor class
class Doctor extends Person {
    private String dateOfEmployment;
    private String specialty;

    public Doctor(String firstName, String lastName, String address, LocalDate birthdate, String phone,
            String dateOfEmployment, String specialty) {
        super(firstName, lastName, address, birthdate, phone);
        this.dateOfEmployment = dateOfEmployment;
        this.specialty = specialty;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void storeDoctor() {
        // Code to store the doctor to the database
        try {
            FileWriter writer = new FileWriter("Doctor.txt", true);
            writer.write(getName() + "," + getBirthdate() + "," + dateOfEmployment + "," + specialty + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error storing doctor to the database: " + e.getMessage());
        }
    }
}

// Patient class
class Patient extends Person {
    private String insuranceCompany;
    private String employer;
    private LocalDate appointment;

    public Patient(String firstName, String lastName, LocalDate birthdate, String insuranceCompany, String employer, LocalDate appointment) {
        super(firstName, lastName, "", birthdate, "");
        this.insuranceCompany = insuranceCompany;
        this.employer = employer;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public LocalDate getAppointment() {
        return appointment;
    }

    public void setAppointment(LocalDate appointment) {
        this.appointment = appointment;
    }

    public static void storePatient(Patient patient) {
        try {
            FileWriter writer = new FileWriter("Patients.txt", true);
            writer.write(patient.getName() + "," + patient.getBirthdate() + "," +
                    patient.getInsuranceCompany() + "," + patient.getEmployer() + "\n");
            writer.close();
            System.out.println("Patient data stored successfully.");
        } catch (IOException e) {
            System.out.println("Error storing patient data: " + e.getMessage());
        }
    }
}
