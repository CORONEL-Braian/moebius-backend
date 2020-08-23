group = "app.mobius"
version = "0.0.0"

repositories {
}

dependencies {
    implementation(project(":domain"))

    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))

    testImplementation("junit", "junit", "4.12")
}
