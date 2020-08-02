pluginManagement {
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")

        mavenCentral()

        maven("https://plugins.gradle.org/m2/")
    }
}

include("app")

include("domain")
include("common")

// Feature
include("feature_sign_up")

// Library
include("library_base")
include("library_test_utils")
