package revworkforce.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import revworkforce.Util.DBUtil;

public class HolidayDao {

    public void viewAllHolidays() {

        String sql = "SELECT holiday_date, description FROM holiday ORDER BY holiday_date";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- Company Holiday Calendar ---");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(rs.getDate("holiday_date")
                        + "  |  " + rs.getString("description"));
            }

            if (!found) {
                System.out.println("No holidays configured.");
            }

        } catch (Exception e) {
            System.out.println("Unable to fetch holidays.");
            e.printStackTrace();
        }
    }
}
