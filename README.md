

http --form POST localhost:8080/simple/api/person firstname='John' lastname='Doe'

http GET localhost:8080/simple/api/person accept:application/xml

http GET localhost:8080/simple/api/person

http GET localhost:8080/simple/api/person/1

echo '{"id":1,"firstname":"John","lastname":"DOE"}' | http PUT localhost:8080/simple/api/person/1