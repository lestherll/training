package com.lestherll.assignments.jdbc;

import java.util.Date;

public record Student(long id,
                      String firstName,
                      String lastName,
                      String address,
                      Date birthdate,
                      Date joined) {

    public Student setFirstName(String newFirstName) {
        return new Student(id, newFirstName, lastName, address, birthdate, joined);
    }

    public Student setLastName(String newLastName) {
        return new Student(id, firstName, newLastName, address, birthdate, joined);
    }

    public Student setAddress(String newAddress) {
        return new Student(id, firstName, lastName, newAddress, birthdate, joined);
    }

    public Student age(Date birthdate) {
        return new Student(id, firstName, lastName, address, birthdate, joined);
    }

    public Student setFirstName(Date newJoined) {
        return new Student(id, firstName, lastName, address, birthdate, newJoined);
    }
}
