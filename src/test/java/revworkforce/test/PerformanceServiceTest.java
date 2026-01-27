package revworkforce.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import revworkforce.Model.PerformanceReview;
import revworkforce.service.PerformanceService;

public class PerformanceServiceTest {

    PerformanceService service = new PerformanceService();

    @Test
    void testSubmitPerformanceReview() {
        PerformanceReview pr = new PerformanceReview();
        pr.setEmpId("chaitu");
        pr.setAchievements("Completed HRM project");
        pr.setImprovements("Time management");
        pr.setSelfRating(4);

        assertDoesNotThrow(() -> service.submitReview(pr));
    }

    @Test
    void testManagerReview() {
        assertDoesNotThrow(() ->
                service.managerReview(1, "Good work", 4)
        );
    }
}


