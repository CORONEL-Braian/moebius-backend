group = "app.mobius"
version = "0.0.0"

//TODO: Inject secret hibernate credential
//open class LoadHibernateCredential : DefaultTask() {}
/*tasks.register<Copy>("copy") {
    from(file("$rootDir/${project.name}/file.txt"))
    into("$buildDir/newFile.txt")
}*/

/**
 * Sources:
 *  https://mvnrepository.com/artifact/org.hibernate/hibernate-core
 *  https://mvnrepository.com/artifact/org.postgresql/postgresql
 */
dependencies {
    implementation(project(":domain"))

    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))
}