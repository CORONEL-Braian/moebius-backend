group = "app.mobius"
version = "0.0.0"

dependencies {

//    Core
    implementation(project(":api-core"))
    implementation(project(":service"))
    implementation(project(":deceased-core"))
    implementation(project(":infrastructure-core"))
    implementation(project(":data"))
    implementation(project(":library_base"))
    api(project(":library_test_utils"))

    api(project(":json-api"))

}