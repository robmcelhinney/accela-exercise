# Accela Coding Exercise by Robert McElhinney
Simple Java application with database access using sqlite.
REST API built using Spring Boot.

## Requirements
* JDK 1.8 or later
* Maven

## Install & Run
    $ git clone git@github.com:robmcelhinney/accela-exercise.git
    $ cd accela-exercise/accela
    $ mvn package
    $ mvn spring-boot:run

## Test
    $ mvn clean test

## Usage
Rest API is running on http://localhost:8080

*   /person - List Persons
*   /person/add - Add Person (id, firstName, lastName)
*   /person/update - Edit Person (firstName, lastName)
*   /person/delete/{id} - Delete Person (id)
*   /person/count - Count Number of Persons
*   /address/add - Add Address to person [multiple required] (id, street, city, state, postalCode)
*   /address/update - Edit Address (street, city, state, postalCode)
*   /address/delete/{id} - Delete Address (id)

## Example Usage
    POST http://localhost:8080/person/add
    Body - {"firstName":"DONALD","lastName":"DUCK","id":1}
    Response - 200 OK

    
    GET http://localhost:8080/person
    Response - 200 OK - [
        {
            "firstName": "DONALD",
            "lastName": "DUCK",
            "id": 1,
            "address": {
                "street": null,
                "city": null,
                "state": null,
                "postalCode": null,
                "id": 0,
                "personId": 0
            }
        }
    ]

    
    POST http://localhost:8080/address/add
    Body - {"id":1,"personId":1,"street":"1313 Webfoot Street","city":"Duckburg","state":"DISNEYLAND","postalCode":"1234"}
    Response - 200 OK

    GET http://localhost:8080/person
    Response - 200 OK - [[
        {
            "firstName": "DONALD",
            "lastName": "DUCK",
            "id": 1,
            "address": {
                "street": "1313 Webfoot Street",
                "city": "Duckburg",
                "state": "DISNEYLAND",
                "postalCode": "1234",
                "id": 1,
                "personId": 1
            }
        }
    ],


## Info
First time using Java Spring and working with databases through Java. Went a bit over the time so didn't do as much testing as I'd like.