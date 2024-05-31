package lab.lab4parta.domain;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    private int employeeNumber;
    private String name;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    public Employee() {}
    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", name='" + name + '\'' +
                '}';
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
