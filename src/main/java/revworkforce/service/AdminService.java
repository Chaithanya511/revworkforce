package revworkforce.service;

import revworkforce.Dao.AdminDao;
import revworkforce.Dao.EmployeeDao;
import revworkforce.Model.Employee;

public class AdminService {

    AdminDao adminDao = new AdminDao();
    EmployeeDao empDao = new EmployeeDao();

    public void addEmployee(Employee emp) {
        empDao.addEmployee(emp);
        System.out.println("Employee Added Successfully");
    }

    public void deactivateEmployee(String empId) {
        boolean result = adminDao.deactivateEmployee(empId);

        if (!result) {
            System.out.println("Employee not found OR already inactive.");
        } else {
            System.out.println("Employee deactivated successfully.");
        }
    }

    public void reactivateEmployee(String empId) {
        boolean result = adminDao.reactivateEmployee(empId);

        if (!result) {
            System.out.println("Employee not found OR already active.");
        } else {
            System.out.println("Employee reactivated successfully.");
        }
    }

    public void assignManager(String empId, String managerId) {
        adminDao.assignManager(empId, managerId);
        System.out.println("Manager Assigned");
    }
}


