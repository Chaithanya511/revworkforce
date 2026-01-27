package revworkforce.Dao;



import java.sql.*;
import revworkforce.Model.PerformanceReview;
import revworkforce.Util.DBUtil;

public class PerformanceDao {

    public boolean submitReview(PerformanceReview pr) {
        String sql = "INSERT INTO performance_review(emp_id,achievements,improvements,self_rating) VALUES(?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pr.getEmpId());
            ps.setString(2, pr.getAchievements());
            ps.setString(3, pr.getImprovements());
            ps.setInt(4, pr.getSelfRating());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void viewTeamReviews(String managerId) {

        String sql = " SELECT review_id, emp_id, self_rating, manager_rating, manager_feedback, status FROM performance_review WHERE emp_id IN (SELECT emp_id FROM employee WHERE manager_id = ?) ";


        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, managerId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- TEAM PERFORMANCE REVIEWS ---");

            boolean found = false;
            while (rs.next()) {
                found=true;
                System.out.println("Review ID        : " + rs.getInt("review_id"));
                System.out.println("Employee ID      : " + rs.getString("emp_id"));
                System.out.println("Self Rating      : " + rs.getInt("self_rating"));
                System.out.println("Manager Rating   : " + rs.getInt("manager_rating"));
                System.out.println("Feedback         : " + rs.getString("manager_feedback"));
                System.out.println("Status           : " + rs.getString("status"));
                System.out.println("----------------------------------");

            }

            if (!found) {
                System.out.println("No reviews found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void giveManagerFeedback(int reviewId, String feedback, int rating) {
        String sql = "UPDATE performance_review SET manager_feedback=?, manager_rating=? WHERE review_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, feedback);
            ps.setInt(2, rating);
            ps.setInt(3, reviewId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewFeedback(String empId) {

        String sql = "SELECT review_id, manager_feedback, manager_rating, status " +
                "FROM performance_review WHERE emp_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("\nReview ID: " + rs.getInt("review_id"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("Manager Rating: " + rs.getInt("manager_rating"));
                System.out.println("Feedback: " + rs.getString("manager_feedback"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getEmployeeIdByReviewId(int reviewId) {
        String sql = "SELECT emp_id FROM performance_review WHERE review_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reviewId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("emp_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateManagerReview(int reviewId, String feedback, int rating) {

        String sql = " UPDATE performance_review SET manager_feedback = ?, manager_rating = ?, status = 'REVIEWED' WHERE review_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, feedback);
            ps.setInt(2, rating);
            ps.setInt(3, reviewId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}


