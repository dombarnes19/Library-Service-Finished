### Implement TDD Unit Test Behavior

Test Driven Development takes place over two parts, testing, and
developing. For this Mastery Task, the testing has been done for you,
and it is up to you now to do the development.

The `LibraryService` is lacking in functionality, only the `save()`
method has been implemented correctly, the rest of the methods are
returning `null` values or dummy data.

Open the `tst.com.bloomtech.library.services.LibraryServiceTest` 
file and read through the unit tests. Each test describes a 
particular behavior the `LibraryService` methods should be able to
perform. First understand these behaviors and then develop the
`LibraryService` class in order to make the tests all pass.

### Completion

Run the gradle command:

`./gradlew -q clean :test --tests 'com.bloomtech.library.services.LibraryServiceTest'`

and make sure all tests pass.