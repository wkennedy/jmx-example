package com.github.darogina.jmx.model;

import org.springframework.jmx.export.annotation.*;

/**
 * User: davidrogina
 * Date: 12/10/13
 */
@ManagedResource(description = "Person MBean")
public class Person {

    private static final String PERSON_FIRST_NAME = "Person First Name";
    private static final String PERSON_LAST_NAME = "Person Last Name";
    private static final String PERSON_AGE = "Person Age";

    private static Person INSTANCE = null;
    private static final String DEFAULT_FIRST = "Default First";
    private static final String DEFAULT_LAST = "Default Last";
    private static final String DEFAULT_AGE = "26";
    private static final String DEFAULT_READ_ONLY = "true";

    private String firstName = DEFAULT_FIRST;
    private String lastName = DEFAULT_LAST;
    private Integer age = Integer.valueOf(DEFAULT_AGE);
    private boolean readOnly = Boolean.valueOf(DEFAULT_READ_ONLY);

    private Person() {}

    public static synchronized Person getInstance() {
        if (INSTANCE == null) {
            return new Person();
        }

        return INSTANCE;
    }

    @ManagedAttribute(defaultValue = DEFAULT_FIRST, description = PERSON_FIRST_NAME)
    public String getFirstName() {
        return firstName;
    }

    @ManagedAttribute(defaultValue = DEFAULT_FIRST)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ManagedAttribute(defaultValue = DEFAULT_LAST)
    public String getLastName() {
        return lastName;
    }

    @ManagedAttribute(defaultValue = DEFAULT_LAST)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManagedAttribute(defaultValue = DEFAULT_AGE)
    public Integer getAge() {
        return age;
    }

    @ManagedAttribute(defaultValue = DEFAULT_AGE)
    public void setAge(Integer age) {
        this.age = age;
    }

    @ManagedAttribute(defaultValue = "true", description = "Whether or not the Read Only property is actually read only.")
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @ManagedOperation(description = "Increment the person's age by a specified value")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "value", description = "Value to increment age.")
    })
    public int incrementAge(int incrementValue) {
        this.age += incrementValue;

        return this.age;
    }
}
