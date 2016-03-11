package org.shipstone.swagger.demo;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.shipstone.swagger.demo.ws.rs.SimpleApplication;
import org.shipstone.swagger.integration.AbstractSwaggerURLRewriter;
import org.shipstone.swagger.integration.SwaggerUIConfiguration;

/**
 * @author francois
 */
@RewriteConfiguration
@SwaggerUIConfiguration()
public class SimpleApplicationConfiguration extends AbstractSwaggerURLRewriter {
}
