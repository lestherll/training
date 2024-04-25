package com.lestherll.assignments.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class JDBCMain {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/student";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
//        props.setProperty("ssl", "true");
        Connection conn = DriverManager.getConnection(url, props);
        conn.setAutoCommit(false);


//        try (
//                Statement st = conn.createStatement();
//                ResultSet rs = st.executeQuery("SELECT studentrollno, studentname, studentlastname FROM studentinformation")
//        ) {
//            while (rs.next()) {
//                System.out.println(rs.getString(1) + " " + rs.getString(2));
//            }
//        }


        System.out.println("\n==PREPARED STATEMENTS==");
//        queryName(conn, "rhoda");


        getStudents(conn, 10).forEach(System.out::println);

        conn.close();
    }

    public static void queryName(Connection conn, String name) {
        try (
                PreparedStatement prepared =
                        conn.prepareStatement(
                                "SELECT studentname, studentlastname " +
                                        "FROM studentinformation " +
                                        "WHERE lower(studentname) LIKE ?"
                        )
        ) {
            prepared.setString(1, "rhoda");
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Student> getStudents(Connection conn) throws SQLException {
        List<Student> students = new ArrayList<>();

        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(
                                "SELECT studentrollno, studentname, studentlastname, " +
                                        "studentaddress, studentbirthdate, studentjoined " +
                                    "FROM studentinformation"
                        );
        ) {
            while (rs.next()) {
                students.add(
                        new Student(
                                rs.getLong("studentrollno"),
                                rs.getString("studentname"),
                                rs.getString("studentlastname"),
                                rs.getString("studentaddress"),
                                rs.getDate("studentbirthdate"),
                                rs.getDate("studentjoined")
                        )
                );
            }
        }
        return students;
    }

    public static List<Student> getStudents(Connection conn, long limit) throws SQLException {
        List<Student> students = new ArrayList<>();

        try (
                PreparedStatement st = conn.prepareStatement(
                        "SELECT studentrollno, studentname, studentlastname, " +
                        "studentaddress, studentbirthdate, studentjoined " +
                        "FROM studentinformation " +
                        "LIMIT (?)"
                );
        ) {
            st.setLong(1, limit);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                students.add(
                        new Student(
                                rs.getLong("studentrollno"),
                                rs.getString("studentname"),
                                rs.getString("studentlastname"),
                                rs.getString("studentaddress"),
                                rs.getDate("studentbirthdate"),
                                rs.getDate("studentjoined")
                        )
                );
            }
        }
        return students;
    }
}

