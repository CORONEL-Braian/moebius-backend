version = "0.0.0"

apply {


}

/**
 * https://mvnrepository.com/artifact/org.springframework.security/spring-security-web
 */
dependencies {
    implementation("org.springframework", "spring-context", "5.3.0")
    implementation("org.springframework.security", "spring-security-config", "5.4.2")

    implementation("org.springframework.boot:spring-boot-starter-web")
}