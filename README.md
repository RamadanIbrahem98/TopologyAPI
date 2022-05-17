# Topology API

A Simple REST API for Handling Topologies.

## Used Tools

- [Java 8](https://openjdk.java.net/)
- [Spring Boot](https://spring.io/)
- [Gson](https://github.com/google/gson)
- [JUnit5](https://junit.org/)
- [AssertJ](https://joel-costigliola.github.io/assertj/)
- [Maven](https://maven.apache.org/)
- [Postman](https://www.getpostman.com/)
- [SpringDoc](https://springdoc.org/)

## API

    GET /topologies
    GET /topologies/{topologyId}/components
    GET /topologies/{topologyId}/netlist/{netlistNode}
    GET /topologies/read/{fileName}
    GET /topologies/write/{topologyId}
    DELETE /topologies/{topologyId}
    
    for accessing the swagger documentaion
    GET /swagger-ui.html
