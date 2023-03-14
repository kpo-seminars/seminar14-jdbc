package ru.hse;

import ru.hse.model.Student;
import ru.hse.repository.StudentRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        createStudent();
        printAllStudents();
    }

    private static void printAllStudents() {
        List<Student> students = StudentRepository.findAll();

        students.forEach(System.out::println);
    }

    private static void createStudent() {
        StudentRepository.save(
                new Student()
                        .setFirstName("Rick")
                        .setLastName("Sanchez"));
    }
}