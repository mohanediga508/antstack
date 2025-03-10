package FetchApi.Controllor;


import FetchApi.Service.AttendanceService;
import FetchApi.model.Salary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    @Autowired
    private AttendanceService attendanceService;



    @GetMapping(value = "/{userId}/{date}", produces = "application/json")
    public ResponseEntity<?> generateSalary(@PathVariable int userId, @PathVariable String date) {
        try {
            LocalDate requestDate = LocalDate.parse(date); // Parse the date
            BigDecimal totalSalary = attendanceService.calculateSalary(userId, requestDate);

            return ResponseEntity.ok(new Salary(userId, totalSalary));

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use YYYY-MM-DD.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
