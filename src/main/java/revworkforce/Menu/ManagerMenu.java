package revworkforce.Menu;

import java.util.Scanner;
import revworkforce.service.GoalService;
import revworkforce.Model.Employee;
import revworkforce.Menu.EmployeeMenu;
import revworkforce.service.EmployeeService;
import revworkforce.service.LeaveService;
import revworkforce.service.PerformanceService;
import revworkforce.Menu.EmployeeMenu;

public class ManagerMenu extends EmployeeMenu{

    private Employee manager;
    private Employee emp;
    private Scanner sc = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();
    private GoalService goalService=new GoalService();


    private LeaveService leaveService = new LeaveService();
    private PerformanceService performanceService = new PerformanceService();

//    public ManagerMenu(Employee manager) {
//      super(manager);
//        this.manager = manager;
//    }
    public ManagerMenu(Employee emp) {
        super(emp);
        this.emp = emp;
    }

//    public ManagerMenu(Employee emp) {
//        if(emp == null)
//        {
//            throw new IllegalArgumentException("Employee cannot be null");
//        }
//        this.emp=emp;
//    }


    public void showMenu() {
        int choice;

        do{
            System.out.println("\n====== MANAGER MENU ======");
            System.out.println("1. View Team Members");
            System.out.println("2. View My Profile");
            System.out.println("3. View Team Leave Requests");
            System.out.println("4. Approve Leave");
            System.out.println("5. Reject Leave");
            System.out.println("6. Review Performance");
            System.out.println("7. View Team Goals");

            System.out.println("0. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Displaying team members ");
                    viewTeamMembers();
                    break;

                case 2:
                    System.out.println("Displaying Details ");
                    Employee e = employeeService.getEmployeeById(emp.getEmpId());
                    if(e != null) {
                        System.out.println("Employee ID : " + e.getEmpId());
                        System.out.println("Name        : " + e.getName());
                        System.out.println("Email       : " + e.getEmail());
                        System.out.println("Role        : " + e.getRole());
                    } else {
                        System.out.println("Profile not found");
                    }
                    break;

                case 3:
                    leaveService.viewTeamLeaves(emp.getEmpId());
                    break;

                case 4:
                    approveLeave();
                    break;

                case 5:
                    rejectLeave();
                    break;

                case 6:
                    performanceService.viewTeamReviews(emp.getEmpId()); // SEE FIRST
                    reviewPerformance();
                    break;


                case 7:
                    goalService.viewTeamGoals(emp.getEmpId());
                    break;

                case 0:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }

    private void viewTeamMembers() {
        employeeService.viewTeam(emp.getEmpId());
    }
    private void approveLeave() {
        System.out.print("Enter Leave ID to approve: ");
        int leaveId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Employee ID: ");
        String empId = sc.nextLine();

        leaveService.approveLeave(leaveId, empId);
        System.out.println("Leave approved successfully");
    }

    private void rejectLeave() {
        System.out.print("Enter Leave ID to reject: ");
        int leaveId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Employee ID: ");
        String empId = sc.nextLine();

        leaveService.rejectLeave(leaveId, empId);
        System.out.println("Leave rejected successfully");
    }

    private void reviewPerformance() {
        System.out.print("Enter Review ID: ");
        int reviewId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Manager Feedback: ");
        String feedback = sc.nextLine();

        System.out.print("Enter Rating (1-5): ");
        int rating = sc.nextInt();

        boolean success = performanceService.provideFeedback(reviewId, feedback, rating);

        if (success) {
            System.out.println("Performance feedback submitted");
        } else {
            System.out.println("Review not found or already reviewed.");
        }

    }

    private void viewMyLeaves() {
        leaveService.viewLeavesByEmpId(emp.getEmpId());
    }

}
