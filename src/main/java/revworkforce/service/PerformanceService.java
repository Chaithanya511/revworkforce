package revworkforce.service;

import revworkforce.Dao.PerformanceDao;
import revworkforce.Model.PerformanceReview;
import revworkforce.service.NotificationService;
public class PerformanceService {
        PerformanceDao dao = new PerformanceDao();
        NotificationService ns = new NotificationService();

        public void submitReview(PerformanceReview pr) {
            dao.submitReview(pr);
            System.out.println("Performance Review Submitted");
        }

        public void provideFeedback(int reviewId, String feedback, int rating) {
            dao.giveManagerFeedback(reviewId, feedback, rating);
            ns.sendNotification(feedback, "Manager has added feedback to your performance review");

        }

        public void viewMyFeedback(String empId) {
            dao.viewFeedback(empId);
        }


    public boolean managerReview(int reviewId, String feedback, int rating) {

        return dao.updateManagerReview(reviewId, feedback, rating);
    }
}
