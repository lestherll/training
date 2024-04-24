package com.lestherll.assignments.jdbc;

import java.sql.*;
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


        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT studentname, studentlastname FROM studentinformation")
        ) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        }


        System.out.println("\n==PREPARED STATEMENTS==");
        queryName(conn, "rhoda");

//        int foovalue = 500;
//        PreparedStatement prepared = conn.prepareStatement("SELECT * FROM mytable WHERE columnfoo = ?");
//        st.setInt(1, foovalue);
//        ResultSet rs = st.executeQuery();
//        while (rs.next()) {
//            System.out.print("Column 1 returned ");
//            System.out.println(rs.getString(1));
//        }
        conn.close();

//        String url = "jdbc:postgresql://172.21.0.3:5432/student?user=postgres&password=postgres&ssl=true";
//        Connection conn = DriverManager.getConnection(url);

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
}
