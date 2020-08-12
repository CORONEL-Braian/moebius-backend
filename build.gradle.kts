buildscript {
	repositories {
		mavenCentral()
	}
}

plugins {

//  	Dont remove, because Cannot resolve external dependency
	kotlin("jvm") version "1.4-M3" apply false
	kotlin("plugin.spring") version "1.4-M3"

	id("org.springframework.boot") version "2.3.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"

}

version = "0.0.0"

subprojects {

	apply {
		plugin("org.jetbrains.kotlin.jvm")

		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
	}

	group = "app.möbius"

	repositories {
		maven("https://dl.bintray.com/kotlin/kotlin-eap")
		mavenCentral()
	}

	val implementation by configurations

	dependencies {
		implementation(kotlin("stdlib-jdk8"))
	}

	configure<JavaPluginConvention> {
		sourceCompatibility = JavaVersion.VERSION_1_8
	}


}

