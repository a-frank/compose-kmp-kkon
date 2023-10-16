plugins {
	alias(libs.plugins.multiplatform)
}

kotlin {
	jvm()
	iosSimulatorArm64()
	iosArm64()
	iosX64()
	js(IR) {
		moduleName = "kkon-model"
		browser {
			commonWebpackConfig(Action {
				outputFileName = "kkon-model.js"
			})
		}
		binaries.executable()
	}

	sourceSets {
		getByName("commonMain")
	}
}