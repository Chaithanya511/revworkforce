package revworkforce.service;


import revworkforce.Dao.GoalDao;
import revworkforce.Model.Goal;

public class GoalService {

    private GoalDao dao = new GoalDao();

    public boolean addGoal(Goal g) {
        return dao.addGoal(g);

    }

    public void updateProgress(int goalId, int progress) {
        dao.updateProgress(goalId, progress);
    }

    public void viewGoals(String empId) {
        dao.viewGoals(empId);
    }

    public void viewTeamGoals(String managerId) {
        dao.viewTeamGoals(managerId);
    }
}


