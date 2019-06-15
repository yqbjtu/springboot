package com.yq.util.other;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

/**
 * Simple to Introduction
 * className: Employee
 *
 * @author EricYang
 * @version 2019/4/19 13:56
 */

@Data
public class Employee {

    private int id;

    private String name;

    private int age;

    private BigDecimal salary;

    private String designation;

    private Address address;

    private long[] phoneNumbers;

    private Map<String, String> personalInformation;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n----- Employee Information-----\n");

        sb.append("ID: " + getId() + "\n");

        sb.append("Name: " + getName() + "\n");

        sb.append("Age: " + getAge() + "\n");

        sb.append("Salary: $" + getSalary() + "\n");

        sb.append("Designation: " + getDesignation() + "\n");

        sb.append("Phone Numbers: " + Arrays.toString(getPhoneNumbers()) + "\n");

        sb.append("Address: " + getAddress() + "\n");

        sb.append("Personal Information:" + getPersonalInformation() + "\n");

        sb.append("*****************************");

        return sb.toString();

    }

}