package org.example.solid_principles.CourseWork;

public class Employee {
    private String name;
    protected int hoursWorked;

    double calculateSalary() {
        return hoursWorked * 10;
    }
}

class FullTimeEmployee extends Employee {
    @Override
    double calculateSalary() {
        return hoursWorked + 2000;
    }
}
