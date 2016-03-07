package org.shipstone.swagger.demo.exception;

/**
 * @author francois
 */
public class PersonNotExistException extends Exception {

  public PersonNotExistException(Integer personId) {
    super(String.format("Person with Id %d not exist", personId));
  }

}
