#Demo - swagger-ui-integration
####*some projects to introduce the use of the [swagger-UI-integration library](https://github.com/ptitbob/swagger-ui-integration)*

---

##Introduction

I made a basic module ([module base](#module-base)), which contains all the APIs in my application demonstration, including REST endpoints and class of the REST application.

>This is certainly not the right way, this is just as an example. ;)

But this will allow me to better show the operation of the swagger-ui-integration library over a few examples.

* [Module simple](#module-simple) - library basic use.
* [Module parameterized](#module-parameterized) - example for use annoation with paramters as your own index file.
* [Module resources  parameterized](#module-resources-parameterized) - example for use resources configuration file.

>You could find the dependency in [maven central repository » swagger-ui-integration](http://mvnrepository.com/artifact/org.shipstone/swagger-ui-integration).

##Module base

The application offers CRUD operations and provide data as JSON. Here are some examples to call with [HTTPie](https://github.com/jkbrzt/httpie) and [cURL](https://curl.haxx.se) (with use module baseApplication for configure base REST application):

###Create

```shell
http --form POST localhost:8080/simple/api/person firstname='John' lastname='Doe'
```

```
curl -i -X POST -d 'firstname=John&lastname=Doe' http://localhost:8080/simple/api/person
```

###Read

* List 

```shell
http GET localhost:8080/simple/api/person
```
```shell
curl -i -X GET http://localhost:8080/simple/api/person
```

if you want xml : 

```shell
http GET localhost:8080/simple/api/person accept:application/xml
```
```shell
curl -i -X GET --header 'accept: application/xml' http://localhost:8080/simple/api/person
```

* Single entity

```shell
http GET localhost:8080/simple/api/person/1
```
```shell
curl -i -X GET http://localhost:8080/simple/api/person/1
```

###Update

```shell
echo '{"id":1,"firstname":"John","lastname":"DOE"}' | http PUT localhost:8080/simple/api/person/1
```
```shell
curl -i -X PUT -d '{"id":1,"firstname":"John","lastname":"DOE"}' http://localhost:8080/simple/api/person/1
```

###Delete

```shell
http DELETE localhost:8080/simple/api/person/1
```
```shell
curl -i -X DELETE http://localhost:8080/simple/api/person/1
```

##Module simple

This this simpliest way to use library, only one empty class with two annotations and depdendency...

First, include dependency :

```xml
<dependency>
    <groupId>org.shipstone</groupId>
    <artifactId>swagger-ui-integration</artifactId>
    <version>1.0-RC1</version>
</dependency>
```

in second time, create class describe swagger-ui-integration basic configuration : 

```java
@SwaggerUIConfiguration
@ApplicationPath("api")
public class SimpleApplicationConfiguration extends Application {
}
```
Your class must extend `AbstractSwaggerURLRewriter` class and use annotations `@RewriteConfiguration` and `@SwaggerUIConfiguration`.

Deploy the war to a application server (i.e. wildfly). Now you can test with http command ;).

The libraty create two url (in this case, default url) : 

* [http://localhost:8080/simple/api/swagger](http://localhost:8080/simple/api/swagger) : get REST API description using swagger format.
* [http://localhost:8080/simple/api-docs/](http://localhost:8080/simple/api-docs/) : access to the embedded swagger UI site from library.

***And voila !!***

##Module parameterized

This module show how configure swagger configuration by annotation and adding your ```index.html``` in place of the default page.

First time, set the swagger-ui-integration in your pom.

Second time, create your configuration class : 

```java
@SwaggerUIConfiguration(
    apiDocPath = "documentation"
    , apiDocIndex = "rest-index/index.html"
    , restApplicationPackageAsRoot = false
    , restApplicationPackage = "org.shipstone.swagger.demo.ws.rs"
)
public class ParameterizedApplicationConfiguration {
}
```

As you see, i set the `restApplicationClass` because the rest applicationPath was store in `@ApplicationPath`. 

I also set the parameters `apiDocPath` and `apiDocIndex` : 

* `apiDocPath` : i change default url to *documentation', see the url examples below.
* `apiDocIndex` : to use *my custom index page*, this file store in my resources directory. If you want do same, see below in chapter *[use your own page](#use-your-own-page)*.


You also see that I have defined the swagger of configuration for generating base of swagger description.

```java
@ApplicationPath("rest")
@SwaggerDefinition(
    info = @Info(title = "swagger-ui-integration demo", version = "1", description = "Global description for basic application demo")
    , tags = {
        @Tag(name = "person", description = "Action on person !!")
    }
)
public class RestApplication extends Application { }
```

* [http://localhost:8080/param/rest/swagger](http://localhost:8080/param/rest/swagger) : get REST API description using swagger format.
* [http://localhost:8080/param/documentation/](http://localhost:8080/param/documentation/) : access to the embedded swagger UI site from library.


##Module resources parameterized

I used the same value as the parameterized module, but I have placed in the resources configuration file ... To show you the configuration of load management, I disabled the integration at the annotation, to reactivate in the configuration file.

```java
@ApplicationPath("rest")
@SwaggerUIConfiguration(
    active = false // set activation to false, file configuration set to true
)
public class RestApplication extends Application {
}
```

configuration file : 

```properties
#Swagger-UI-integration configuration file
swagger.active=true
swagger.apiDocPath = documentation
swagger.apiDocIndex = rest-index/index.html
swagger.restApplicationPackageAsRoot = false
swagger.restApplicationPackage = org.shipstone.swagger.demo.ws.rs
```

##Use your own page

For use your own documentation page, you could use the library default page (see source here : [github.com/.../index.html](https://github.com/ptitbob/swagger-ui-integration/blob/master/src/main/resources/inside-docs/index.html)) and customize !

You ***must keep*** all header file and javascript part. Don't change the ids, because the swagger-ui javascript uses them. And more particularly, those two lines there:

```html
<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
```

In my example, I changed that:

* The logo, header title and url target
* banner background color
* hide all input

My index.html: [github.com/.../parameterized/.../resources/rest-index/index.html](https://github.com/ptitbob/swagger-ui-integration-test/blob/master/parameterized/src/main/resources/rest-index/index.html).

