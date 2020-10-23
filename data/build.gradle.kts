group = "app.mobius"
version = "0.0.0"

dependencies {
    implementation("org.springframework.data", "spring-data-jpa", "2.3.4.RELEASE")

    api(project(":data-core"))
}
