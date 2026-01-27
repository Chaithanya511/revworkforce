package revworkforce.Dao;

import java.sql.*;
import revworkforce.Model.Goal;
import revworkforce.Util.DBUtil;

public class GoalDao {


    public void addGoal(Goal g) {
        String sql = "INSERT INTO goal(emp_id, description, deadline, priority, progress) VALUES (?,?,?,?,0)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, g.getEmpId());
            ps.setString(2, g.getDescription());
            ps.setDate(3, g.getDeadline());
            ps.setString(4, g.getPriority());

            ps.executeUpdate();
            System.out.println("Goal added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateProgress(int goalId, int progress) {
        String sql = "UPDATE goal SET progress=? WHERE goal_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, progress);
            ps.setInt(2, goalId);
            ps.executeUpdate();
            System.out.println("Goal progress updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void viewGoals(String empId) {
        String sql = "SELECT goal_id, description, deadline, priority, progress FROM goal WHERE emp_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Your Goals ---");
            while (rs.next()) {
                System.out.println("Goal ID: " + rs.getInt("goal_id"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Deadline: " + rs.getDate("deadline"));
                System.out.println("Priority: " + rs.getString("priority"));
                System.out.println("Progress: " + rs.getInt("progress") + "%");
                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void viewTeamGoals(String managerId) {
        String sql = "SELECT g.goal_id, e.name, g.description, g.deadline, g.priority, g.progress " +
                "FROM goal g JOIN employee e ON g.emp_id=e.emp_id " +
                "WHERE e.manager_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, managerId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Team Goals ---");
            while (rs.next()) {
                System.out.println("Goal ID: " + rs.getInt("goal_id"));
                System.out.println("Employee: " + rs.getString("name"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Deadline: " + rs.getDate("deadline"));
                System.out.println("Priority: " + rs.getString("priority"));
                System.out.println("Progress: " + rs.getInt("progress") + "%");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


