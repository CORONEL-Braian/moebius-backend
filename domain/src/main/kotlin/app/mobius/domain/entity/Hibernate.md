HIBERNATE
---------

##### Naming Strategy

Use camelCase, snake_case or annotation in:
 * @Id -> testUuid and not testUUID
 * @Enumerated
 * 

##### @Entity
*The entity class must have a no-arg constructor. 
The entity class may have other constructors as well. The no-arg constructor must be public or protected.*
    
1. <u>Primary constructor</u>: Mirrors the database table (except uuid)
2. <u>Secondary constructor</u>: Gadget or no-args for JPA specification

[Source](https://stackoverflow.com/a/27966933/5279996)


##### UUID: @Id @GeneratedValue 

   1. <u>Entity withouts relations</u>: Use @GeneratedValue (AUTO is default) y dont use IDENTITY to avoid getting the following error:
            `org.hibernate.id.IdentifierGenerationException: unrecognized id type : pg-uuid -> java.util.UUID`.
         The default assignment of a null value or a random uuid is indistinct, although null is used 
         to scale to entities with relationships.
   2. <u>Entity with relations</u>: ASSIGN A NULL TO ALL UUIDs BY DEFAULT, so that they can be automatically generated
         in the relationships at the time of insertions in the database !!! And use @JoinColumn, dont @PKJoinColumn.

        *Sources:*
         * https://stackoverflow.com/a/64096963/5279996
         * https://stackoverflow.com/a/64057902/5279996

##### @Enum:
[Source](https://stackoverflow.com/a/64021041/5279996)


##### @ManyToMany:

This association has two sides i.e. the owning side and the inverse side

[Source](https://www.baeldung.com/hibernate-many-to-many)


#### @Column
 * uniqueness: 
    *Sources:*
             * https://stackoverflow.com/a/36945146/5279996
             * https://codingexplained.com/coding/java/hibernate/unique-field-validation-using-hibernate-spring
    
   