# terminalVelocity
##APCS Spring 2016 Final Project
by Anthony Zeng and Roy Kim

##Final Note:
Unfortunately, we couldn't get the GUI working in time. However, we do have a fully functional version that works on the terminal.

###To Mr. K:
I, Roy Kim, did not make commits to my branch for the beginning part of the project. I had problems with branching and did not branch at first. However, I made my commits in the master branch for the beginning and then I branched off to a branch called Roy. After a while, I had troubles in my branch and so I created a new branch called Roy2.

##Description:
We are trying to create a terminal search engine that is able to look up prices and details for hotels and flight fares. The user will be able to enter paramters to narrow their search.

##Instructions:
- javac terminalVelocity.java
- run java terminalVelocity
- Enter your parameters (There are defaults for some and those will be stated)
- A list of results will be printed and you can navigate your options with your numpad or with your numbers
- Choosing a hotel will open up a web page that will allow you to book it for the price you chose (depending on how fast you react)
- if a skyscanner net null error appears, then something was wrong with your parameters. We did put restrictions on dates but some scenarios such as booking a hotel for 3 months, are events that we cannot take into account since the API is in charge of that.

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

### 5/24/2016-5/25/2016
* Worked on formatting the code to a more readable format

### 5/26/2016 - 5/28/2016
* Merging errors
* Started a new branch called Roy2 
* Methods and classes renamed for a more organized project
* Began new concept of storing the Hotels as Objects rather than storing Hotel information in Strings
* New Hotel Autosuggest Service replaced old one
* Hotel and agent Ids are now able to be stored
* Autosuggest is now able to print out country and city names

### 5/30/2016 - 5/31/2016
* Place and entityIDs are now able to be printed by the Hotel Autosuggest service
* Hotel Session Objects and Hotel Entry Object are being developed

### 6/1/2016
* Started the GUI

### 6/2/2016 - 6/5/2016
* Cleaned up unneccessary files
* Continued work on Hotels
* Continued work on GUI

### 6/6/2016
* Finished Terminal Hotels Search
