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

    
## Important Links
   For api documentation I am using swagger-Open Document Api which can be accessed from below Link
      
   Swagger : http://localhost:8080/swagger-ui/index.html

   Some of the API links are described below:

    some of the links are:
    1.Add a new survivor to the database
	  **POST /survivor**
	 ```json
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
	```	

2.```GET /survivor/{id}``` -This is to fetch the details of a survivor
	    
3.```GET /survivor/infection/list``` -This is to fetch the list of infected/Non-Infected survivors it takes a parameter of either **INFECTED/NON-INFECTED**

4.```GET /survivor/list``` -This is to fetch the list of all survivors

5.```PUT /survivor/{id}``` -This is to update details of a survivor

6.```PUT /survivor/{id}/last-location``` -This is to update last location of a  survivor
```json
{
  "longitude": "string",
  "latitude": "string"
}
```

7.```POST /survivor/{id}/infection-reporter``` -This is to report an infected survivor the id represents the infected survivor id and below request body carries reporter details

**Request:**
```json
{
  "reported_date": "2022-06-20",
  "infection_reporter_id": 0
}
```
9.```GET /survivor/{id}/infection-reporters``` -This is to fetch the list of infection reporters of a survivor

10.```GET /api/resource``` -This is to fetch All resources registered It contains filters like survivorId, resourceType[MEDICATION, AMMUNITION,MEDICAL, OTHER], description,and pagination details

11.```GET /api/resource/{id}``` -This is to fetch details of a resource with id

12.```POST /api/resource``` -This is to add a new resource for a survivor
```json
        {
          "survivor_id": 0,
          "resource_type": "WATER",
          "description": "2L"
        }
```
13. ```GET /api/resource/survivor/{survivorId}``` -This is to fetch all resources of a survivor with id
13.```GET /api/robot``` -This is to Fetch all robots in a readable json format as below
```json
[
  {
    "model": "02U3L",
    "serialNumber": "OAN98YIG3K6ALFW",
    "manufacturedDate": "2022-07-27T15:21:55.1883188+00:00",
    "category": "Land"
  },
  {
    "model": "129VZ",
    "serialNumber": "HPUEUCPYXIJON7N",
    "manufacturedDate": "2022-07-12T15:21:55.188297+00:00",
    "category": "Land"
  }...]
```


   
**Storage**

For Storage I am using H2- databasewhich is an in-memory database and saving to file for persistence

H2 DB UI: http://localhost:8080/h2-console   
```
jdbcUrl:jdbc:h2:file:/data/apocalypsedb
username :sa
password :password
```

## Assumptions
1.**Survivor Object is not overloaded with resources, during fetching of survivor but this can be changed by uncommenting the below line in toSurvivorDto method in Survivor Entity**
```
survivorDto.setResources(this.resource.stream().map(Resource::toResourceDto).collect(Collectors.toList()));
```
    
## Build
Download or clone the project from the link below:
https://github.com/ikatanyi/ioco.git

    mvn clean build

## Run
    mvn spring-boot:run
 *Run packaged jar*
```
    java -jar dir-path\apocalypse\target\apocalypse-0.0.1-SNAPSHOT.jar
```
## OTHER
  1. *The project contains a docker file for running the application in a docker container*

  2. *The project has a kubernetes service setup for deploying the application in a kubernetes cluster*
  
  3. *The project contains a README.md file for the purpose of documentation*

## Licence

    IOCO
