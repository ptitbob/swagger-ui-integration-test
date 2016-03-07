package org.shipstone.swagger.demo.ws.rs;

import org.shipstone.swagger.demo.exception.PersonAlreadyPersisted;
import org.shipstone.swagger.demo.exception.PersonNotExistException;
import org.shipstone.swagger.demo.model.Person;
import org.shipstone.swagger.demo.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * @author francois
 */
@Path("person")
@Produces({
    "application/json", "application/xml"
})
public class PersonEndpoint {

  @Inject
  private PersonService personService;

  @GET
  public List<Person> getPersonList() {
    return personService.getList(null);
  }

  @GET
  @Path("{personId:[0-9]*}")
  public Person getPerson(@PathParam("personId") Integer personId) throws PersonNotExistException {
    return personService.get(personId);
  }

  @POST
  @Consumes("application/x-www-form-urlencoded")
  public Response createPerson(
      @FormParam("firstname") String firstName
      , @FormParam("lastname") String lastName
  ) throws PersonAlreadyPersisted {
    Person person = personService.persist(new Person(firstName, firstName));
    URI personUri = UriBuilder
        .fromResource(PersonEndpoint.class) 
        .path(String.valueOf(person.getId())) 
        .build();
    return Response.created(personUri).build();
  }

}
