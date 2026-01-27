package revworkforce.service;

import revworkforce.Dao.PerformanceDao;
import revworkforce.Model.PerformanceReview;
import revworkforce.service.NotificationService;
public class PerformanceService {
        PerformanceDao dao = new PerformanceDao();
        NotificationService ns = new NotificationService();

        public boolean submitReview(PerformanceReview pr) {
            return dao.submitReview(pr);
        }

//        public void provideFeedback(int reviewId, String feedback, int rating) {
//            dao.giveManagerFeedback(reviewId, feedback, rating);
//            ns.sendNotification(feedback, "Manager has added feedback to your performance review");
//
//        }
public boolean provideFeedback(int reviewId, String feedback, int rating) {

    boolean updated = dao.updateManagerReview(reviewId, feedback, rating);
    if (!updated) {
        return false;
    }

    String empId = dao.getEmployeeIdByReviewId(reviewId);
    if (empId != null) {
        ns.sendNotification(
                empId,
                "Manager has reviewed your performance. Rating: " + rating
        );
    }
    return true;
}




    public void viewMyFeedback(String empId) {
            dao.viewFeedback(empId);
        }

    public void viewTeamReviews(String managerId) {
        dao.viewTeamReviews(managerId);
    }



//    public boolean managerReview(int reviewId, String feedback, int rating) {
//
//        return dao.updateManagerReview(reviewId, feedback, rating);
//    }
}
