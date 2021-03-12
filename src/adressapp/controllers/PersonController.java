package adressapp.controllers;

import javafx.fxml.FXML;

public abstract class PersonController<T> {
    @FXML
    protected T firstName;
    @FXML
    protected T lastName;
    @FXML
    protected T city;
    @FXML
    protected T street;
    @FXML
    protected T postalCode;
    @FXML
    protected T birthday;

    public T getFirstName() {
        return firstName;
    }

    public void setFirstName(T firstName) {
        this.firstName = firstName;
    }

    public T getLastName() {
        return lastName;
    }

    public void setLastName(T lastName) {
        this.lastName = lastName;
    }

    public T getCity() {
        return city;
    }

    public void setCity(T city) {
        this.city = city;
    }

    public T getStreet() {
        return street;
    }

    public void setStreet(T street) {
        this.street = street;
    }

    public T getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(T postalCode) {
        this.postalCode = postalCode;
    }

    public T getBirthday() {
        return birthday;
    }

    public void setBirthday(T birthday) {
        this.birthday = birthday;
    }
}
