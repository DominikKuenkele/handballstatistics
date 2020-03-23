package com.kuenkele.handballstatistics;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uuid;

    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜ]+(([ -][a-zA-ZäöüÄÖÜ])?[a-zA-ZäöüÄÖÜ]*)*$", message = "first name has invalid characters")
    private String firstName;

    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜ]+(([ -][a-zA-ZäöüÄÖÜ])?[a-zA-ZäöüÄÖÜ]*)*$", message = "last name has invalid characters")
    private String lastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
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
                ", uuid=" + uuid +
                '}';
    }
}
