version = "0.0.0"

repositories {
}

dependencies {
    implementation(project(":feature_credential_managment", "default"))
    implementation(project(":feature_sign_up", "default"))
    implementation(project(":library_test_utils", "default"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
