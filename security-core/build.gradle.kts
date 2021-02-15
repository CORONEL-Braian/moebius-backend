version = "0.0.0"

apply {


}

/**
 * https://mvnrepository.com/artifact/org.springframework.security/spring-security-web
 */
dependencies {
    implementation(project(":feature_credential_managment", "default"))

    implementation("org.springframework.security", "spring-security-config", "5.4.2")
    implementation("org.springframework.security", "spring-security-web", "5.4.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.vault", "spring-vault-core", "2.1.1")


//    Testing
    testImplementation("org.springframework.security", "spring-security-test", "5.4.2")
}