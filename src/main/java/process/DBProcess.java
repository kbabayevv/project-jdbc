package process;

import connection.DBConnection;
import exception.PostgresException;
import entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DBProcess {
    private static Connection connection = DBConnection.makeConnection();
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public static void createTeacherTable() {
        try {
            String query = "CREATE TABLE teachers(id BIGINT PRIMARY KEY, name VARCHAR(20)," +
                    " surname VARCHAR(30), salary FLOAT)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Teacher table has created successfully !");
        } catch (SQLException e) {
            throw new PostgresException(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new PostgresException(e.getMessage());
                }
            }
        }
        DBConnection.closeConnection();
    }

    public static void insertTeacher(List<Teacher> listOfTeachers) {
        try {
            String query = "INSERT INTO teachers(id, name, surname, salary) VALUES(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            for (Teacher teacher : listOfTeachers) {
                preparedStatement.setLong(1, teacher.getId());
                preparedStatement.setString(2, teacher.getName());
                preparedStatement.setString(3, teacher.getSurname());
                preparedStatement.setFloat(4, teacher.getSalary());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            System.out.println("Data inserted successfully !");
        } catch (SQLException e) {
            throw new PostgresException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new PostgresException(e.getMessage());
            }
        }
        DBConnection.closeConnection();
    }

    public static void updateTeacher() {
        System.out.println("Enter id which teacher you want to update:");
        Scanner sc = new Scanner(System.in);
        long id = sc.nextLong();
        sc = new Scanner(System.in);
        System.out.println("Enter a new name: ");
        String name = sc.nextLine();
        System.out.println("Enter a new surname: ");
        String surname = sc.nextLine();
        sc = new Scanner(System.in);
        System.out.println("Enter a new salary: ");
        float salary = sc.nextFloat();
        try {
            String query = "UPDATE teachers SET name = ?, surname = ?, salary = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setFloat(3, salary);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Teacher updated successfully !");
        } catch (SQLException e) {
            throw new PostgresException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new PostgresException(e.getMessage());
            }
        }
        DBConnection.closeConnection();
    }

    public static void deleteTeacher() {
        System.out.println("Enter id which teacher you want to delete:");
        Scanner sc = new Scanner(System.in);
        long id = sc.nextLong();
        try {
            String query = "DELETE FROM teachers WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Teacher deleted successfully !");
        } catch (SQLException e) {
            throw new PostgresException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new PostgresException(e.getMessage());
            }
        }
        DBConnection.closeConnection();
    }

    public static void findTeacherByID() {
        System.out.print("Enter teacher id: ");
        long id = new Scanner(System.in).nextLong();
        try {
            String query = "SELECT * FROM teachers WHERE id =?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getLong(1));
                teacher.setName(resultSet.getString(2));
                teacher.setSurname(resultSet.getString(3));
                teacher.setSalary(resultSet.getFloat(4));
                System.out.println(teacher);
            }
        } catch (SQLException e) {
            throw new PostgresException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new PostgresException(e.getMessage());
            }
        }
        DBConnection.closeConnection();
    }

    public static void findStudentLikeName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a little letter: ");
        String letter = sc.nextLine();

        String query = "SELECT * FROM teachers WHERE name LIKE '" + letter + "%'";


        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                System.out.println(name + " " + surname);
            }
        } catch (SQLException e) {
            throw new PostgresException(e.getMessage());
        }
    }
}
