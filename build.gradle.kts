import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	// Kotlin plugins
	kotlin("jvm") version "1.5.20"
	kotlin("kapt") version "1.4.31"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.4.31"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.4.31"
	id("org.jetbrains.kotlin.plugin.spring") version "1.4.31"

	// Java plugins
	java
	`java-library`

	// Spring plugins
	id("org.springframework.boot") version "2.5.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"

	// Other plugins
	idea
}

allprojects {
	apply {
		// Kotlin plugins
		plugin("kotlin")
		plugin("kotlin-kapt")
		plugin("kotlin-allopen")
		plugin("kotlin-jpa")
		plugin("kotlin-spring")

		// Java plugins
		plugin("java")
		plugin("java-library")

		// Spring plugins
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")

		// Other plugins
		plugin("idea")
	}

	repositories {
		mavenCentral()
	}

	group = "com.henry"
	version = "0.0.1-SNAPSHOT"

	tasks {
		withType<KotlinCompile>().configureEach {
			sourceCompatibility = "11"
			targetCompatibility = "11"

			kotlinOptions {
				freeCompilerArgs = listOf("-Xjsr305=strict")
				jvmTarget = "11"
			}
		}

		withType<Test>().configureEach {
			useJUnitPlatform()
		}
	}

	java {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.springframework.boot:spring-boot-starter-web")

		implementation("org.axonframework:axon-spring-boot-starter:4.5")

		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("io.github.microutils:kotlin-logging:1.6.22")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}
}

project(":module-order") {
	dependencies {
		implementation(project(":module-common"))

		implementation("mysql:mysql-connector-java")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	}
}

project(":module-payment") {
	dependencies {
		implementation(project(":module-common"))
	}
}
