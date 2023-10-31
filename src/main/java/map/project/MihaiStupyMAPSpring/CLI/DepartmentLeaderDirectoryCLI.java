package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.DepartmentLeader;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DepartmentLeaderDirectoryCLI implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DepartmentLeaderDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Department Leader Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all department leaders");
            System.out.println("2. Add a new department leader");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllDepartmentLeaders();
                    break;
                case 2:
                    addDepartmentLeader(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllDepartmentLeaders() {
        Iterable<DepartmentLeader> departmentLeaders = employeeRepository.findAllByIsLeader(true);
        System.out.println("List of Department Leaders:");
        departmentLeaders.forEach(leader -> {
            System.out.println("Employee ID: " + leader.getEmployeeID() + ", Name: " + leader.getFirstName() + " " + leader.getLastName());
        });
    }

    private void addDepartmentLeader(Scanner scanner) {
        System.out.println("Enter department leader details:");

        // Get department leader details from the user
        System.out.print("Employee ID: ");
        int employeeID = scanner.nextInt();

        System.out.print("First Name: ");
        String firstName = scanner.next();

        System.out.print("Last Name: ");
        String lastName = scanner.next();

        System.out.print("Phone Number: ");
        int phoneNumber = scanner.nextInt();

        System.out.print("Email Address: ");
        String emailAddress = scanner.next();

        // Create a new department leader
        DepartmentLeader departmentLeader = new DepartmentLeader(employeeID, firstName, lastName, phoneNumber, emailAddress, null);

        // Set the isDepartmentLeader property to true for department leaders
        departmentLeader.setIsLeader(true);

        // Save the department leader to the database
        employeeRepository.save(departmentLeader);

        System.out.println("Department Leader added successfully.");
    }
}
