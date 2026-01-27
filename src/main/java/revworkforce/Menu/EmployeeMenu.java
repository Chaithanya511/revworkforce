package revworkforce.Menu;

import java.sql.Date;
import java.util.Scanner;

import revworkforce.Dao.HolidayDao;
import revworkforce.Model.Employee;
import revworkforce.Model.Goal;
import revworkforce.Model.LeaveRequest;
import revworkforce.Model.PerformanceReview;
import revworkforce.service.*;

public class EmployeeMenu {

    private Employee emp;
    private Scanner sc = new Scanner(System.in);
    HolidayService holidayService = new HolidayService();
    GoalService goalService = new GoalService();
    private LeaveService leaveService = new LeaveService();
    private PerformanceService performanceService = new PerformanceService();

    public EmployeeMenu(Employee emp) {
        this.emp = emp;
    }

    public void showMenu() {
        int choice;

        do {
            System.out.println("\n====== EMPLOYEE MENU ======");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Apply Leave");
            System.out.println("4. View Leave Status");
            System.out.println("5. Submit Performance Review");
            System.out.println("6. View Performance Feedback");
            System.out.println("7. View Notifications");
            System.out.println("8.  Holiday Calendar");
            System.out.println("9. Add New Goal");
            System.out.println("10. Update Goal Progress");
            System.out.println("11. View My Goals");
            System.out.println("0. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    viewProfile();
                    break;

                case 2:
                    editProfile();
                    break;

                case 3:
                    applyLeave();
                    break;

                case 4:
                    viewMyLeaveStatus();
                    break;

                case 5:
                    submitPerformanceReview();
                    break;

                case 6:
                    performanceService.viewMyFeedback(emp.getEmpId());
                    break;

                case 7:
                    viewNotifications();
                    break;

                case 8:
                    holidayService.viewHolidayCalendar();
                    break;
                case 9:
                    addGoal();
                    break;
                case 10:
                    updateGoalProgress();
                    break;
                case 11:
                    goalService.viewGoals(emp.getEmpId());
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }

    private void viewProfile() {
        System.out.println("\n--- Profile Details ---");
        System.out.println("ID: " + emp.getEmpId());
        System.out.println("Name: " + emp.getName());
        System.out.println("Email: " + emp.getEmail());
        System.out.println("Role: " + emp.getRole());
    }

    private void editProfile() {

        System.out.print("Enter new phone number: ");
        emp.setPhone(sc.nextLine());

        System.out.print("Enter new address: ");
        emp.setAddress(sc.nextLine());

        System.out.println("Profile updated successfully ");
    }

    private void applyLeave() {
        LeaveRequest leave = new LeaveRequest();

        leave.setEmpId(emp.getEmpId());

        System.out.print("Enter Leave Type (CL / SL / PL): ");
        leave.setLeaveType(sc.nextLine());

        System.out.print("Enter Start Date (yyyy-mm-dd): ");
        leave.setStartDate(Date.valueOf(sc.nextLine()));

        System.out.print("Enter End Date (yyyy-mm-dd): ");
        leave.setEndDate(Date.valueOf(sc.nextLine()));

        System.out.print("Enter Reason: ");
        leave.setReason(sc.nextLine());

        leaveService.applyLeave(leave);
    }

    private void viewMyLeaveStatus() {
        leaveService.viewEmployeeLeaves(emp.getEmpId());
    }

    private void viewNotifications() {
        NotificationService ns = new NotificationService();
        ns.viewNotifications(emp.getEmpId());
        ns.markAsRead(emp.getEmpId());
    }

    private void submitPerformanceReview() {
        PerformanceReview pr = new PerformanceReview();

        pr.setEmpId(emp.getEmpId());

        System.out.print("Enter Achievements: ");
        pr.setAchievements(sc.nextLine());

        System.out.print("Enter Areas of Improvement: ");
        pr.setImprovements(sc.nextLine());

        System.out.print("Enter Self Rating (1-5): ");
        pr.setSelfRating(sc.nextInt());
        sc.nextLine();

        performanceService.submitReview(pr);
    }

    private void addGoal() {
        Goal g = new Goal();
        g.setEmpId(emp.getEmpId());

        System.out.print("Enter Goal Description: ");
        g.setDescription(sc.nextLine());

        System.out.print("Enter Deadline (yyyy-mm-dd): ");
        g.setDeadline(java.sql.Date.valueOf(sc.nextLine()));

        System.out.print("Enter Priority (High/Medium/Low): ");
        g.setPriority(sc.nextLine());

        goalService.addGoal(g);
    }

    private void updateGoalProgress() {
        System.out.print("Enter Goal ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Progress % (0-100): ");
        int progress = sc.nextInt();
        sc.nextLine();

        goalService.updateProgress(id, progress);
    }

}
