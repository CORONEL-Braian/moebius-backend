pluginManagement {
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")

        mavenCentral()

        maven("https://plugins.gradle.org/m2/")
    }
}

include("backend")

include("domain")
include("common")

// Feature
include("feature_sign_up")
include("feature_login")
include("feature_legal")

// Library
include("library_base")
include("library_test_utils")
include("feature_third_profile")
