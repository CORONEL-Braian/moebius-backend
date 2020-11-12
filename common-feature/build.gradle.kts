group = "app.möbius"
version = "0.0.0"

/**
 * CRNK:
 *  . https://mvnrepository.com/artifact/io.crnk/crnk-spring
 *  . https://bintray.com/crnk-project/maven/crnk-setup-spring
 */
dependencies {
    api("io.crnk", "crnk-setup-spring", "3.3.20200920055408")

    implementation(project(":api"))
    implementation(project(":service"))

    implementation(project(":library_base"))
    api(project(":library_test_utils"))

    implementation(project(":deceased-core"))
    implementation(project(":infrastructure-core"))
    implementation(project(":data"))
}