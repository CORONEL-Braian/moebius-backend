group = "app.mobius"
version = "0.0.0"

/**
 * https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
 */
dependencies {
    api("org.springframework.data", "spring-data-jpa", "2.3.4.RELEASE")

    api(project(":data-core"))
}
