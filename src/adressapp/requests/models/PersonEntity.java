package adressapp.requests.models;

import adressapp.models.Person;
import adressapp.utils.DateUtil;

public class PersonEntity {
    private Integer id;
    private String firstName;
    private String  lastName;
    private String street;
    private String city;
    private Integer postalCode;
    private String birthday;

    public PersonEntity(Integer id, String firstName, String lastName, String street, String city, Integer postalCode, String birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.birthday = birthday;
    }

    public PersonEntity(Person person) {

        id = person.getId();
        firstName = person.getFirstName();
        lastName = person.getLastName();
        street = person.getStreet();
        city = person.getCity();
        postalCode = person.getPostalCode();
        birthday = DateUtil.format(person.getBirthday());
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "PersonReg{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
