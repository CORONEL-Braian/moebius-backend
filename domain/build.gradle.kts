group = "app.möbius"
version = "0.0.0"

/**
 * https://mvnrepository.com/artifact/org.hibernate/hibernate-core
 * https://mvnrepository.com/artifact/org.postgresql/postgresql
 */
dependencies {
    api("javax.persistence", "javax.persistence-api", "2.2")
    api("org.hibernate", "hibernate-core",  "5.4.21.Final")

    api("io.crnk", "crnk-spring", "2.6.20180430102904")

    implementation(project(":build-src"))
}

