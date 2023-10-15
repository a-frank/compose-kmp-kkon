plugins {
	alias(libs.plugins.multiplatform)
	alias(libs.plugins.kotlinx.serialization)
}

kotlin {
	jvm()
	iosSimulatorArm64()
	iosArm64()
	iosX64()

	sourceSets {
		val commonMain = getByName("commonMain") {
			dependencies {
				implementation(project(":dataTransfer"))
				implementation(libs.kotlinx.serialization.json)

				implementation(libs.ktor.client.core)

				implementation(libs.ktor.client.content.negotiation)
				implementation(libs.ktor.serialization.kotlinx.json)

				implementation(libs.koin)

				implementation(libs.touchlab.kermit)
			}
		}
		getByName("jvmMain") {
			dependencies {
				implementation(libs.ktor.client.okhttp)
			}
		}
		val iosMain = create("iosMain") {
			dependsOn(commonMain)
			dependencies {
				implementation(libs.ktor.client.darwin)
			}
		}
		getByName("iosX64Main").dependsOn(iosMain)
		getByName("iosArm64Main").dependsOn(iosMain)
		getByName("iosSimulatorArm64Main").dependsOn(iosMain)
	}
}