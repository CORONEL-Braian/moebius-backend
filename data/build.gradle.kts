
version = "0.0.0"

dependencies {
    api(project(":data-core"))
}

tasks.test {
    useJUnitPlatform()
}