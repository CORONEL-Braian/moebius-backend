# Testing 

 - [Docs Spring](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing)
 - [Unit testing Spring MVC with JUnit 5](https://www.infoworld.com/article/3543268/junit-5-tutorial-part-2-unit-testing-spring-mvc-with-junit-5.html)
 - [Spring 3.1 M2: Testing with @Configuration Classes and Profiles](https://spring.io/blog/2011/06/21/spring-3-1-m2-testing-with-configuration-classes-and-profiles)
 - [Integration Tests](https://reflectoring.io/spring-boot-test/)


### Difference between fake, stubs and mocks:

 - **Fake** objects actually have working implementations, but usually take some shortcut which makes them not suitable for production
 - **Stubs** provide canned answers to calls made during the test, usually not responding at all to anything outside what's programmed in for the test. Stubs may also record information about calls, such as an email gateway stub that remembers the messages it 'sent', or maybe only how many messages it 'sent'.
 - **Mocks** are what we are talking about here: objects pre-programmed with expectations which form a specification of the calls they are expected to receive.

 - [Source](https://stackoverflow.com/a/346440/5279996)
___

### Services

 - [Test Service Layer in Spring Boot](https://youtu.be/gIb_m06XeQE)
 
### Mockito

- [Mockito Series](https://www.baeldung.com/mockito-series)
- [Injecting Mocks in Spring](https://www.baeldung.com/injecting-mocks-in-spring)