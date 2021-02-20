group = "app.mobius"
version = "0.0.0"



dependencies {
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.postgresql",  "postgresql",  "42.2.16")

    implementation(project(":library_test_utils"))
}