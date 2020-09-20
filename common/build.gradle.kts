group = "app.möbius"
version = "0.0.0"

repositories {
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":api"))
    implementation(project(":service"))

    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))

    implementation(project(":deceased-core"))
    implementation(project(":data-core"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}