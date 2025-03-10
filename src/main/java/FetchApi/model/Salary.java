package FetchApi.model;

import java.math.BigDecimal;


import java.math.BigDecimal;

public class Salary {
    private BigDecimal totalSalary;
    private int userId;

    public Salary(int userId, BigDecimal totalSalary) {  // Fixed constructor
        this.userId = userId;
        this.totalSalary = totalSalary;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }
}
