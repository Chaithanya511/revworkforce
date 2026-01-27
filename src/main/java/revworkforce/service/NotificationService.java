package revworkforce.service;

import revworkforce.Dao.NotificationDao;

public class NotificationService {

    private NotificationDao dao = new NotificationDao();

    public void sendNotification(String empId, String message) {
        dao.addNotification(empId, message);
    }

    public void viewNotifications(String empId) {
        dao.viewNotifications(empId);
    }
    public void markAsRead(String empId) {
        dao.markAsRead(empId);
    }
}


