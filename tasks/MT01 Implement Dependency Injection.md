## Review the Implementation of Dependency Injection

The starter repo for this project has already implemented Spring
Boot's DI framework. Take some time to look at the dependency
autowiring in the starter code for this project and see that every
Service, Repository, and Controller component classes contain 
`@Autowired` annotations which act to inject dependencies into 
each component.

In order for a class to be injected with @Autowired it must be
marked with one of the following component annotations:
- `@RestController` for any controller who needs to be routed to
- `@Service` for any service a controller may need to call
- `@Repository` for any repositories
- `@Component` for generic components whose lifecycle needs to be
managed by the Application context.
    - *NOTE:* These are `SeedData` and `Datastore`

Classes who are marked as a component will be automatically intantiated
and managed by Spring's `ApplicationContext`. Additionally, they will
now be injectable using the `@Autowired` field-injection annotation.

After completing these steps and removing the unnecessary code,
you should have no constructors in any Spring component class
and your `App.main` method should begin the Spring application.

After running the `App::main` method you will be able to access endpoints 
through Postman by sending a GET request to an endpoint such as 
`http://localhost:8080/checkables`

### Naming conventions

Check out the Naming Conventions section in the `README.md`.
The MT01 Tests are Reflection tests ensuring component names
are set up properly. These tests need to know the name of
your fields and classes, so do not change any class names and 
use full camelCased names for each component:  
   - e.g. `patronService` not `patServ`
   
Reading any failing Reflection tests output will indicate if
it is failing due to a naming mismatch by returning a
`NoSuchFieldException` and indicating the name it was looking for.

### Completion

The dependency injection functionality of this project should already
be implemented and thus the MT01 tests should all pass. You should take
this time to familiarize yourself with the `@Autowired` injection 
procedure. 
1. Take a look at the controller classes a notice what types of components
are being injected.
2. Notice how the service components also have repositories as dependencies
injected via `@Autowired`.
3. Notice as well that none of these components have constructors. They are
instantiated by the framework.

Run the gradle command:
`./gradlew -q clean :test --tests 'com.tct.MT01*'`
and make sure all tests pass.