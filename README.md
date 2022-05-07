# Topology API

A Simple REST API for Handling Topologies.

## Used Tools

- [Java 8](https://openjdk.java.net/)
- [Spring Boot](https://spring.io/)
- [Gson](https://github.com/google/gson)
- [Postman](https://www.getpostman.com/)

## API

    GET /topologies
    GET /topologies/{topologyId}/components
    GET /topologies/{topologyId}/netlist/{netlistNode}
    GET /topologies/read/{fileName}
    GET /topologies/write/{topologyId}
    DELETE /topologies/{topologyId}

[![Try in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/13215606-c65891c4-5759-4225-b8a3-1ccd0c57ecf3?action=collection%2Ffork&collection-url=entityId%3D13215606-c65891c4-5759-4225-b8a3-1ccd0c57ecf3%26entityType%3Dcollection%26workspaceId%3D5d45d646-dd61-4541-86f2-58a2cc673df9#?env%5BTopology%20Environment%5D=W3sia2V5IjoiVVJMIiwidmFsdWUiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJlbmFibGVkIjp0cnVlLCJ0eXBlIjoiZGVmYXVsdCIsInNlc3Npb25WYWx1ZSI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCIsInNlc3Npb25JbmRleCI6MH1d)

## TODO

- Testing (Unit & Integration)

- Custom Error Handling (Not Returning 200 for Failed Cases)

- Documentation on the Class Level
