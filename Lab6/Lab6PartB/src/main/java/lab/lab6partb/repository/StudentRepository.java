package lab.lab6partb.repository;

import lab.lab6partb.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByDepartment_Name(String name);
    List<Student> findByGrades_Course_Name(String courseName);
}
