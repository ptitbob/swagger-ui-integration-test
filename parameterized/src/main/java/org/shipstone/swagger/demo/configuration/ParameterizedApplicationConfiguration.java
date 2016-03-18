package org.shipstone.swagger.demo.configuration;

import org.shipstone.swagger.demo.ws.rs.RestApplication;
import org.shipstone.swagger.integration.annotation.SwaggerUIConfiguration;

/**
 * @author francois
 */
@SwaggerUIConfiguration(
    restApplicationClass = RestApplication.class
    , apiDocPath = "documentation"
    , apiDocIndex = "rest-index/index.html"
)
public class ParameterizedApplicationConfiguration {
}
