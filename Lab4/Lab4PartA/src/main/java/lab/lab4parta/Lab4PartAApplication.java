package lab.lab4parta;

import lab.lab4parta.domain.Book;
import lab.lab4parta.domain.Department;
import lab.lab4parta.domain.Employee;
import lab.lab4parta.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab4PartAApplication implements CommandLineRunner {
    @Autowired
    private DepartmentRepository departmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab4PartAApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Department department = new Department("Sales");
        Employee employee = new Employee("John", department);
        department.add(employee);
        departmentRepository.save(department);

        System.out.println("Fetch department from DB");
        departmentRepository.findAll().forEach(System.out::println);

        Book book = new Book("book", "author");

    }
}
