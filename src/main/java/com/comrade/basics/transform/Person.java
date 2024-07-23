package com.comrade.basics.transform;

import com.comrade.util.Util;

import lombok.Data;

@Data
public class Person {
    private String firstName;
    private Integer age;

    public Person() {
        this.firstName = Util.fakerInstance().name().firstName();
        this.age = Util.fakerInstance().random().nextInt(0, 100);
    }
}
