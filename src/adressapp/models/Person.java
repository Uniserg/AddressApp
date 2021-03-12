package adressapp.models;

import javafx.beans.property.*;
import adressapp.requests.models.PersonEntity;
import adressapp.utils.DateUtil;

import java.time.LocalDate;

public class Person {
    private final Integer id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final StringProperty city;
    private final IntegerProperty postalCode;
    private final ObjectProperty<LocalDate> birthday;

    public Person(Integer id, String firstName, String lastName, String street, String city, Integer postalCode, LocalDate birthday) {
        this.id = id;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.postalCode = new SimpleIntegerProperty(postalCode);
        this.birthday = new SimpleObjectProperty<>(birthday);
    }

    public Person(PersonEntity personEntity) {
        this(personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                personEntity.getStreet(),
                personEntity.getCity(),
                personEntity.getPostalCode(),
                DateUtil.parse(personEntity.getBirthday()));
    }

    public Person() {
        this(null,null, null, null, null,  -1, null);
    }

    public Integer getId() {
        return id;
    }

    public int getPostalCode() { return postalCode.get(); }

    public String getCity() { return city.get(); }

    public String getFirstName() { return firstName.get(); }

    public StringProperty getFirstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }

    public StringProperty getLastNameProperty() { return lastName; }

    public String getStreet() { return street.get(); }

    public LocalDate getBirthday() { return birthday.get(); }

    public void setStreet(String street) { this.street.set(street); }

    public void setCity(String city) { this.city.set(city); }

    public void setFirstName(String firstName) { this.firstName.set(firstName); }

    public void setLastName(String lastName) { this.lastName.set(lastName); }

    public void setBirthday(LocalDate birthday) { this.birthday.set(birthday); }

    public void setPostalCode(int postalCode) { this.postalCode.set(postalCode); }
}
