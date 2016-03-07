package org.shipstone.swagger.demo.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * @author François Robert
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class Person {

  @XmlAttribute
  private Integer id;

  @XmlElement
  private String firstname;

  @XmlElement
  private String lastname;

  public Person() {
  }

  public Person(String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Person)) return false;
    Person person = (Person) o;
    return Objects.equals(getId(), person.getId()) &&
        Objects.equals(getFirstname(), person.getFirstname()) &&
        Objects.equals(getLastname(), person.getLastname());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getFirstname(), getLastname());
  }

  @Override
  public String toString() {
    return "Person{" +
        "id=" + id +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        '}';
  }

}
