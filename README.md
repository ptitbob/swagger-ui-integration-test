#Demo - swagger-ui-integration
####*some projects to introduce the use of the swagger-UI-integration library*

---

##Introduction

I made a basic module (module base), which contains all the APIs in my application demonstration, including REST endpoints and class of the REST application.

>This is certainly not the right way, this is just as an example. ;)

But this will allow me to better show the operation of the swagger-ui-integration library over a few examples.

* [Module simple](#module-simple) - library basic use

##Module base

The application offers CRUD operations and provide data as JSON. Here are some sample call with [HTTPie](https://github.com/jkbrzt/httpie) and [cURL](https://curl.haxx.se):

###Create

```shell
http --form POST localhost:8080/simple/api/person firstname='John' lastname='Doe'
```

```
curl -i -X POST -d 'firstname=John&lastname=Doe' http://localhost:8080/simple/api/person
```

###Read

* List 

```
http GET localhost:8080/simple/api/person
```
```
curl -i -X GET http://localhost:8080/simple/api/person
```

if you want xml : 

```shell
http GET localhost:8080/simple/api/person accept:application/xml
```
```
curl -i -X GET --header 'accept: application/xml' http://localhost:8080/simple/api/person
```

* Single entity

```
http GET localhost:8080/simple/api/person/1
```
```
curl -i -X GET http://localhost:8080/simple/api/person/1
```

###Update

```shell
echo '{"id":1,"firstname":"John","lastname":"DOE"}' | http PUT localhost:8080/simple/api/person/1
```
```
curl -i -X PUT -d '{"id":1,"firstname":"John","lastname":"DOE"}' http://localhost:8080/simple/api/person/1
```

###Delete

```
http DELETE localhost:8080/simple/api/person/1
```
```
curl -i -X DELETE http://localhost:8080/simple/api/person/1
```

##Module simple

This this simpliest way to use library, only one empty class with two annotations and depdendency...

First, include dependency :

```xml
<dependency>
    <groupId>org.shipstone.swagger</groupId>
    <artifactId>swagger-ui-integration</artifactId>
    <version>0.6</version>
</dependency>
```

in second time, create class describe swagger-ui-integration basic configuration : 

```java
@RewriteConfiguration
@SwaggerUIConfiguration(
    restApplicationClass = SimpleApplication.class
)
public class SimpleApplicationConfiguration extends AbstractSwaggerURLRewriter {
}
```
