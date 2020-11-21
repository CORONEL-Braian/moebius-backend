group = "app.möbius"
version = "0.0.0"



dependencies {
//    Used in data-core and features
    api("org.springframework.boot:spring-boot-starter-web")

    api("org.postgresql",  "postgresql",  "42.2.16")

    implementation(project(":library_test_utils"))
}