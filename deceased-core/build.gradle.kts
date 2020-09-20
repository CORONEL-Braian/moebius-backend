group = "app.mobius"
version = "0.0.0"

repositories {
}

dependencies {
    implementation(project(":domain"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}