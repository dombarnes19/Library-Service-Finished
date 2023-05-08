## Writing Unit Tests With Mocks

When writing unit tests, it is important to avoid calling any
methods the belong to classes outside of the class we are testing.
Since Spring requires coordination between several different layers
even for the most basic API requests, mocking dependencies is very
important.

For Spring, this means using the `@MockBean` annotation to create
a mock of any **component** we would otherwise be `@Autowiring` in.

We can then use `Mockito.when().thenReturn()` structure to return
dummy data instead of calling the real method of a dependent class.
We can also use `Mockito.verify()` to check that a particular
method did indeed get called. This is useful for `void` methods
which have no return data, and thus we cannot see that they were
called using `Mockito.when().thenReturn()`.

Your task is to write unit tests for the `CheckableService` and
achieve 95% or more code coverage. You will be given a `jacoco`
gradle command to quickly test for code coverage, or you can select
`run with coverage` on your test file to generate a detailed 
report (as an html file) under `build/jacocoHtml`.

Make sure to only `@Autowire` the component under test! All other
dependencies must be mocked. Your test code will be tested using
Reflection again so please make sure to continue following the
naming conventions. You can use the `LibraryServiceTest` as a 
*reference*.

### Completion

Run the gradle command:

`./gradlew -q clean test jacocoTestCoverage`

and make sure all tests pass. (This will also test MT01 and MT02)