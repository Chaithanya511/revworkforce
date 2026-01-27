package revworkforce.service;

import revworkforce.Dao.EmployeeDao;
import revworkforce.Model.Employee;

public class AuthService {
    EmployeeDao dao = new EmployeeDao();

    public Employee login(String id, String password) {
        Employee emp = dao.getEmployeeById(id);

        if (emp != null && emp.getPassword().equals(password) && emp.getIsActive() == 1) {
            return emp;
        }
        return null;
    }
}

