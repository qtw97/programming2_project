import java.time.*;
import java.io.*;

// Main class to test the system
public class ClinicSystem {
    public static void main(String[] args) {
        // Creating a doctor
        Doctor doctor = new Doctor("Dr. Smith", "1970-01-01", "2020-01-01", "Cardiology");

        // Creating a patient
        Patient patient = new Patient("John Smith", LocalDate.of(1990, 5, 15), "ABC Insurance", "XYZ Company");

        // Displaying patient info
        System.out.println(patient.getInfo());
    }
}

abstract class Person {
    private String firstName; // to store the first name
    private String lastName; // to store the last name
    private String address; // to store the address
    private LocalDate birthdate; // to store the date of birth
    private String phone; // to store the phone number
    private int age; // to store age

    // create a constructor with parameters
    public Person(String firstName, String lastName, String address, LocalDate birthdate, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthdate = birthdate;
        this.phone = phone;
        this.calculateAge();
    }

    // method to calculate age
    private void calculateAge() {
        LocalDate today = LocalDate.now();
        this.age = Period.between(this.birthdate, today).getYears();
    }

    // method to set name
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // method to return name
    public String getName() {
        return firstName + " " + lastName;
    }

    // method to set address
    public void setAddress(String address) {
        this.address = address;
    }

    // method to return address
    public String getAddress() {
        return address;
    }

    // method to set date of birth
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        this.calculateAge();
    }

    // method to return date of birth
    public LocalDate getBirthdate() {
        return birthdate;
    }

    // method to set phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // method to return phone number
    public String getPhone() {
        return phone;
    }

    // method to return age
    public int getAge() {
        return age;
    }

    // method return informations as String
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

    public Doctor(String name, String dob, String dateOfEmployment, String specialty) {
        super(name.split(" ")[0], name.split(" ")[1], "", LocalDate.parse(dob), "");
        this.dateOfEmployment = dateOfEmployment;
        this.specialty = specialty;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public String getSpecialty() {
        return specialty;
    }

    // Store the name, date of birth, date of employment, and specialty of a doctor to the database as Doctor.txt
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

    public Patient(String name, LocalDate dateOfBirth, String insuranceCompany, String employer) {
        super(name.split(" ")[0], name.split(" ")[1], "", dateOfBirth, "");
        this.insuranceCompany = insuranceCompany;
        this.employer = employer;
    }

    // Getters and setters for insuranceCompany and employer
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
}
