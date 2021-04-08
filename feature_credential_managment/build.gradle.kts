version = "0.1"

/**
 * https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test
 */
dependencies {
    implementation(project(":common-feature", "default"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}