package FetchApi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "attendance", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "date"}) // Ensures each user has only one record per date
})
public class Attendance {

    @Id
    private UUID attendance_id=UUID.randomUUID();

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        PRESENT, HALF_DAY, ABSENT
    }
}
