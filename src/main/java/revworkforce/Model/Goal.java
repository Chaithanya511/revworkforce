package revworkforce.Model;


import java.sql.Date;

public class Goal {
    private int goalId;
    private String empId;
    private String description;
    private Date deadline;
    private String priority;
    private int progress;


    public int getGoalId() { return goalId; }
    public void setGoalId(int goalId) { this.goalId = goalId; }
    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }
}

