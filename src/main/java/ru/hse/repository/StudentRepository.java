package ru.hse.repository;

import lombok.experimental.UtilityClass;
import ru.hse.model.Student;
import ru.hse.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class StudentRepository {
    private static final String FIND_ALL_SQL = "select * from student";

    private static final String INSERT_STUDENT_SQL = "insert into student (first_name, last_name) values (?, ?)";

    public static List<Student> findAll() {
        List<Student> resultStudents = new ArrayList<>();

        try (Connection connection = ConnectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL))
        // рассказать про Statement и отличие от PreparedStatement
        {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student()
                        .setId(resultSet.getInt("id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"));

                resultStudents.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultStudents;
    }

    public static Student save(Student student) {
        try (Connection connection = ConnectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }
}
