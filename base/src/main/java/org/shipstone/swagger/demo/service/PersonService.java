package org.shipstone.swagger.demo.service;

import org.shipstone.swagger.demo.exception.PersonAlreadyPersisted;
import org.shipstone.swagger.demo.exception.PersonNotExistException;
import org.shipstone.swagger.demo.model.Person;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

/**
 * @author francois
 */
@ApplicationScoped
public class PersonService {

  private Map<Integer, Person> personMap;

  public enum PersonSortType {
    ID, NAME
  }

  @PostConstruct
  public void postConstruct() {
    personMap = new TreeMap<>();
  }

  public Person get(Integer personId) throws PersonNotExistException {
    if (!personMap.containsKey(personId)) {
      throw new PersonNotExistException(personId);
    }
    return personMap.get(personId);
  }

  public List<Person> getList(PersonSortType personSortType) {
    List<Person> personList = new ArrayList<>(personMap.values());
    if (personSortType != null) {
      switch (personSortType) {
        case ID:
          Collections.sort(personList, (person1, person2) -> person1.getId().compareTo(person1.getId()));
          break;
        case NAME:
          Collections.sort(personList, (person1, person2) -> (person1.getFirstname() + "-" + person1.getLastname()).compareTo((person2.getFirstname() + " " + person2.getLastname())));
          break;
      }
    }
    return personList;
  }

  public Person persist(Person person) throws PersonAlreadyPersisted {
    if (person.getId() != null && personMap.containsKey(person.getId())) {
      throw new PersonAlreadyPersisted(person);
    } else {
      person.setId(generateId());
    }
    personMap.put(person.getId(), person);
    return person;
  }

  public void merge(Person person) throws PersonNotExistException {
    if (!personMap.containsKey(person.getId())) {
      throw new PersonNotExistException(person.getId());
    }
    personMap.put(person.getId(), person);
  }

  public Person delete(Person person) {
    return delete(person.getId());
  }

  public Person delete(Integer personId) {
    return personMap.remove(personId);
  }

  private Integer generateId() {
    Map.Entry<Integer, Person> personEntry = ((TreeMap) personMap).lastEntry();
    return personEntry == null ? 1 : personEntry.getKey() + 1;
  }

}
