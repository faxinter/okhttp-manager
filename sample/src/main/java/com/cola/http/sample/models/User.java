package com.cola.http.sample.models;

/**
 * @ Author SpringWater
 * @ Date 15/12/15 下午3:17
 * @ Description // Please Add Annotation
 */
public class User {

    public String name;

    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
