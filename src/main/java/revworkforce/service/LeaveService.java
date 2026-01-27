package revworkforce.service;

import revworkforce.Dao.LeaveDao;
import revworkforce.Model.LeaveRequest;

public class LeaveService {

    private LeaveDao dao = new LeaveDao();

    public void applyLeave(LeaveRequest leave) {
        boolean success = dao.applyLeave(leave);

        if (success) {
            System.out.println("Leave applied successfully. Status: PENDING");
        } else {
            System.out.println("Failed to apply leave");
        }
    }

    public void viewEmployeeLeaves(String empId) {
        dao.viewLeavesByEmployee(empId);
    }
    private NotificationService notificationService = new NotificationService();

    public void approveLeave(int leaveId, String empId) {
        dao.updateLeaveStatus(leaveId, "APPROVED");
        notificationService.sendNotification(
                empId,
                "Your leave request (ID: " + leaveId + ") has been APPROVED"
        );
    }

    public void rejectLeave(int leaveId, String empId) {
        dao.updateLeaveStatus(leaveId, "REJECTED");
        notificationService.sendNotification(
                empId,
                "Your leave request (ID: " + leaveId + ") has been REJECTED"
        );
    }
    public void viewLeavesByEmpId(String empId) {
        dao.getLeavesByEmpId(empId);
    }
    public void viewTeamLeaves(String managerId) {
        dao.getTeamLeaves(managerId);
    }

//    public void viewProfile() {
//
//    }
}
