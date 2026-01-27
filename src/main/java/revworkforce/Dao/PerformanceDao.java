package revworkforce.Dao;



import java.sql.*;
import revworkforce.Model.PerformanceReview;
import revworkforce.Util.DBUtil;

public class PerformanceDao {

    public void submitReview(PerformanceReview pr) {
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
    public boolean updateManagerReview(int reviewId, String feedback, int rating) {

        String sql = " UPDATE performance_review SET manager_feedback = ?, manager_rating = ? WHERE review_id = ? ";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, feedback);
            ps.setInt(2, rating);
            ps.setInt(3, reviewId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}


