# Getting Started 

There are several ways to run a Spring Boot application on your local machine.<BR> 
Prefered way is to execute the OnlineGameStoreApplication.java class from your IDE.


# Endpoints

<h3>GET list of all the games in the store.</h3>
Unsecured endpoint allowing visitors to browse thro games in the store.

GET http://localhost:8080/api/games

<h3>GET Game by it's name</h3>
Unsecured enpoint allowing visitors to search particular game in store by it's name.

http://localhost:8080/api/games/?name=Gwent


<h3>Authorization </h3>
The task required that admins had power to post, delete and update games. For that I decided to use JWT based authority, 
which turned out to be a little overkill for such a small app, but i decided to stick with it, as it is a industry standard. 
To get authorized, you need to acces:

POST http://localhost:8080/api/login 

endpoint with admin creditentials json body: 

{
  "username":"admin",
  "password":"admin"
}

The returning value is JWT Token lasting 1 hour. The token must be provided in header named Authorization and before token must be placed word "Bearer ". <br>
This is easiest achieved in Postman, clicking on Authorization tab, choosing Bearer Token and pasting returned token from login.

<h3>POST Game to database</h3>
Secured endpoint allowing admins to add new games to the store.

http://localhost:8080/api/games/post

example body

{
   "id": 0,
   "name": "Witcher 2: Assasin of kings",
   "genre": "rpg",
   "description": "Witcher 2",
   "developer": "CD Projekt RED",
   "releaseDate": "2023-04-06",
   "dollarPrice": 6.99
}



<h3>DELETE Game by it's id.</h3>
Secured enpoint for admins to delete games from store by their id.

http://localhost:8080/api/games/delete/2


<h3>PUT Update game providing its id and body.</h3>

http://localhost:8080/api/games/update/1

example body 

{
    "id": 0,
    "name": "Witcher 1",
    "genre": "rpg",
    "description": "Witcher 1",
    "developer": "CD Projekt RED",
    "releaseDate": "2023-04-06",
    "dollarPrice": 3.99
}


For all the calls i created public space on Postman under the link: <br>
https://elements.getpostman.com/redirect?entityId=20001291-f72cda6a-9c2b-431e-a108-4e754685bfaa&entityType=collection

# Tests
