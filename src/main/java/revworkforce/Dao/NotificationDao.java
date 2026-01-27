package revworkforce.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import revworkforce.Util.DBUtil;

public class NotificationDao {

    public void addNotification(String empId, String message) {

        String sql = "INSERT INTO notification (emp_id, message) VALUES (?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ps.setString(2, message);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewNotifications(String empId) {

        String sql = "SELECT notification_id, message,is_read,created_at FROM notification " +
                "WHERE emp_id = ? ";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Notifications ---");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("- " + rs.getString("message"));
            }

            if (!found) {
                System.out.println("No new notifications");
            }

            markAsRead(empId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void markAsRead(String empId) {

        String sql = "UPDATE notification SET is_read = True WHERE emp_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
