version = "0.0.0"

repositories {
}

dependencies {
    implementation(project(":feature_sign_up", "default"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
