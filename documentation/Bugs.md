#### Error:

`org.junit.jupiter unresolve` reference

#### Solution:
 
1. Delete all cache folders including:
    .gradle
    .idea

2. Then invalidate cache & restart.
3. Finally open any gradle file (settings.gradle.kts) and if in the IDE at the top right a button 
   appears that suggests `Link Gradle Kotlin Script` (or a similar message) to click it.

[Source](https://stackoverflow.com/q/47871181/5279996)

____