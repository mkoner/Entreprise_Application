package lab.lab6partb.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Grade {
    @Id
    @GeneratedValue
    private int id;
    private int grade;
    @OneToOne(fetch = FetchType.LAZY)
    private Course course;
}
