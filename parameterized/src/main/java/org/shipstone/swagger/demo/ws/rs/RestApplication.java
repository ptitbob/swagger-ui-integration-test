package org.shipstone.swagger.demo.ws.rs;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author francois
 */
@ApplicationPath("rest")
@SwaggerDefinition(
    info = @Info(title = "swagger-ui-integration demo", version = "1", description = "Global description for basic application demo")
    , tags = {
        @Tag(name = "person", description = "Action on person !!")
    }
)
public class RestApplication extends Application {

}
