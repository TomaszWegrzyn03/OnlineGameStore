# Getting Started 

There are several ways to run a Spring Boot application on your local machine.<BR> 
Prefered way is to execute the OnlineGameStoreApplication.java class from your IDE.
This code is using maven dependencies.


# Endpoints

<h3>GET list of all the games in the store.</h3>
Unsecured endpoint allowing visitors to browse thro games in the store.

<code> GET </code> http://localhost:8080/api/games <br>
Response: <br>
![image](https://user-images.githubusercontent.com/101212671/230711588-b476aa58-3fe6-47c2-9578-ec3e081194a2.png)



<h3>GET Game by it's name</h3>
Unsecured enpoint allowing visitors to search particular game in store by it's name.

<code> GET </code>http://localhost:8080/api/games/?name=Gwent <br> <br>
Response: <br>
<img src="https://user-images.githubusercontent.com/101212671/230711323-a56a6cea-5193-49b7-933e-edea090f2b98.png">





<h3>Authorization </h3>
The task required that admins had power to post, delete and update games. For that I decided to use JWT based authority, 
which turned out to be a little overkill for such a small app, but i decided to stick with it, as it is a industry standard. 
To get authorized, you need to acces :

<code> POST </code> http://localhost:8080/api/login  

endpoint with admin creditentials json body: 

<code>{
  "username":"admin",
  "password":"admin"
}</code>

The returned value is JWT Token lasting 1 hour. The token must be provided in header named Authorization and before the token must be placed word "Bearer ". <br>
This is easiest achieved in Postman, clicking on Authorization tab, choosing Bearer Token and pasting returned token from login. <br>
<img src="https://user-images.githubusercontent.com/101212671/230711385-bb50aed1-e134-42d7-bbff-0024cbb1a0fc.png" width="70%">


<h3>POST Game to database</h3>
Secured endpoint allowing admins to add new games to the store.

<code> POST </code> http://localhost:8080/api/games/post

Example body : 

<code>{
   "id": 0,
   "name": "Witcher 2: Assasin of kings",
   "genre": "rpg",
   "description": "Witcher 2",
   "developer": "CD Projekt RED",
   "releaseDate": "2023-04-06",
   "dollarPrice": 6.99
}</code>

Example response : <br>
<img src="https://user-images.githubusercontent.com/101212671/230711544-d44a2bdc-9c63-44af-aade-d8d96a034363.png">




<h3>DELETE Game by it's id.</h3>
Secured enpoint for admins to delete games from store by their id.

</code> DELETE </code> http://localhost:8080/api/games/delete/2

<br>

<h3>PUT Update game providing its id and body.</h3>

<code> PUT </code> http://localhost:8080/api/games/update/1

Example body:

<code>{
    "id": 0,
    "name": "Witcher 1",
    "genre": "rpg",
    "description": "Witcher 1",
    "developer": "CD Projekt RED",
    "releaseDate": "2023-04-06",
    "dollarPrice": 3.99
}</code>


For all the calls i created public space on Postman under the link: <br>
https://elements.getpostman.com/redirect?entityId=20001291-f72cda6a-9c2b-431e-a108-4e754685bfaa&entityType=collection

# Tests
I created tests for service and controller. They might be far from perfect, because i still learn testing, but I did my best for knowledge I have.<br>
![tests1](https://user-images.githubusercontent.com/101212671/230711611-8d63622d-f941-4b4d-9f76-5fbe298dc0bd.jpg)<br>

![test2](https://user-images.githubusercontent.com/101212671/230711614-80ec2731-d5a0-414a-9427-74207108e8a5.jpg)

