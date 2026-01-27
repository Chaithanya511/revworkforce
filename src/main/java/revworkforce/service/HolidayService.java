package revworkforce.service;

import revworkforce.Dao.HolidayDao;

public class HolidayService {

    private HolidayDao holidayDao = new HolidayDao();

    public void viewHolidayCalendar() {
        holidayDao.viewAllHolidays();
    }
}
