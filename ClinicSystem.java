import java.time.*;
import java.io.*;

// Main class to test the system
public class ClinicSystem {
    public static void main(String[] args) {
        // Creating a doctor
        Doctor doctor = new Doctor("Dr. Smith", "1970-01-01", "2020-01-01", "Cardiology");

        // Creating a patient
        Patient patient = new Patient("John Doe", "1980-05-15", "ABC Insurance", "XYZ Company");

        // Recording a treatment
        // Treatment treatment = new Treatment("2024-04-09", "10:00", "11:00", doctor, patient);

        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        Person person1 = new Person("John", "Doe", "123 Main St, Anytown, USA", birthdate, "555-123-4567");
        System.out.println(person1.getInfo());
    }
}


abstract class Person {
    private static String firstName; //to store the first name
    private static String lastName; //to store the second name
    private String address; //to store the address
    private LocalDate birthdate; //to store the date of birth
    private String phone; //to store the phone number
    private int age; //to store age
    
    //create a constructor with parameters
    public Person(String firstName,String lastName, String address, LocalDate birthdate, String phone) {
        Person.firstName = firstName;
        Person.lastName = lastName;
        this.address = address;
        this.birthdate = birthdate;
        this.phone = phone;
        this.calculateAge();
    }
    
    //method to calculate age
    private void calculateAge() {
        LocalDate today = LocalDate.now();
        this.age = Period.between(this.birthdate, today).getYears();
    }
    
    //method to set name
    public void setName(String firstName,String secondName) {
    	 Person.firstName = firstName;
         Person.lastName = secondName;
    }

    //method to return name
    public String getName(String firstName,String secondName) {
    	String name = firstName + "\s" +secondName;
        return name;
    }
    
    //method to set address
    public void setAddress(String address) {
        this.address = address;
    }
    
    //method to return address
    public String getAddress() {
        return address;
    }
    
    //method to set date of birth
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        this.calculateAge();
    }
     
    //method to return date of birth
    public LocalDate getBirthdate() {
        return birthdate;
    }
    
    //method to set phone
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    //method to return phone number
    public String getPhone() {
        return phone;
    }
    
    //method to return age
    public int getAge() {
        return age;
    }
    
    //method return informations as String
    public String getInfo() {
		return "Name:" + getName(firstName, lastName)+ "\n" +
				"Address: " + getAddress() + "\n" +
				"Birthdate: " + getBirthdate() + "\n" +
				"Phone: " + getPhone() + "\n" +
				"Age: " + getAge();
    	
    }

    // Doctor class
    public class Doctor extends Person {
        private String dateOfEmployment;
        private String specialty;
    
        public Doctor(String name, String dob, String dateOfEmployment, String specialty) {
            super(firstName, lastName, address, birthdate, phone);
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
                writer.write(getName() + "," + getDob() + "," + dateOfEmployment + "," + specialty + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("Error storing doctor to the database: " + e.getMessage());
            }
        }
    }

    // Patient class
    public class Patient extends Person {
        private String insuranceCompany;
        private String employer;
    
        public Patient(String name, String dateOfBirth, String insuranceCompany, String employer) {
            super(firstName, lastName, address, birthdate, phone);
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


}