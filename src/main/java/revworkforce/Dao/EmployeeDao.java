package revworkforce.Dao;

import java.sql.*;
import revworkforce.Model.Employee;
import revworkforce.Util.DBUtil;

public class EmployeeDao {

    public Employee getEmployeeById(String empId) {
        Employee emp = null;
        String sql = "SELECT * FROM employee WHERE emp_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp = new Employee();
                emp.setEmpId(rs.getString("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setPassword(rs.getString("password"));
                emp.setRole(rs.getString("role"));
                emp.setIsActive(rs.getInt("is_active"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getEmpId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setString(5, emp.getAddress());
            ps.setDate(6, null);
            ps.setString(7, emp.getDepartment());
            ps.setString(8, emp.getDesignation());
            ps.setDate(9, null);
            ps.setDouble(10, 0);
            ps.setString(11, emp.getPassword());
            ps.setString(12, emp.getRole());
            ps.setString(13, emp.getManagerId());
            ps.setInt(14, 1);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void getTeamMembers(String managerId) {

        String sql = "SELECT emp_id, name, email, role " +
                "FROM employee WHERE manager_id = ? AND is_active = 1";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, managerId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Team Members ---");
            System.out.println("ID\tName\tEmail\tRole");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getString("emp_id") + "\t" +
                                rs.getString("name") + "\t" +
                                rs.getString("email") + "\t" +
                                rs.getString("role")
                );
            }

            if (!found) {
                System.out.println("No team members found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET name=?, email=?, phone=?, address=?,  role=?, manager_id=? " +
                "WHERE emp_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPhone());
            ps.setString(4, emp.getAddress());
            ps.setString(5, emp.getRole());
            ps.setString(6, emp.getManagerId());
            ps.setString(7, emp.getEmpId());

            ps.executeUpdate();
            System.out.println("Employee updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


