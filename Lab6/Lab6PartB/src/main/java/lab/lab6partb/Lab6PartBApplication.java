package lab.lab6partb;

import lab.lab6partb.domain.Course;
import lab.lab6partb.domain.Department;
import lab.lab6partb.domain.Grade;
import lab.lab6partb.domain.Student;
import lab.lab6partb.repository.CourseRepository;
import lab.lab6partb.repository.DepartmentRepository;
import lab.lab6partb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab6PartBApplication implements CommandLineRunner {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab6PartBApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Department department = new Department();
		department.setName("Computer Science");
		Department department2 = new Department();
		department2.setName("Mathematics");
		departmentRepository.save(department);
		departmentRepository.save(department2);

		Course course1 = new Course();
		course1.setName("EA");
		Course course2 = new Course();
		course2.setName("ASD");
		courseRepository.save(course1);
		courseRepository.save(course2);

		Student student1 = new Student();
		student1.setStudentNumber("111");
		student1.setName("John Doe");
		student1.setDepartment(department);
		Student student2 = new Student();
		student2.setName("Jane Doe");
		student2.setStudentNumber("222");
		student2.setDepartment(department);
		Student student3 = new Student();
		student3.setStudentNumber("333");
		student3.setName("Mary Doe");
		student3.setDepartment(department2);
		Grade grade1 = new Grade();
		grade1.setGrade(5);
		grade1.setCourse(course1);
		Grade grade2 = new Grade();
		grade2.setGrade(3);
		grade2.setCourse(course2);
		Grade grade3 = new Grade();
		grade3.setGrade(2);
		grade3.setCourse(course2);
		student1.addGrade(grade1);
		student2.addGrade(grade2);
		student3.addGrade(grade3);

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);

		System.out.println("Students in Computer Science department are: ");
		studentRepository.findByDepartment_Name("Computer Science").forEach(System.out::println);

		System.out.println("Students in ASD course are: ");
		studentRepository.findByGrades_Course_Name("ASD").forEach(System.out::println);

	}
}
