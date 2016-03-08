package org.shipstone.swagger.demo.ws.rs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.shipstone.swagger.demo.exception.PersonAlreadyPersisted;
import org.shipstone.swagger.demo.exception.PersonNotExistException;
import org.shipstone.swagger.demo.model.Person;
import org.shipstone.swagger.demo.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
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
@Api(
    value = "Person"
    , description = "Person REST endpoint"
)
public class PersonEndpoint {

  @Inject
  private PersonService personService;

  @GET
  @Produces({
      "application/json", "application/xml"
  })
  @ApiOperation(
      value = "get list of person"
      , notes = "if no person already persited, provide a empty list"
      , response = Person.class
      , responseContainer = "List"
  )
  public List<Person> getPersonList() {
    return personService.getList(null);
  }

  @GET
  @Path("{personId:[0-9]*}")
  @Produces({
      "application/json", "application/xml"
  })
  @ApiOperation(
      value = "get person by Id"
  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "success", response = Person.class)
      , @ApiResponse(code = 404, message = "Person not found", response = String.class)
  })
  public Person getPerson(@PathParam("personId") Integer personId) throws PersonNotExistException {
    return personService.get(personId);
  }

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @ApiOperation("Create person")
  public Response createPerson(
      @FormParam("firstname") String firstName
      , @FormParam("lastname") String lastName
  ) throws PersonAlreadyPersisted {
    Person person = personService.persist(new Person(firstName, lastName));
    URI personUri = UriBuilder
        .fromResource(PersonEndpoint.class) 
        .path(String.valueOf(person.getId())) 
        .build();
    return Response.created(personUri).build();
  }

  @PUT
  @Consumes({"application/json", "application/xml"})
  @Path("{personId:[0-9]*}")
  @ApiOperation(value = "Update person by Id", notes = "Id (path) and Id (person) must match")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "successful")
      , @ApiResponse(code = 404, message = "Person not found", response = String.class)
  })
  public Response updatePerson(@PathParam("personId") Integer personId, Person person) throws PersonNotExistException {
    if (!personId.equals(person.getId())) {
      throw new IllegalStateException("PersonId not match person.id");
    }
    personService.merge(person);
    return Response.ok().build();
  }

  @DELETE
  @Path("{personId:[0-9]*}")
  @ApiOperation("Delete person by Id")
  public Response deletePerson(@PathParam("personId") Integer personId) {
    personService.delete(personId);
    return Response.ok().build();
  }

}
