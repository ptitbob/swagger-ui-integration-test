package org.shipstone.swagger.demo.configuration;

import org.shipstone.swagger.integration.annotation.SwaggerUIConfiguration;

/**
 * @author francois Robert
 */
@SwaggerUIConfiguration(
    apiDocPath = "documentation"
    , apiDocIndex = "rest-index/index.html"
    , restApplicationPackageAsRoot = false
    , restApplicationPackage = "org.shipstone.swagger.demo.ws.rs"
)
public class ParameterizedApplicationConfiguration {
}
