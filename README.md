# Flight Ticket

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview

### Description
Keep tabs on your next vacation trip with FlightTicket. Search through endless amount of flights and saved your favorites.

### App Evaluation
- **Category:** Traveling 
- **Mobile:** mobile first experience.
- **Story:** Allows users to search and save flights.
- **Market:** Frequent travelers or anyone looking to plan a vacation could enjoy this app.
- **Habit:** Users can look at new/updated flight throughout the day many times. Users can explore endless amount of trips to exotic locations. 
- **Scope:** Flight Tikced will start as being able to search and save flights.
## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
* Allow users to sign up for a new account or login to an existing account
* Allow users to search for flights
* Allow users to view saved flights


### 2. Screen Archetypes

* Login/Sign up page
   * The User will be able to login/Sign up
   * Users will have to create an account
* Home page
    * User will be able to navigate to search page
    * User will be able to navigate to saved flights
* Search Flights Page
   * User will be able to scroll through flights
   * User will be able to search flights
* Saved Flights Page
    * User will be able view saved flights
    * Page will display user information
* Individual Flight page
    * Displays Flight Inforamtion
    * User can save flight
    * User can remove flight from saved list
### 3. Navigation

**Flow Navigation**

* Login / Sign up 
   * Home
* Home
   * Search Flights
   * Saved Flights
* Search Flights 
   * Individual Flight information
   * Home
* Saved Flights 
   * Individual Flight information
   * Home


## Wireframes

<img style="border: 1px solid rgba(0, 0, 0, 0.1);" width="800" height="450" src="https://www.figma.com/embed?embed_host=share&url=https%3A%2F%2Fwww.figma.com%2Ffile%2FeZfiGk743UQYUoKXyuezms%2FFlight-Tickets%3Fnode-id%3D0%253A1"/>



## Schema 

### Models
#### User

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | userID     | int   | unique id for the user  |
   | username       | String| unique username created by user |
   | password         | String     | used for authentication |
 

   
#### Flight

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | id      | int   | unique id for the flight |
   | minPrice        | String| minimum price that flight has been found |
   | carrier      | String  | airline  |
   | placeDep     | Place | take off location |
   | currency     | String | date when event is taking place|
   |placeDist| Place | destination of flight|
   
#### Place

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | id      | int   | unique id for the place|
   | stringId        | String| unique id for place |
   | airportName      | String  | name of airport |
   | cityyName     | String | name of city airport is located in |
   | countryName     | String | name of country is located in |
   
### Networking
#### List of network requests by screen
   - Search Flight Page(Read/GET) Query Flights user searches
       1. You need to get RetrofitClient, you will use it to send requests:
`RetrofitClient retrofit = RetrofitClient.getClient();` 
        2. Specify what call you what to make and pass parameters:
`retrofit.getFlightsCall([your params here])`
      OR
`retrofit.getPlacesCall([your params here])`
        3. Use enqueue when you make your requests in Activities, it will make requests in a separate thread and Android Studio will not throw exceptions at you:
retrofit.getFlightsCall([your params here]).enqueue([CallBack object here])
        4. Your CallBack object will look something like this:
`new Callback<>() {`
