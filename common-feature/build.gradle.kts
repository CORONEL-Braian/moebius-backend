group = "app.möbius"
version = "0.0.0"

dependencies {
    api(project(":json-api"))

    implementation(project(":api"))
    implementation(project(":service"))

    implementation(project(":library_base"))
    api(project(":library_test_utils"))

    implementation(project(":deceased-core"))
    implementation(project(":infrastructure-core"))
    implementation(project(":data"))
}