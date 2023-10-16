plugins {
	alias(libs.plugins.multiplatform)
}

kotlin {
	jvm()
	iosSimulatorArm64()
	iosArm64()
	iosX64()
	js(IR) {
		moduleName = "kkon-pois"
		browser {
			commonWebpackConfig(Action {
				outputFileName = "kkon-pois.js"
			})
		}
		binaries.executable()
	}

	sourceSets {
		val commonMain = getByName("commonMain") {
			dependencies {
				implementation(project(":dataTransfer"))
				implementation(project(":frontend:shared:model"))
				implementation(project(":frontend:shared:network"))

				implementation(libs.kotlin.coroutine.core)
				implementation(libs.koin)
			}
		}

		val iosMain = create("iosMain") {
			dependsOn(commonMain)
		}
		getByName("iosX64Main").dependsOn(iosMain)
		getByName("iosArm64Main").dependsOn(iosMain)
		getByName("iosSimulatorArm64Main").dependsOn(iosMain)
		getByName("jvmMain")
	}
}