/**
 * Abstract class representing a general employee.
 */
abstract class Employee {
    protected String firstName;
    protected String lastName;
    protected int id;

    /**
     * Constructor for creating an employee.
     * @param firstName The first name of the employee
     * @param lastName The last name of the employee
     * @param id The employee's identification number
     */
    public Employee(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    /**
     * Default constructor.
     * Initializes fields with default values.
     */
    public Employee() {
        this("Plony", "Almony", 0);
    }

    /**
     * @return The employee's first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the first name of the employee.
     * @param firstName The new first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * @return The employee's last name
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the last name of the employee.
     * @param lastName The new last name
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * @return The employee's ID number
     */
    public int getId() { return id; }

    /**
     * Sets the employee's ID number.
     * @param id The new ID number
     */
    public void setId(int id) { this.id = id; }

    /**
     * @return A string representation of the employee
     */
    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

    /**
     * Compares two employees based on their ID number.
     * @param obj The object to compare with
     * @return true if the ID numbers match, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id;
    }

    /**
     * Abstract method to calculate the employee's salary.
     * @return The employee's earnings
     */
    public abstract double earnings();
}

/**
 * Represents an hourly employee.
 */
class HourlyEmployee extends Employee {
    private int hours;
    private float wage;

    /**
     * Constructor for an hourly employee.
     * @param firstName The first name of the employee
     * @param lastName The last name of the employee
     * @param id The employee ID
     * @param hours The number of hours worked
     * @param wage The hourly wage
     */
    public HourlyEmployee(String firstName, String lastName, int id, int hours, float wage) {
        super(firstName, lastName, id);
        if (hours < 0 || wage < 0) throw new IllegalArgumentException("Hours and wage must be non-negative");
        this.hours = hours;
        this.wage = wage;
    }

    /**
     * Default constructor.
     */
    public HourlyEmployee() {
        this("Plony", "Almony", 0, 0, 0);
    }

    /**
     * @return The number of hours worked
     */
    public int getHours() { return hours; }

    /**
     * Sets the number of hours worked.
     * @param hours The number of hours
     */
    public void setHours(int hours) {
        if (hours < 0) throw new IllegalArgumentException("Hours must be non-negative");
        this.hours = hours;
    }

    /**
     * @return The hourly wage
     */
    public float getWage() { return wage; }

    /**
     * Sets the hourly wage.
     * @param wage The new wage
     */
    public void setWage(float wage) {
        if(wage < 0) throw new IllegalArgumentException("Wage must be non-negative");
        this.wage = wage;
    }

    /**
     * @return A string representation of the hourly employee
     */
    @Override
    public String toString() {
        return "CommissionEmployee{" +  // יש כאן שגיאה, צריך להיות "HourlyEmployee"
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id + '\'' +
                ", hours=" + hours + '\'' +
                ", wage=" + wage + '\'' +
                '}';
    }

    /**
     * @return The earnings of an hourly employee
     */
    @Override
    public double earnings() {
        return hours * wage;
    }
}

/**
 * Represents a commission-based employee.
 */
class CommissionEmployee extends Employee {
    protected float grossSales;
    protected int commission;

    public CommissionEmployee(String firstName, String lastName, int id, float grossSales, int commission) {
        super(firstName, lastName, id);
        if (grossSales < 0 || commission < 0 || commission > 100)
            throw new IllegalArgumentException("Invalid sales or commission");
        this.grossSales = grossSales;
        this.commission = commission;
    }

    public CommissionEmployee() {
        this("Plony", "Almony", 0, 0, 0);
    }

    public float getGrossSales() { return grossSales; }

    public void setGrossSales(float grossSales) {
        if (grossSales < 0) throw new IllegalArgumentException("Sales must be non-negative");
        this.grossSales = grossSales;
    }

    public int getCommission() { return commission; }

    public void setCommission(int commission) {
        if (commission < 0 || commission > 100) throw new IllegalArgumentException("Invalid commission value");
        this.commission = commission;
    }

    @Override
    public double earnings() {
        return grossSales * commission / 100;
    }
}

/**
 * Represents a commission-based employee with a base salary.
 */
class BasePlusCommissionEmployee extends CommissionEmployee {
    private float baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, int id, float grossSales, int commission, float baseSalary) {
        super(firstName, lastName, id, grossSales, commission);
        if (baseSalary < 0)
            throw new IllegalArgumentException("BaseSalary must be non-negative");
        this.baseSalary = baseSalary;
    }

    public BasePlusCommissionEmployee() {
        this("Plony", "Almony", 0, 0, 0, 0);
    }

    public float getBaseSalary() { return baseSalary; }

    public void setBaseSalary(float baseSalary) {
        if (baseSalary < 0)
            throw new IllegalArgumentException("BaseSalary must be non-negative");
        this.baseSalary = baseSalary;
    }

    @Override
    public double earnings() {
        return super.earnings() + baseSalary;
    }
}

/**
 * Class to test the payroll system.
 */
class Payroll {
    public static void main(String[] args) {
        Employee[] employees = {
                new HourlyEmployee("Shalom", "Leibovich", 123, 40, 20.5f),
                new CommissionEmployee("Nahum", "Hadad", 456, 5000, 10),
                new BasePlusCommissionEmployee("Moshe", "Navon", 789, 7000, 5, 500)
        };

        for (Employee e : employees) {
            System.out.println(e.toString() + ", Weekly Salary: " + String.format("%.2f", e.earnings()));
        }
        System.out.println(employees[0].equals(employees[1]));
    }
}
