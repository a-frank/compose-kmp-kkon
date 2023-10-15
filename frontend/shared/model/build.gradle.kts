plugins {
	alias(libs.plugins.multiplatform)
}

kotlin {
	jvm()
	iosSimulatorArm64()
	iosArm64()
	iosX64()

	sourceSets {
		getByName("commonMain")
	}
}