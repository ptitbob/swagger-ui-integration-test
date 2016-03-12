package org.shipstone.swagger.demo.configuration;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.shipstone.swagger.demo.ws.rs.RestApplication;
import org.shipstone.swagger.integration.AbstractSwaggerURLRewriter;
import org.shipstone.swagger.integration.SwaggerUIConfiguration;

/**
 * @author francois
 */
@RewriteConfiguration
@SwaggerUIConfiguration(
    restApplicationClass = RestApplication.class
    , apiDocPath = "documentation"
    , apiDocIndex = "rest-index/index.html"
)
public class ParameterizedApplicationConfiguration extends AbstractSwaggerURLRewriter {
}
