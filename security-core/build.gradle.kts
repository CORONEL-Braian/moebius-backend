version = "0.0.0"

apply {
    plugin("org.jetbrains.kotlin.jvm")
}

/*plugins {
//    TODO: Create plugin does not in submodule
    id("org.springframework.boot") version "2.3.2.RELEASE"
//    id("io.spring.convention.spring-sample-boot")
}*/

//
dependencies {
    implementation(project(":feature_credential_managment", "default"))
//
//    implementation(project(":spring-security-config"))
//    implementation(project(":spring-security-web"))
//    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")

//    testCompile project(':spring-security-test')
//    testCompile 'org.springframework.boot:spring-boot-starter-test'

}