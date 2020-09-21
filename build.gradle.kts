import org.springframework.boot.gradle.tasks.bundling.BootJar

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

tasks.register<Copy>("copy") {
	from("/home/braian/Projects/Moebius/Backend/Moebius-backend/HELP.md")
	into("/home/braian/Projects/Moebius/Backend/Moebius-backend/HELP2.md")
}

version = "0.0.0"

/**
 * https://stackoverflow.com/a/57069958/5279996
 */
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

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}

	tasks.getByName<BootJar>("bootJar") {
		enabled = false
	}

	tasks.getByName<Jar>("jar") {
		enabled = true
	}
}