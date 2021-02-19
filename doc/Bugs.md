`org.junit.jupiter unresolve` reference

Solution: 
 1. Delete all cache folders: 
    .gradle
    .idea
    output
 2. $ ./gradlew clean
 3. Invalidate Cache & restart
 4. Then open anything gradle file (settings.gradle.kts) and click in the `Link Gradle Kotlin Script` button at the top-right

Source: https://stackoverflow.com/q/47871181/5279996