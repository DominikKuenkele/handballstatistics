package com.kuenkele.handballstatistics;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;

public class Player {

    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜ]+(([ -][a-zA-ZäöüÄÖÜ])?[a-zA-ZäöüÄÖÜ]*)*$", message = "first name has invalid characters")
    private String firstName;

    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜ]+(([ -][a-zA-ZäöüÄÖÜ])?[a-zA-ZäöüÄÖÜ]*)*$", message = "last name has invalid characters")
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}
