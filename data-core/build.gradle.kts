group = "app.mobius"
version = "0.0.0"

//TODO: Inject secret hibernate credential
/*open class LoadHibernateCredential : DefaultTask() {

}*/

tasks.register<Copy>("copyReport") {
    from(file("$rootDir/${project.name}/testFile.txt"))
    into(file("$buildDir/new/testFile2.txt"))
}

/**
 * Sources:
 *  https://mvnrepository.com/artifact/org.hibernate/hibernate-core
 *  https://mvnrepository.com/artifact/org.postgresql/postgresql
 */
dependencies {
    implementation(project(":domain"))

    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))

    implementation("org.hibernate",  "hibernate-core",  "5.4.21.Final")
    implementation("org.postgresql",  "postgresql",  "42.2.16")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")   //TODO: Refactor mandatory in this module
}