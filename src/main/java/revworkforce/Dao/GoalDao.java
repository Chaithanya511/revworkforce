package revworkforce.Dao;

import java.sql.*;
import revworkforce.Model.Goal;
import revworkforce.Util.DBUtil;

public class GoalDao {


    public boolean addGoal(Goal goal) {

        int nextGoalId = getNextGoalId(goal.getEmpId());

        String sql = "INSERT INTO goal " +
                "(emp_id, goal_id, goal_description, deadline, priority) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, goal.getEmpId());
            ps.setInt(2, nextGoalId);
            ps.setString(3, goal.getDescription());
            ps.setDate(4, goal.getDeadline());
            ps.setString(5, goal.getPriority());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
        String sql = "SELECT goal_id, goal_description, deadline, priority, progress FROM goal WHERE emp_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Your Goals ---");
            while (rs.next()) {
                System.out.println("Goal ID: " + rs.getInt("goal_id"));
                System.out.println("Description: " + rs.getString("goal_description"));
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
        String sql = "SELECT g.goal_id, e.name, g.goal_description, g.deadline, g.priority, g.progress " +
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
                System.out.println("Description: " + rs.getString("goal_description"));
                System.out.println("Deadline: " + rs.getDate("deadline"));
                System.out.println("Priority: " + rs.getString("priority"));
                System.out.println("Progress: " + rs.getInt("progress") + "%");
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public int getNextReviewId(String empId) {
            String sql = "SELECT COALESCE(MAX(review_id), 0) + 1 FROM performance_review WHERE emp_id = ?";

            try (Connection con = DBUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, empId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        }
    public int getNextGoalId(String empId) {
        String sql = "SELECT COALESCE(MAX(goal_id), 0) + 1 FROM goal WHERE emp_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }


}


