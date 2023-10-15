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
		getByName("commonMain") {
			dependencies {
				implementation(libs.kotlinx.serialization.json)
			}
		}
	}
}