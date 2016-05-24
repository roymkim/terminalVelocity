# terminalVelocity
##APCS Spring 2016 Final Project
by Anthony Zeng and Roy Kim

##Description:
We are trying to create a terminal search engine that is able to look up prices and details for hotels and flight fares. The user will be able to enter paramters to narrow their search.

##Objectives:
* Ideally, we want to get the hotel search service working first because it is easier and more rudimentary. Flight fares are constantly changing and their multitude is too complicated to work with at first. We will use our ideas for our hotel search and apply them to flight fares later on.

##Problems:
* We originally had a lot of trouble setting up the API
* skyscanner, the company who is providing us the API, had requests done in python and not in java. We spent some time trying to create requests for java.
* Our objective now is to take the response from the servers and turn it into a readable format 

##Development Log:

### 5/12/2016
* Started project.
* Starting writing code to request flight information from API.

### 5/13/2016 - 5/16/2016
* Continued working on Java code to retrieve information from API
* Flight API giving errors 
* Started writing code to request hotel information from API.

### 5/17/2016
* Got code to work to create session with the flight API.
* Still working on polling the flight API session.

### 5/18/2016
* Worked on creating the session for the hotel API
* Worked on polling the session for flight fares

### 5/19/2016 - 5/22/2016
* Completed creating the session for the hotel API
* Completed polling the session for the hotel API
* Response from the servers are successful
* Commited a JSON parsing library
  
### 5/23/2016
* Started working on location AutoSuggest
* Changed out json parsing library for another one(simple-json)
* Results are parsed and printed out formatted
* Class takes arguments for location and dates
