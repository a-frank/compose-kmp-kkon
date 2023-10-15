plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlinx.serialization)
	alias(libs.plugins.ktor)
}

application {
	mainClass.set("com.lexfury.kkon.MainKt")
}

ktor {
	fatJar {
		archiveFileName.set("server.fat.jar")
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
	jvmToolchain(17)
}

dependencies {
	implementation(project(":dataTransfer"))

	implementation(libs.ktor.server.core)
	implementation(libs.ktor.server.netty)
	implementation(libs.ktor.server.content.negotiation)
	implementation(libs.ktor.serialization.kotlinx.json)
	implementation(libs.ktor.server.call.logging)
	implementation(libs.logback.classic)
}