package revworkforce;
import revworkforce.Menu.AdminMenu;
import revworkforce.Menu.EmployeeMenu;
import revworkforce.Menu.ManagerMenu;
import revworkforce.Model.Employee;
import revworkforce.service.AuthService;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();

        System.out.println("=== Rev Workforce HRM System ===");
        System.out.print("Employee ID: ");
        String id = sc.next();
        System.out.print("Password: ");
        String pwd = sc.next();

        Employee emp = auth.login(id, pwd);
        if (emp != null) {

            if (emp.getRole().equalsIgnoreCase("MANAGER")) {
                ManagerMenu managerMenu = new ManagerMenu(emp);
                managerMenu.showMenu();
            }
        }


        if (emp == null) {
            System.out.println("Invalid Credentials");
            return;
        }

        switch (emp.getRole().toUpperCase()) {
            case "EMPLOYEE":
                new EmployeeMenu(emp).showMenu();
                break;
            case "MANAGER":
                new ManagerMenu(emp).showMenu();
                break;
            case "ADMIN":
                new AdminMenu().showMenu();
                break;
        }
    }
}


