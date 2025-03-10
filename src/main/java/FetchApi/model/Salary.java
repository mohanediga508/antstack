package FetchApi.model;

import java.math.BigDecimal;


public class Salary {
    private int user_id;
    private BigDecimal total_salary;

    public Salary(int user_id, BigDecimal total_salary) {
        this.user_id = user_id;
        this.total_salary = total_salary;
    }
}
