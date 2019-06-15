package com.yq.util.other;

import lombok.Data;

/**
 * Simple to Introduction
 * className: Address
 *
 * @author EricYang
 * @version 2019/4/19 13:57
 */

@Data
public class Address {

    private String street;
    private String city;
    private int zipCode;

    @Override

    public String toString(){
        return getStreet() + ", "+getCity()+", "+getZipCode();
    }

}
