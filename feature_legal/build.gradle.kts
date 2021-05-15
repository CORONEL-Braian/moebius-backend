group = "app.mobius.backend.feature.legal"
version = "0.0.0"

repositories {
}

dependencies {
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}