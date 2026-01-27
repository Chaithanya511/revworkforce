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
        adminDao.deactivateEmployee(empId);
        System.out.println("Employee Deactivated");
    }

    public void assignManager(String empId, String managerId) {
        adminDao.assignManager(empId, managerId);
        System.out.println("Manager Assigned");
    }
}


