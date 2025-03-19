package org.example.odjectClass;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Animal {

    private String type;
    private String name;
    private int age;

    public Animal(){}

    public Animal (String type, String name, int age){
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
