package revworkforce.Menu;

import java.util.Scanner;
import java.sql.Date;
import revworkforce.Model.Employee;
import revworkforce.service.AdminService;
import revworkforce.Dao.EmployeeDao;
import revworkforce.service.EmployeeService;

public class AdminMenu {

    Scanner sc = new Scanner(System.in);
    AdminService adminService = new AdminService();
    private EmployeeService employeeService = new EmployeeService();


    public void showMenu() {
        int choice;

        do {
            System.out.println("\n====== ADMIN MENU ======");
            System.out.println("1. Add New Employee");
            System.out.println("2. Update Employee Details");
            System.out.println("3. Deactivate Employee");
            System.out.println("4. Assign / Change Manager");
            System.out.println("5. View Employee ");
            System.out.println("6. Leave Policy ");
            System.out.println("0. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // buffer clear

            switch (choice) {

                case 1:
                    addEmployee();
                    break;

                case 2:
                    updateEmployee();
                    break;

                case 3:
                    deactivateEmployee();
                    break;

                case 4:
                    assignManager();
                    break;

                case 5:
                    viewEmployee();
                    break;

                case 6:
                    System.out.println("Leave Policy: CL=12, SL=10, PL=15 per annum");
                    break;

                case 0:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }



    private void addEmployee() {
        Employee emp = new Employee();

        System.out.print("Enter Employee ID: ");
        emp.setEmpId(sc.nextLine());

        System.out.print("Enter Name: ");
        emp.setName(sc.nextLine());

        System.out.print("Enter Email: ");
        emp.setEmail(sc.nextLine());

        System.out.print("Enter Phone: ");
        emp.setPhone(sc.nextLine());

        System.out.print("Enter Department: ");
        emp.setDepartment(sc.nextLine());

        System.out.print("Enter Designation: ");
        emp.setDesignation(sc.nextLine());

        System.out.print("Enter Role (EMPLOYEE / MANAGER / ADMIN): ");
        emp.setRole(sc.nextLine());

        System.out.print("Set Password: ");
        emp.setPassword(sc.nextLine());

        adminService.addEmployee(emp);
    }

    private void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        String empId = sc.nextLine();
        Employee emp = new Employee();
        emp.setEmpId(empId);
        System.out.print("Name: "); emp.setName(sc.nextLine());
        System.out.print("Email: "); emp.setEmail(sc.nextLine());
        System.out.print("Phone: "); emp.setPhone(sc.nextLine());
        System.out.print("Address: "); emp.setAddress(sc.nextLine());
        System.out.print("Role: "); emp.setRole(sc.nextLine());
        System.out.print("Manager ID: "); emp.setManagerId(sc.nextLine());

        System.out.println("Employee " + empId + " updated successfully");
    }

    private void deactivateEmployee() {
        System.out.print("Enter Employee ID to deactivate: ");
        String empId = sc.nextLine();

        adminService.deactivateEmployee(empId);
    }

    private void assignManager() {
        System.out.print("Enter Employee ID: ");
        String empId = sc.nextLine();

        System.out.print("Enter Manager ID: ");
        String managerId = sc.nextLine();

        adminService.assignManager(empId, managerId);
    }

    private void viewEmployee() {
        System.out.print("Enter Employee ID: ");
        String empId = sc.nextLine();


        Employee emp = employeeService.getEmployeeById(empId);

        System.out.println("Displaying employee details ");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name        : " + emp.getName());
        System.out.println("Mail        : " + emp.getEmail());
        System.out.println("Role        : " + emp.getRole());
        System.out.println("Status: Active");
    }
}
