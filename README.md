#  Coding Challenge

## Assumptions

- Every request to add recipe overwrites already stored recipe
- Expires At date can be slightly different from date recipe was sent as server date might vary


## Design Decisions
- It is necessary to have a service class that implements most of the functionality of a recipe (e.g save).
- Very little logic should be implemented in the controller class to allow for code reusability
- Proper validation for request to ensure empty text are not saved.

## Production Concerns Addressed
- Improper input of recipe name, expires in data. There was need to add validation on the 
input fields and return a response message with the validation on each field.
- Ability to change time of expiry value (30s) from config without necessary redeploying code base.
- Proper logging of error messages to allow for tracing and resolution of issues in production

## Choice of Technology
-  [Spring boot](https://spring.io/projects/spring-boot) was chosen because of its large ecosystem of users and support.
- It is relatively easy to scaffold with lots of out of box support in the IDE ([IntelliJ](https://www.jetbrains.com/idea/promo) for [java](https://www.jetbrains.com/idea))
- Things like request validation are support out of the box, setting response status is easy,
 marshalling and unmarshalling of request and response in controllers.
- Easy dependency injection process. [read more](https://www.baeldung.com/spring-dependency-injection#)
- In-built light weight http server
- Load balancing is very easy to spring framework using [Spring cloud gateway Support] (https://spring.io/projects/spring-cloud-gateway).


## Error Handling
- Spring comes with out of the box [ApiAdvice](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc) that handles several errors . Friendly messages can 
be deplayed for each exception handled.
- Info Logs are displayed on the running console when an exception is raised. This can be integrated 
to [Apache Kafka](https://kafka.apache.org/) for log monitoring  or some other distributed tracing platforms.
