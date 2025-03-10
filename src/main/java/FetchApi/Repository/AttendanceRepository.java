package FetchApi.Repository;

import FetchApi.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {


    boolean existsByUserIdAndDate(int userId, Date date);
    List<Attendance> findByUserId(int userId);

    Optional<Attendance> findByUserIdAndDate(int userId, Date date);
}
