# Movie Info


### Technologies and libraries:
- Java 17
- Spring Boot
- Spring Data
- Spring MVC
- mapStruct
- Lombok
- Gradle
- OpenCSV
- H2 DB


### APIs :


####  Read from CSV and Movie API and save in the DB:

  ```
  GET : http://localhost:8080/movie/save
  ```

####  Get list of all movies:

  ```
  GET : http://localhost:8080/movie/
  ```

####  Find Movie by title:

  ```
  GET : http://localhost:8080/movie/findByTitle?title=
  ```