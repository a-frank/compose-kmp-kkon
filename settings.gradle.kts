rootProject.name = "kkon-compose"

include(":dataTransfer")
include(":server")
include(":frontend:androidApp")
include(":frontend:desktopApp")
include(":frontend:shared:ui")
include(":frontend:shared:pois")
include(":frontend:shared:model")
include(":frontend:shared:network")

pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
		google()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version ("0.4.0")
}

dependencyResolutionManagement {
	repositories {
		mavenCentral()
		google()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}
