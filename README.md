# Lawn mower

## Prerequisites

- Java 14

## Getting started

- Start application with `mvnw spring-boot:run`
- Run curl command:

```
curl -X POST -H "Content-Type: application/json" -d "{\"dimensions\":{\"width\":5,\"height\":5},\"mowers\":[{\"position\":{\"x\":1,\"y\":2,\"orientation\":\"N\"},\"instructions\":\"GAGAGAGAA\"},{\"position\":{\"x\":3,\"y\":3,\"orientation\":\"E\"},\"instructions\":\"AADAADADDA\"}]}" http://localhost:8080/mower/program
```

- Instead, you can open the `index.html` in your browser to get the same result as the previous curl command
- The `LawnMowerApplicationTests.specificationIntegrationTest` also executes the same request and ensures the result is
  as expected

## Commands

- Start application with `mvnw spring-boot:run`
- Run tests with `mvnw test`

## Entrypoint

Execute a mower program: `http://localhost:8080/mower/program`

Request body:

```
{
  "dimensions": {
     "width": 5,
     "height": 5
  },
  "mowers": [
    {
       "position": {
         "x": 1,
         "y": 2,
         "orientation": "N"
       },
       "instructions": "GAGAGAGAA"
    },
    {
       "position": {
         "x": 3,
         "y": 3,
         "orientation": "E"
       },
       "instructions": "AADAADADDA"
    }
  ]
}
```

## What could be improved

- Better validation
  - Instructions
  - Mower position (2 mowers never in the same position)
- Using DTO instead of domain objects directly
- More unit tests
