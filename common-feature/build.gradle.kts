group = "app.möbius"
version = "0.0.0"

/**
 * CRNK: https://mvnrepository.com/artifact/io.crnk/crnk-spring
 */
dependencies {
    api("io.crnk", "crnk-spring", "2.6.20180430102904")

    implementation(project(":api"))
    implementation(project(":service"))

    implementation(project(":library_base"))
    api(project(":library_test_utils"))

    implementation(project(":deceased-core"))
    implementation(project(":infrastructure-core"))
    implementation(project(":data"))
}