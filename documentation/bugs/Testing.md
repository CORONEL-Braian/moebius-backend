#### Error:

`org.junit.jupiter unresolve` reference

#### Solution:
 
1. Delete all cache folders including:
    .gradle
    .idea

2. Then invalidate cache & restart.
3. Open any gradle file (settings.gradle.kts)
4. In the IDE at the top right click in `Link Gradle project`
5. Finally, specifies that JUnit 5 should be used to execute the tests by gradle or from the IDE

[Source](https://stackoverflow.com/a/66286369/5279996)

____

#### Error:

`kotlin.UninitializedPropertyAccessException: lateinit property appAuthorizationJpaRepository has not been initialized`

#### Solution:

Replace from: `import org.junit.Test`
To: `import org.junit.jupiter.api.Test`