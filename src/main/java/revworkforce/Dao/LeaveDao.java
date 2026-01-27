package revworkforce.Dao;


import java.sql.*;

import revworkforce.Model.LeaveRequest;
import revworkforce.Util.DBUtil;

public class LeaveDao {

    public boolean applyLeave(LeaveRequest leave) {
        String sql = "INSERT INTO leave_request(emp_id,leave_type,from_date,to_date,reason,status) VALUES(?,?,?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, leave.getEmpId());
            ps.setString(2, leave.getLeaveType());
            ps.setDate(3, leave.getStartDate());
            ps.setDate(4, leave.getEndDate());
            ps.setString(5, leave.getReason());
            ps.setString(6, "PENDING");

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateLeaveStatus(int leaveId, String status) {

        String sql = "UPDATE leave_request SET status = ? WHERE leave_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, leaveId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void viewLeavesByEmployee(String empId) {

        String sql = "SELECT leave_id, from_date, to_date, leave_type, status " +
                "FROM leave_request WHERE emp_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("leave_id") + "\t" +
                                rs.getDate("from_date") + "\t" +
                                rs.getDate("to_date") + "\t" +
                                rs.getString("leave_type") + "\t" +
                                rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getLeavesByEmpId(String empId) {

        String sql = "SELECT * FROM leave_requests WHERE emp_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Leave ID: " + rs.getInt("leave_id") +
                                " | Type: " + rs.getString("leave_type") +
                                " | Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getTeamLeaves(String managerId) {

        String sql = " SELECT l.leave_id, l.emp_id, l.leave_type, l.status FROM leave_request l JOIN employee e ON l.emp_id = e.emp_id WHERE e.manager_id = ? ";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, managerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Leave ID: " + rs.getInt("leave_id") +
                                " | Emp: " + rs.getString("emp_id") +
                                " | Type: " + rs.getString("leave_type") +
                                " | Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


