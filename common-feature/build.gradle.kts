group = "app.möbius"
version = "0.0.0"

/**
 * Katharsis: https://mvnrepository.com/artifact/io.katharsis/katharsis-spring
 */
dependencies {
    implementation("io.katharsis", "katharsis-spring", "3.0.2")


    implementation(project(":api"))
    implementation(project(":service"))

    implementation(project(":library_base"))
    api(project(":library_test_utils"))

    implementation(project(":deceased-core"))
    implementation(project(":infrastructure-core"))
    implementation(project(":data"))
}