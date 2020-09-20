group = "app.möbius"
version = "0.0.0"

dependencies {
    api("org.springframework.boot:spring-boot-starter-web")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}