# Spring-Boot-MongoDB-JWT
### Spring Boot base for projects with MongoDB and JWT based security.
This is a quick-start base for java projects with Spring Boot, MongoDB and configured JWT security.
### Running
* Download this base
* Start the MongoDB service/daemon in your system 
* Run project by `Application.class` or by `mvn clean install`, `java -jar target/*.jar`.

---
### JWT security
Page `http://localhost:8080/api/hello` is secured. To access this page, you need to do the following:

* POST request to `http://localhost:8080/api/signup` with body
```json
  username: "user",
  password: "12345"
```
* GET request to `http://localhost:8080/api/auth`, then take token from responce and use it in header to access secured page
* GET request to `http://localhost:8080/api/hello` with header:
```json
  x-auth-token: <your token here>
```

Security is based on custom filter 
