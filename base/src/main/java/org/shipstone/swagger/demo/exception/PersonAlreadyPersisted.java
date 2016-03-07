package org.shipstone.swagger.demo.exception;

import org.shipstone.swagger.demo.model.Person;

/**
 * @author francois
 */
public class PersonAlreadyPersisted extends Exception {

  public PersonAlreadyPersisted(Person person) {
    super(String.format("person already persisted : %s", person.toString()));
  }
}
