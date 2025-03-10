package FetchApi.Service;

import FetchApi.Repository.AttendanceRepository;
import FetchApi.Repository.UserRepository;
import FetchApi.model.Attendance;
import FetchApi.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository usersRepository;

    public BigDecimal calculateSalary(int userId, LocalDate requestDate) {
        try {
            // Ensure salary is generated only between the 1st and 5th of the month
            int dayOfMonth = requestDate.getDayOfMonth();
            if (dayOfMonth < 1 || dayOfMonth > 5) {
                throw new IllegalArgumentException("Salary can only be generated between the 1st and 5th of the month.");
            }

            // Get the previous month
            YearMonth previousMonth = YearMonth.from(requestDate.minusMonths(1));

            // Fetch attendance records for the previous month
            List<Attendance> attendanceRecords = attendanceRepository.findByUserId(userId);
            if (attendanceRecords == null || attendanceRecords.isEmpty()) {
                return BigDecimal.ZERO; // No attendance = no salary
            }

            // Fetch user details safely
            BigDecimal presentWage = BigDecimal.valueOf(1000);
            BigDecimal halfDayWage = BigDecimal.valueOf(500);

            BigDecimal totalSalary = BigDecimal.ZERO;

            for (Attendance record : attendanceRecords) {
                LocalDate attendanceDate = record.getDate().toLocalDate();
                if (attendanceDate.getMonthValue() == previousMonth.getMonthValue()) {
                    switch (record.getStatus()) {
                        case PRESENT:
                            totalSalary = totalSalary.add(presentWage);
                            break;
                        case HALF_DAY:
                            totalSalary = totalSalary.add(halfDayWage);
                            break;
                        case ABSENT:
                            break;
                    }
                }
            }

            return totalSalary;
        } catch (Exception e) {
            // Log the error and rethrow a custom exception
            throw new RuntimeException("Error calculating salary: " + e.getMessage(), e);
        }
    }

}
