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
include("common-feature")

// Main
include("data")
include("domain")
include("api-core")
include("service")

// Core
include("deceased-core")
include("data-core")
include("infrastructure-core")
include("json-api")

// Library
include("library_base")
include("library_test_utils")

// Feature
include("feature_sign_up")
include("feature_login")
include("feature_legal")
include("feature_third_profile")
include("feature_credential_managment")
include("security-core")
include("common-feature-test-utils")
include("secret_manager")
