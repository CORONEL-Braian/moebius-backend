TODO: Add name strategy

### Data Jpa Query

 * https://www.baeldung.com/spring-data-jpa-query
 
#### When an Enum is used in JpaRepository nativeQuery

> You must to transform the value of the parameter to a String using .name() (or receive a String as a parameter) and cast that value of type String to the specific Enum that is needed.

    @Query(
        value = "SELECT some_routine(CAST(:#{#environmentNamedParam.name()} as environment))",
        nativeQuery = true
    )
    fun yourFunction(
        @Param("environmentNamedParam") environmentParam: Environment
    ) : Boolean


 
 
 
 ___
### Modules

 * https://reflectoring.io/spring-boot-modules/
 * https://www.baeldung.com/spring-boot-custom-auto-configuration

___
### @QueryParams

 * https://stackoverflow.com/a/28993810/5279996
 
---
### @TestPropertySource

 * https://www.baeldung.com/spring-test-property-source

___
### Headers

[Read HTTP Headers](https://www.baeldung.com/spring-rest-http-headers)