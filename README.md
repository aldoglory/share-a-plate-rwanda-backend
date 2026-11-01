# share-a-plate-rwanda-backend

## Share A Plate Rwanda
** name: ** Ruterana Gloire 
** ID: ** 27717
** Project: ** share-a-plate-rwanda-backend
# How to run the API
**1 make sure you have the tools. ** you need Java 17 or higher , Maven and PostgreSQL.
**2 Create the database. ** Open pgAdmin and run:
``` sql
CREATE DATABASE share_a_plate;
```
The tables will create on their own once you run the project at first time 

**3. add your database password ** `src/main/resources/application.properties` and put in your own PostgreSQL password

**4. Start the API. ** From the project folder where `pom.xml` is, run :
```bash
mvn clean spring-boot:run
```
When you see `Tomcat started on port 8083` , the API is ready at:
```
http://locolahost:8083
```
**5. Try it in postman. **Import `postman/shre-a-plate-rwanda.postman_collection.json` use baseUrl `localhost:8083`

To stop the API, press `Ctrl +c` in the terminalS
