pluginManagement {
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "Moebius-backend"

include("backend")

// Common
include("common")
include("domain")
include("api")
include("service")

// Feature
include("feature_sign_up")
include("feature_login")
include("feature_legal")

// Library
include("library_base")
include("library_test_utils")
include("feature_third_profile")

// Core
include("deceased-core")