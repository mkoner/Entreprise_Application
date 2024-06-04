package lab.lab6partb.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue
    private int id;
    private String studentNumber;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    @OneToMany(cascade = CascadeType.ALL)
    List<Grade> grades = new ArrayList<>();
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNumber='" + studentNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
