plugins {
	alias(libs.plugins.multiplatform)
	alias(libs.plugins.kotlinx.serialization)
}

kotlin {
	jvm()
	iosSimulatorArm64()
	iosArm64()
	iosX64()
	js(IR) {
		moduleName = "kkon-data-transfer"
		browser {
			commonWebpackConfig(Action {
				outputFileName = "kkon-data-transfer.js"
			})
		}
		binaries.executable()
	}

	sourceSets {
		getByName("commonMain") {
			dependencies {
				implementation(libs.kotlinx.serialization.json)
			}
		}
	}
}