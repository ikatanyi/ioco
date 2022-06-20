# IOCO ROBOT APOCALYPSE Project

## Task Description
The year is 2050 and the world as we know it has been taken over by robots. Created as 
once friendly robots, have now turned against humankind, especially software engineers 
like yourself. Their mission is to transform everyone into mindless zombies for their 
entertainment. You as a resistance member (and the last survivor who knows how to code), 
was designated to develop a system to meet the following requirements:-

* Develop a REST API  which will store information about the survivors, as well as the resources 
they own.

* In order to accomplish this, the API must fulfil the following use cases:
  Add survivors to the database
* A survivor must have a name, age, gender, ID and last location (latitude, longitude).
* A survivor also has an inventory of resources (which you need to declare upon the 
  registration of the survivor). This can include Water, Food, Medication and Ammunition.
  Update survivor location
* A survivor must have the ability to update their last location, storing the new 
  latitude/longitude pair in the base (no need to track locations, just replacing the previous 
  one is enough).
* Flag survivor as infected
  In a chaotic situation like this, it's inevitable that a survivor may get transformed into a 
  zombie. When this happens, we need to flag the survivor as infected.
  A survivor is marked as infected when at least three other survivors report their 
  contamination.

* Connect to the Robot CPU system
  Connect to the robot CPU system to get a list of all robots and their known locations. Robots 
  have two categories (Flying robots and land robots). You need to sort this information in a 
  meaningful and intuitive way for humans to understand and process. You can use this link 
  to get the list of robots
  https://robotstakeover20210903110417.azurewebsites.net/robotcpu


## Software Specifications
    * java 1.8
    * Maven
    * Springboot V2.5
    * H2 Database

## Assumptions
    *
    
## Important Links
   For api documentation I am using swagger-Open Document Api which can be accessed from below Link
      
   *Swagger : http://localhost:8080/swagger-ui/index.html

    some of the links are:
       1. POST /survivor  - Add a new survivor to the database
        {
          "name": "John Watt",
          "age": 20,
          "gender": "MALE",
          "latitude": "-24.0E",
          "longitude": "44.0W",
          "resources": [
            {
              "survivor_id": 0,
              "resource_type": "WATER",
              "description": "2L"
            },
            {
              "survivor_id": 0,
              "resource_type": "MEDICATION",
              "description": "ASPIRIN"
            }
          ]
        }

        2. GET /survivor/{id} -This is to fetch the details of a survivor
        3. GET /survivor/infection/list -This is to fetch the list of infected/Non-Infected survivors it takes a parameter of either **INFECTED/NON-INFECTED**
        4. GET /survivor/list -This is to fetch the list of all survivors
        5. PUT /survivor/{id} -This is to update details of a survivor
        6. PUT /survivor/{id}/last-location -This is to update last location of a  survivor

    
    * For Storage I am using H2- databasewhich is an in-memory database and saving to file for persistence
      H2 DB UI: http://localhost:8080/h2-console
         *jdbcUrl:jdbc:h2:file:/data/apocalypsedb
         *username :sa
         *password :password
    
## Build
    mvn clean build

## Run
    mvn spring-boot:run

## Licence

    IOCO
