package com.doo.study.dytransit.POJO;

import java.util.List;

/**
 * Created by dooyoungki on 12/30/15.
 */
public class Properties {
    //car
    private String address;
    private String model;
    private String license_plate;
    private int fuel_level;
    private String engine_type;
    private String internal_cleanliness;
    private String description;
    private int seats;
    private int doors;

    //bike
    private int id;
    private int available_bikes;

    //taxi
    private List<Company> companies;

    public static class Company{
        private String name;
        private String phone;
    }
}
