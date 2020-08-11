plugins {
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    java
    kotlin("jvm") version "1.4-M3"
}

group = "app.möbius"
version = "0.0.0"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
}

dependencies {
//    implementation(project(":feature_sign_up", "default"))

    implementation(kotlin("stdlib-jdk8"))

//    implementation("org.springframework.boot", "spring-boot-autoconfigure", "2.3.2.RELEASE")
//    api("org.springframework", "spring-context", "5.2.8.RELEASE")

    implementation("org.springframework.boot:spring-boot-starter-web")



    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}