package revworkforce.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import revworkforce.Model.Employee;
import revworkforce.Util.DBUtil;
import revworkforce.service.AdminService;

public class AdminDao {


    public boolean deactivateEmployee(String empId) {
        String sql = "UPDATE employee SET is_active = 0 WHERE emp_id = ? AND is_active = 1";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId.trim());

            int rows = ps.executeUpdate();
            System.out.println("Deactivate rows affected: " + rows);

            return rows == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reactivateEmployee(String empId) {
        String sql = "UPDATE employee SET is_active = 1 WHERE emp_id = ? AND is_active = 0";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId.trim());

            int rows = ps.executeUpdate();
            System.out.println("Reactivate rows affected: " + rows);

            return rows == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public boolean assignManager(String empId, String managerId) {
        String sql = "UPDATE employee SET manager_id=? WHERE emp_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, managerId);
            ps.setString(2, empId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


