package org.example.odjectClass;

import java.util.ArrayList;
import java.util.List;

public class Book {

    public String firstName;
    public String lastName;
    public String nationality;
    public String title;
    public int yearOfBirth;
    public int yearOfDeath;
    public List<String> sonnetLines;

    public Book(){}

    public Book (String firstName,String lastName, String nationality,
                 String title, int yearOfBirth, int yearOfDeath){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.title = title;
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
        this.sonnetLines = new ArrayList<>();
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getSonnetLines() {
        return sonnetLines;
    }

    public void setSonnetLines(List<String> sonnetLines) {
        this.sonnetLines = sonnetLines;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getYearOfDeath() {
        return yearOfDeath;
    }

    public void setYearOfDeath(int yearOfDeath) {
        this.yearOfDeath = yearOfDeath;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void addLine(String line) {
        sonnetLines.add(line);
    }

    @Override
    public String toString() {
        return "Book:\n" +
                "Title: " + title + '\n' +
                "--Author--\nFirstName: " + firstName + '\n' +
                "LastName: " + lastName + '\n' +
                "Nationality: " + nationality + '\n' +
                "YearsOfLife: " + yearOfBirth + " - " + yearOfDeath + '\n' +
                "--Text--\n" + sonnetLines;
    }


}
