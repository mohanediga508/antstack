package FetchApi.Controllor;

import FetchApi.Repository.AttendanceRepository;
import FetchApi.model.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @PostMapping("/attendance")
    public ResponseEntity<Map<String, String>>  addAttendance(@RequestBody Attendance attendance){

        boolean exist=attendanceRepository.existsByUserIdAndDate(attendance.getUserId(),attendance.getDate());
        if (exist){
            return ResponseEntity.ok(Collections.singletonMap("error", "Attendance already marked"));

        }
        LocalDate date = attendance.getDate().toLocalDate();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return ResponseEntity.ok(Collections.singletonMap("error", "Cannot mark attendance on weekend."));
        } else {
            try {
                attendanceRepository.save(attendance);
                return ResponseEntity.ok(Collections.singletonMap("message", "Attendance recorded."));
            } catch (Exception e) {
                return ResponseEntity.ok(Collections.singletonMap("error", "internal error"+e.getMessage()));
            }

        }

           }

}
