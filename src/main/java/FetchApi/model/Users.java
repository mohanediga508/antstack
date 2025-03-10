package FetchApi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "users") // Avoids conflicts with reserved keywords
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Generates UUID automatically
    @Column(name = "user_id")
    private UUID userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal present_wage = new BigDecimal("1000.00"); // Default value

    @Column(nullable = false)
    private BigDecimal half_day_wage = new BigDecimal("500.00"); // Default value


}
