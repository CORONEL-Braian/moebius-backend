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
 *  https://github.com/ronmamo/reflections
 *  https://mvnrepository.com/artifact/org.springframework/spring-orm
 *  https://mvnrepository.com/artifact/org.springframework/spring-jdbc
 */
dependencies {
    api(project(":domain"))

    implementation("org.reflections:reflections:0.9.12")
    implementation("org.springframework", "spring-orm", "2.5.1")
    implementation("org.springframework", "spring-jdbc", "2.0.6")

    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))
    implementation(kotlin("reflect"))
}