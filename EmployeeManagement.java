import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple console application for managing employee records.
 * Records are kept in memory and are cleared when the program exits.
 */
public class EmployeeManagement {
    private static final Map<Integer, Employee> employees = new LinkedHashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Add employee");
            System.out.println("2. Display all employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Update employee details");
            System.out.println("5. Delete employee");
            System.out.println("6. Exit");

            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-6.");
            }
        }
        scanner.close();
    }

    private static void addEmployee() {
        int id = readInt("Employee ID: ");
        if (employees.containsKey(id)) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        String name = readNonEmpty("Name: ");
        String department = readNonEmpty("Department: ");
        double salary = readSalary("Salary: ");

        employees.put(id, new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet.");
            return;
        }

        System.out.println("\nEmployee records:");
        for (Employee employee : employees.values()) {
            System.out.println(employee);
        }
    }

    private static void searchEmployee() {
        int id = readInt("Enter employee ID to search: ");
        Employee employee = employees.get(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            System.out.println("Employee found:");
            System.out.println(employee);
        }
    }

    private static void updateEmployee() {
        int id = readInt("Enter employee ID to update: ");
        Employee employee = employees.get(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
            return;
        }

        System.out.println("Enter the updated employee details:");
        String name = readNonEmpty("Name: ");
        String department = readNonEmpty("Department: ");
        double salary = readSalary("Salary: ");

        employees.put(id, new Employee(id, name, department, salary));
        System.out.println("Employee details updated successfully.");
    }

    private static void deleteEmployee() {
        int id = readInt("Enter employee ID to delete: ");
        Employee removed = employees.remove(id);
        if (removed == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            System.out.println("Employee deleted successfully.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static double readSalary(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double salary = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isFinite(salary) && salary >= 0) {
                    return salary;
                }
            } catch (NumberFormatException e) {
                // Show the validation message below.
            }
            System.out.println("Please enter a valid non-negative salary.");
        }
    }

    private static class Employee {
        private final int id;
        private final String name;
        private final String department;
        private final double salary;

        private Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("ID: %d | Name: %s | Department: %s | Salary: %.2f",
                    id, name, department, salary);
        }
    }
}
