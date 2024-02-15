# smp
Social media microservice platform

# 1. Application Build ( Core-module, notification-module )
-----------------
command : mvn clean deploy -DskipTests

# 2. Docker image Build command ( Core-module, notification-module )
-----------------
command: docker build -t [tag-name] .

# 3. Docker-compose run command ( Core-module, notification-module )
-----------------
command: docker-compose up -d 


# 4. test ( Core-module, notification-module )
-----------------
userd post-man

#ex :
---------------
add: -
----------------
curl --location 'http://localhost:8081/user' \
--header 'Content-Type: application/json' \
--data '{
"name":"Rashedul islam",
"address":"medul badd"
}'

updated : -
-------------
curl --location --request PUT 'http://localhost:8081/user/1' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Hasib",
    "address": "merul badda"
}'

get-all : -
-----------------------
curl --location 'http://localhost:8081/user/all'

get details by id : -
--------------------------
curl --location 'http://localhost:8081/user/1'

delete user : -
------------------------
curl --location --request DELETE 'http://localhost:8081/user/1'


# 5. test ( notification-module )
-----------------
notification show in console