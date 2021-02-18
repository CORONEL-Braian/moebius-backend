group = "app.mobius"
version = "0.0.0"

dependencies {
    api(project(":data-core"))

//    implementation(project(":library_test_utils"))

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.8.0-M1")
//    implementation("org.junit.jupiter", "junit-jupiter-api", "5.8.0-M1")
//    runtimeOnly("org.junit.jupiter", "junit-jupiter-api", "5.8.0-M1")
}