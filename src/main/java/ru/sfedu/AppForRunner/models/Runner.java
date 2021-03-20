package ru.sfedu.AppForRunner.models;


import com.opencsv.bean.CsvBindByName;

public class Runner {
    @CsvBindByName(required = true)
    private Long runnerId;

    @CsvBindByName(required = true)
    private String firstName;

    @CsvBindByName(required = true)
    private String lastName;

    @CsvBindByName(required = true)
    private Long age;

    @CsvBindByName(required = true)
    private Long weight;

    @CsvBindByName(required = true)
    private Long height;

    public Runner() {};

    public void setId(Long runnerId) {
        this.runnerId = runnerId;
    }

    public Long getId() {
        return runnerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getAge() {
        return age;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getWeight() {
        return weight;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Runner{" +
                "runnerId=" + runnerId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}