package org.shipstone.swagger.demo.ws.rs.mapper;

import org.shipstone.swagger.demo.exception.PersonNotExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author francois
 */
@Provider
public class PersonNotExistExceptionMapper implements ExceptionMapper<PersonNotExistException> {
  @Override
  public Response toResponse(PersonNotExistException e) {
    return Response.status(404).entity("error message : " + e.getMessage()).build();
  }
}
