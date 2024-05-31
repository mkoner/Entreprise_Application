package lab.lab4parta.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Collection<Employee> employees;
    public Department() {}
    public Department(String name) {
        this.name = name;
        employees = new ArrayList<Employee>();
    }

    public void add(Employee emp) {
        employees.add(emp);
        emp.setDepartment(this);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
