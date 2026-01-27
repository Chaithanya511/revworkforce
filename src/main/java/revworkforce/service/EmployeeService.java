package revworkforce.service;

import revworkforce.Dao.EmployeeDao;
import revworkforce.Model.Employee;

public class EmployeeService {

    EmployeeDao dao = new EmployeeDao();
    public void updateProfile(Employee emp) {

        dao.addEmployee(emp);
    }
    public void viewTeam(String managerId) {
        dao.getTeamMembers(managerId);
    }
    public Employee getEmployeeById(String empId) {
        return dao.getEmployeeById(empId);
    }

}


